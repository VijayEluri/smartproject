package br.nom.pedro.oliveira.smartproject.integration.persistence;

import com.ppm.infrastructure.dao.UnitOfWork;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.ppm.infrastructure.tools.Reflections.*;

/**
 * The SmartProject Unit of Work
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
public final class SmProjectUnitOfWork implements UnitOfWork {

	/**
	 * The id.
	 */
	private final long id;
	/**
	 * The roll back only.
	 */
	private boolean rollBackOnly = false;
	/**
	 * The status.
	 */
	private Status status = Status.NOT_INITIALIZED;
	/**
	 * The log.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SmProjectUnitOfWork.class);
	/**
	 * The to insert set.
	 */
	private final List<EntityHolder> toInsertSet = new LinkedList<>();
	/**
	 * The to insert with parent set.
	 */
	private final List<EntityHolder> toInsertWithParentSet = new LinkedList<>();
	/**
	 * The to delete set.
	 */
	private final Set<EntityHolder> toDeleteSet = new LinkedHashSet<>();
	/**
	 * The to update set.
	 */
	private final Set<EntityHolder> toUpdateSet = new LinkedHashSet<>();
	/**
	 * The Entity manager.
	 */
	private final EntityManager entityManager;

	/**
	 * Instantiates a new PPW unit of work.
	 *
	 * @param dataManager the data manager
	 */
	protected SmProjectUnitOfWork(final EntityManager entityManager) {
		log("New PPWUnitOfWork created.");
		this.entityManager = entityManager;
		this.id = Thread.currentThread().getId();
		changeStatus(Status.NEW);
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#markToInsert(java.lang.Object)
	 */
	@Override
	public void markToInsert(final Object entity) {
		if (entity != null) {
			isReadyToUse();
			changeStatus(Status.ACTIVE);
			final EntityHolder holder = new EntityHolder(entity, getCallers());
			toInsertSet.add(holder);
			log("markToInsert Object: ".concat(objectReference(holder.getEntity())));
		}
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#markToInsert(java.lang.Object,
	 * java.lang.Object[])
	 */
	@Override
	public void markToInsert(final Object entity, final Object... parents) {
		if (entity != null) {
			isReadyToUse();
			changeStatus(Status.ACTIVE);
			final EntityHolder holder = new EntityHolder(entity, parents, getCallers());
			checkParentsMarkedToInsert(parents);
			toInsertWithParentSet.add(holder);
			log("markToInsert Object With Parent Check: ".concat(objectReference(holder.getEntity())));
		}
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#markToUpdate(java.lang.Object)
	 */
	@Override
	public void markToUpdate(final Object entity) {
		if (entity != null) {
			isReadyToUse();
			changeStatus(Status.ACTIVE);
			final EntityHolder holder = new EntityHolder(entity, getCallers());
			if (toUpdateSet.contains(holder)) {
				toUpdateSet.remove(holder);
			}
			toUpdateSet.add(holder);
			log("markToUpdate Object: ".concat(objectReference(holder.getEntity())));
		}
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#markToDelete(java.lang.Object)
	 */
	@Override
	public void markToDelete(final Object entity) {
		if (entity != null) {
			isReadyToUse();
			changeStatus(Status.ACTIVE);
			final EntityHolder holder = new EntityHolder(entity, getCallers());
			if (toDeleteSet.contains(holder)) {
				toDeleteSet.remove(holder);
			}
			toDeleteSet.add(holder);
			log("markToDelete Object:".concat(objectReference(holder.getEntity())));
		}
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#commit()
	 */
	@Override
	public void commit() {
		try {
			changeStatus(Status.COMMITING);
			doInsertOperations();
			doInsertWithParentOperations();
			doUpdateOperations();
			doDeleteOperations();
			changeStatus(Status.COMMITTED);

			if (isRollbackOnly()) {
				rollback();
			}
		} catch (RuntimeException ex) {
			changeStatus(Status.ERROR);
			throw ex;
		} finally {
			clean();
		}
	}

	/**
	 * Do insert operations.
	 */
	private void doInsertOperations() {
		if (!toInsertSet.isEmpty()) {
			log("Starting to Insert");
			for (EntityHolder holder : toInsertSet) {
				try {
					log("TRY TO SAVE: ".concat(objectReference(holder.getEntity())));
					entityManager.persist(holder.getEntity());
					entityManager.flush();
					log(objectReference(holder.getEntity()).concat(": SAVED!"));
				} catch (RuntimeException ex) {
					logError("ERROR ON SAVE: ");
					logError("Objeto: ".concat(objectReference(holder.getEntity())));
					logError("Callers: ".concat(Arrays.deepToString(holder.getStacks())), ex);
					throw ex;
				}
			}
		}
	}

	/**
	 * Do insert with parent operations.
	 */
	private void doInsertWithParentOperations() {
		if (!toInsertWithParentSet.isEmpty()) {
			log("Starting to Insert With Parents");
			for (EntityHolder holder : toInsertWithParentSet) {
				try {
					log("TRY TO SAVE W/PARENT: ".concat(objectReference(holder.getEntity())));
					if (entityHasCompositeId(holder.getEntity())) {
						populateCompositeId(holder.getEntity(), holder.getParents());
					} else {
						populateSingleId(holder.getEntity(), holder.getParents());
					}
					entityManager.persist(holder.getEntity());
					entityManager.flush();
					log(objectReference(holder.getEntity()).concat(": SAVED!"));
				} catch (RuntimeException ex) {
					logError("ERROR ON W/PARENT: ");
					logError("Objeto: ".concat(objectReference(holder.getEntity())));
					logError("Callers: ".concat(Arrays.deepToString(holder.getStacks())), ex);
					throw ex;
				}
			}
		}
	}

	/**
	 * Populate composite id.
	 *
	 * @param entity the entity
	 * @param parents the parents
	 */
	private void populateCompositeId(final Object entity, final Object[] parents) {
		try {
			//FIXME: Melhorar tratamento de excecao.
			Field compIdField = entity.getClass().getDeclaredField("comp_id");
			Field[] idFields = compIdField.getType().getDeclaredFields();
			Method getCompIdMethod = findMethod(entity.getClass(), methodGet(compIdField));
			Object compId = getCompIdMethod.invoke(entity, (Object[]) null);

			for (Field idField : idFields) {
				if (isNull(idField, compId)) {
					idField.setAccessible(true);
					for (Object parent : parents) {
						boolean fieldCopied = false;
						if (entityHasCompositeId(parent)) {
							fieldCopied = tryCopyFromCompIdOnParent(idField, compId, parent);
						}
						if (!fieldCopied) {
							fieldCopied = tryCopyFromParent(idField, compId, parent);
						}
						log("populateCompositeId - FIELD COPIED: ".concat(idField.getName()).concat(" - ").concat(String.valueOf(fieldCopied)));
					}
				}
			}
		} catch (Exception ex) {
			logError("ERROR ON populateCompositeId()", ex);
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Populate single id.
	 *
	 * @param entity the entity
	 * @param parents the parents
	 */
	private void populateSingleId(final Object entity, final Object[] parents) {
		try {
			Field[] idFields = entity.getClass().getDeclaredFields();

			for (Field idField : idFields) {
				if (isNull(idField, entity)) {
					idField.setAccessible(true);
					for (Object parent : parents) {
						boolean fieldCopied = false;
						if (entityHasCompositeId(parent)) {
							fieldCopied = tryCopyFromCompIdOnParent(idField, entity, parent);
						}
						if (!fieldCopied) {
							fieldCopied = tryCopyFromParent(idField, entity, parent);
						}
						log("populateSingleId - FIELD COPIED: ".concat(idField.getName()).concat(" - ").concat(String.valueOf(fieldCopied)));
					}
				}
			}
		} catch (Exception ex) {
			logError("ERROR ON populateSingleId()", ex);
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Try copy from comp id on parent.
	 *
	 * @param idField the id field
	 * @param entity the entity
	 * @param parent the parent
	 *
	 * @return true, if successful
	 *
	 * @throws SecurityException the security exception
	 * @throws NoSuchFieldException the no such field exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	private boolean tryCopyFromCompIdOnParent(final Field idField, final Object entity, final Object parent)
		throws SecurityException, NoSuchFieldException,
		IllegalArgumentException, IllegalAccessException,
		InvocationTargetException {

		boolean fieldCopied = false;
		Field parentCompIdField = parent.getClass().getDeclaredField("comp_id");
		Field[] parentIdFields = parentCompIdField.getType().getDeclaredFields();
		Method parentGetCompIdMethod = findMethod(parent.getClass(), methodGet(parentCompIdField));
		Object parentCompId = parentGetCompIdMethod.invoke(parent, (Object[]) null);

		for (Field parentIdField : parentIdFields) {
			parentCompIdField.setAccessible(true);
			if (idField.getName().equals(parentIdField.getName())) {
				copyValueParentToChild(idField, entity, parentCompId);
				fieldCopied = true;
				break;
			}
		}
		return fieldCopied;
	}

	/**
	 * Try copy from parent.
	 *
	 * @param idField the id field
	 * @param entity the entity
	 * @param parent the parent
	 *
	 * @return true, if successful
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	private boolean tryCopyFromParent(Field idField, Object entity, Object parent)
		throws IllegalArgumentException, IllegalAccessException,
		InvocationTargetException {

		boolean fieldCopied = false;
		Field[] parentFields = parent.getClass().getDeclaredFields();
		for (Field parentField : parentFields) {
			parentField.setAccessible(true);
			if (idField.getName().equals(parentField.getName())) {
				copyValueParentToChild(idField, entity, parent);
				fieldCopied = true;
				break;
			}
		}
		return fieldCopied;
	}

	/**
	 * Copy value parent to child.
	 *
	 * @param idField the id field
	 * @param entity the entity
	 * @param parent the parent
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	private void copyValueParentToChild(final Field idField, final Object entity, final Object parent)
		throws IllegalArgumentException, IllegalAccessException,
		InvocationTargetException {
		final Method childSetMethod = findMethod(entity.getClass(), methodSet(idField), idField.getType());
		final Method parentGetMethod = findMethod(parent.getClass(), methodGet(idField));
		final Object value = parentGetMethod.invoke(parent, (Object[]) null);
		childSetMethod.invoke(entity, new Object[]{value});
	}

	/**
	 * Checks if is null.
	 *
	 * @param field the field
	 * @param entity the entity
	 *
	 * @return true, if is null
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	private boolean isNull(final Field field, final Object entity) throws IllegalArgumentException,
		IllegalAccessException, InvocationTargetException {
		final Method method = findMethod(field.getDeclaringClass(), methodGet(field));
		return method != null && (method.invoke(entity, (Object[]) null) == null);
	}

	/**
	 * Entity has composite id.
	 *
	 * @param entity the entity
	 *
	 * @return true, if successful
	 */
	private boolean entityHasCompositeId(final Object entity) {
		boolean hasField;
		try {
			hasField = entity.getClass().getDeclaredField("comp_id") != null;
		} catch (NoSuchFieldException ex) {
			hasField = false;
		}
		return hasField;
	}

	/**
	 * Do update operations.
	 */
	private void doUpdateOperations() {
		if (!toUpdateSet.isEmpty()) {
			log("Starting to Update");
			for (EntityHolder holder : toUpdateSet) {
				try {
					log("TRY TO UPDATE: ".concat(objectReference(holder.getEntity())));
					entityManager.merge(holder.getEntity());
					entityManager.flush();
					log(objectReference(holder.getEntity()).concat(": UPDATED!"));
				} catch (RuntimeException ex) {
					logError("ERROR ON UPDATE: ");
					logError("Objeto: ".concat(objectReference(holder.getEntity())));
					logError("Callers: ".concat(Arrays.deepToString(holder.getStacks())), ex);
					throw ex;
				}
			}
		}
	}

	/**
	 * Do delete operations.
	 */
	private void doDeleteOperations() {
		if (!toDeleteSet.isEmpty()) {
			log("Starting to Delete");
			for (EntityHolder holder : toDeleteSet) {
				try {
					log("TRY TO DELETE: ".concat(objectReference(holder.getEntity())));
					entityManager.remove(holder.getEntity());
					entityManager.flush();
					log(objectReference(holder.getEntity()).concat(": DELETED!"));
				} catch (RuntimeException ex) {
					logError("ERROR ON DELETE: ");
					logError("Objeto: ".concat(objectReference(holder.getEntity())));
					logError("Callers: ".concat(Arrays.deepToString(holder.getStacks())), ex);
					throw ex;
				}
			}
		}
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#rollback()
	 */
	@Override
	public void rollback() {
		try {
			changeStatus(Status.ROLLING_BACK);
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().setRollbackOnly();
			}
			changeStatus(Status.ROLLEDBACK);
		} catch (Exception ex) {
			changeStatus(Status.ERROR);
			logError("ERROR ON ROLLBACK: ", ex);
			throw ex;
		} finally {
			clean();
		}
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#setRollbackOnly()
	 */
	@Override
	public void setRollbackOnly() {
		log("Seted to RollBackOnly!!");
		this.rollBackOnly = true;
	}

	/**
	 * Checks if is rollback only.
	 *
	 * @return true, if is rollback only
	 */
	protected boolean isRollbackOnly() {
		return this.rollBackOnly;
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#clean()
	 */
	@Override
	public void clean() {
		if (Status.COMMITTED.equals(getStatus())
			|| Status.ERROR.equals(getStatus())
			|| Status.ROLLEDBACK.equals(getStatus())) {

			if (!toInsertSet.isEmpty()) {
				toInsertSet.clear();
			}
			if (!toInsertWithParentSet.isEmpty()) {
				toInsertWithParentSet.clear();
			}
			if (!toUpdateSet.isEmpty()) {
				toUpdateSet.clear();
			}
			if (!toDeleteSet.isEmpty()) {
				toDeleteSet.clear();
			}

			if (Status.ERROR.equals(getStatus())
				|| Status.ROLLEDBACK.equals(getStatus())) {
				entityManager.clear();
			}
			changeStatus(Status.SLEEPING);
		}
	}

	/**
	 * Change status.
	 *
	 * @param currentStatus the current status
	 */
	protected void changeStatus(Status currentStatus) {
		log("PPWUnitOfWork.changeStatus: " + currentStatus.name());
		this.status = currentStatus;
	}

	/**
	 * Log.
	 *
	 * @param msg the msg
	 */
	private void log(final String msg) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace(
				MessageFormat.format("UnitOfWork-{0}: {1}", id, msg));
		}
	}

	/**
	 * Log.
	 *
	 * @param msg the msg
	 * @param t the t
	 */
	private void logError(final Object msg, Throwable t) {
		LOGGER.error(MessageFormat.format("UnitOfWork-{0}: {1}", id, msg), t);
	}

	private void logError(final Object msg) {
		LOGGER.error(MessageFormat.format("UnitOfWork-{0}: {1}", id, msg));
	}

	/**
	 * Object reference.
	 *
	 * @param o the o
	 *
	 * @return the string
	 */
	private String objectReference(Object o) {
		final String reference;
		if (o == null) {
			reference = "null";
		} else {
			reference = o.toString();
		}
		return reference;
	}

	/**
	 * Gets the callers.
	 *
	 * @return the callers
	 */
	private StackTraceElement[] getCallers() {
		StackTraceElement[] callers = null;
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		if (stacks != null) {
			int offset = (stacks.length < 10) ? stacks.length : 10;
			callers = new StackTraceElement[offset];
			System.arraycopy(stacks, 0, callers, 0, offset);
		}
		return callers;
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.UnitOfWork#getStatus()
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/**
	 * Checks if is ready to use.
	 */
	private void isReadyToUse() {
		if (getStatus().equals(Status.NOT_INITIALIZED)) {
			throw new IllegalStateException(new StringBuilder().append("UnitOfWork-").append(id).append(": ").append("Not Initialized").toString());
		}
	}

	/**
	 * Check parents marked to insert.
	 *
	 * @param parents the parents
	 */
	private void checkParentsMarkedToInsert(final Object[] parents) {
		for (Object parent : parents) {
			if (!searchOnToInsertSet(parent) && !searchParentOnSession(parent)) {
				throw new IllegalStateException(new StringBuilder().append("UnitOfWork-").append(id).append(": ").append("YOU NEED TO MARK THE PARENT: ").append(objectReference(parent)).append(" TO INSERT FIRST!").toString());
			}
		}
	}

	/**
	 * Search parent on session.
	 *
	 * @param parent the parent
	 *
	 * @return true, if successful
	 */
	private boolean searchParentOnSession(final Object parent) {
		return entityManager.contains(parent);
	}

	/**
	 * Search on to insert set.
	 *
	 * @param entity the entity
	 *
	 * @return true, if successful
	 */
	private boolean searchOnToInsertSet(Object entity) {
		boolean finded = false;
		for (EntityHolder holder : toInsertSet) {
			if (holder.sameIdentityAs(entity)) {
				finded = true;
				break;
			}
		}
		return finded;
	}
}
