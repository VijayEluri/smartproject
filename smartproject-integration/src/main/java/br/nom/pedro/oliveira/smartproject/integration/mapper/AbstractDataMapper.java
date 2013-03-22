package br.nom.pedro.oliveira.smartproject.integration.mapper;

import com.ppm.infrastructure.dao.EntityStateManager;
import com.ppm.infrastructure.utils.log.Severity;
import static com.ppm.infrastructure.utils.log.Severity.*;
import com.ppm.model.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A Class that will map a type to the Database correspondent.
 *
 * You will extends this class when
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Component
public abstract class AbstractDataMapper<T extends Entity, ORM> {

	@Autowired
	protected EntityStateManager entityStateManager;
	/**
	 * The entity manager.
	 */
	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Map a Entity to ORM.
	 *
	 * @param entity
	 * @return
	 */
	public abstract ORM entityToORM(T entity);

	/**
	 * Map a ORM to a Entity
	 *
	 * @param orm
	 * @return
	 *
	 */
	public abstract T ormToEntity(ORM orm);

	/**
	 * Will try to delete a entity to the database sch ema
	 *
	 * @param entity
	 */
	public abstract void insert(T entity);

	/**
	 * Will try to delete a entity to the database schema
	 *
	 * @param entity
	 */
	public abstract void update(T entity);

	/**
	 * Will try to delete a entity to the database schema
	 *
	 * @param entity
	 */
	public abstract void delete(T entity);
	
	/**
	 * The Logger
	 */
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void log(String msg, Severity s) {
		if (INFO.equals(s) && logger.isInfoEnabled()) {
			logger.info(msg);
		} else if (WARN.equals(s) && logger.isWarnEnabled()) {
			logger.warn(msg);
		} else if (DEBUG.equals(s) && logger.isDebugEnabled()) {
			logger.debug(msg);
		} else if (ERROR.equals(s) && logger.isErrorEnabled()) {
			logger.error(msg);
		} else {
			//The Default Log Level
			logger.trace(msg);
		}
	}

	protected void logError(final String msg) {
		this.log(msg, ERROR);
	}

	protected void logInfo(final String msg) {
		this.log(msg, INFO);
	}

	public void setEntityStateManager(EntityStateManager entityStateManager) {
		this.entityStateManager = entityStateManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
