package br.nom.pedro.oliveira.smartproject.integration.persistence;

import com.ppm.infrastructure.dao.EntityStateManager;
import com.ppm.infrastructure.dao.UnitOfWork;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * The SmartProject Entity State Manager
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Repository
public class SmProjectEntityStateManager implements EntityStateManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmProjectEntityStateManager.class);
	/**
	 * The Thread Local.
	 */
	private final ThreadLocal<UnitOfWork> threadLocal = new ThreadLocal<>();
	/**
	 * The entity manager.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default Constructor
	 */
	public SmProjectEntityStateManager() {
	}

	/**
	 * Instantiates a new PPW entity state manager.
	 *
	 * @param dataManager the data manager
	 */
	public SmProjectEntityStateManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#begin()
	 */
	@Override
	public void begin() {
		log("PPWEntityStateManager.begin()");
		if (threadLocal.get() == null) {
			log("PPWEntityStateManager.setting new unit of work");
			UnitOfWork unitOfWork = new SmProjectUnitOfWork(entityManager);
			threadLocal.set(unitOfWork);
		}
	}

	/**
	 * Gets the current.
	 *
	 * @return the current
	 */
	private UnitOfWork getCurrent() {
		UnitOfWork unitOfWork = threadLocal.get();
		if (unitOfWork == null) {
			throw new IllegalStateException("ERROR: Unit Of Work NOT INITIALIZED!!");
		}
		return unitOfWork;
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#commit()
	 */
	@Override
	public void commit() {
		log("PPWEntityStateManager.commit()");
		getCurrent().commit();
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#rollback()
	 */
	@Override
	public void rollback() {
		log("PPWEntityStateManager.rollback()");
		getCurrent().rollback();
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#setRollbackOnly()
	 */
	@Override
	public void setRollbackOnly() {
		log("PPWEntityStateManager.setRollbackOnly()");
		getCurrent().setRollbackOnly();
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#markToInsert(java.lang.Object)
	 */
	@Override
	public void markToInsert(final Object entity) {
		log("PPWEntityStateManager.markToInsert: ".concat(String.valueOf(entity)));
		getCurrent().markToInsert(entity);
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#markToInsert(java.lang.Object,
	 * java.lang.Object[])
	 */
	@Override
	public void markToInsert(final Object entity, final Object... parents) {
		log("PPWEntityStateManager.markToInsert with parents: ".concat(String.valueOf(entity)));
		getCurrent().markToInsert(entity, parents);
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#markToUpdate(java.lang.Object)
	 */
	@Override
	public void markToUpdate(final Object entity) {
		log("PPWEntityStateManager.markToUpdate: ".concat(String.valueOf(entity)));
		getCurrent().markToUpdate(entity);
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#markToDelete(java.lang.Object)
	 */
	@Override
	public void markToDelete(final Object entity) {
		log("PPWEntityStateManager.markToDelete: ".concat(String.valueOf(entity)));
		getCurrent().markToDelete(entity);
	}

	/*
	 * (non-Javadoc) @see
	 * com.porto.portoprint.automovel.persistence.EntityStateManager#markAllToInsert(java.util.Set)
	 */
	@Override
	public void markAllToInsert(Set<?> collection) {
		log("PPWEntityStateManager.markAllToInsert: ".concat(String.valueOf(collection)));
		for (Object o : collection) {
			getCurrent().markToInsert(o);
		}
	}

	@Override
	public void markAllToUpdate(Set<?> collection) {
		log("PPWEntityStateManager.markAllToUpdate: ".concat(String.valueOf(collection)));
		for (Object o : collection) {
			getCurrent().markToUpdate(o);
		}
	}

	@Override
	public void markAllToRemove(Set<?> collection) {
		log("PPWEntityStateManager.markAllToRemove: ".concat(String.valueOf(collection)));
		for (Object o : collection) {
			getCurrent().markToDelete(o);
		}
	}

	@Override
	public void clean() {
		log("PPWEntityStateManager.clean()");
		getCurrent().clean();
	}

	@Override
	public Status getStatus() {
		log("PPWEntityStateManager.status: ".concat(String.valueOf(getCurrent().getStatus())));
		return getCurrent().getStatus();
	}

	@Override
	public Object findById(final Class<?> classz, final Object id) {
		log("PPWEntityStateManager.findById: ".concat(String.valueOf(id)));
		return entityManager.find(classz, id);
	}

	/**
	 * Log.
	 *
	 * @param msg the msg
	 */
	private void log(final String msg) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace(msg);
		}
	}
}
