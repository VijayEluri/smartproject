package br.nom.pedro.oliveira.smartproject.integration.dao;

import br.nom.pedro.oliveira.smartproject.domain.annotation.RepositoryLayer;
import com.ppm.infrastructure.utils.log.Severity;
import static com.ppm.infrastructure.utils.log.Severity.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A abstract JpaDao
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@RepositoryLayer
abstract class JpaDao<T> {

	/**
	 * The JPA Entity Manager
	 */
	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Default Constructor
	 */
	public JpaDao() {
	}
	
	/**
	 * Gets the ORM Class.
	 * @return 
	 */
	public abstract Class<T> getOrmClass();

	/**
	 * set the entityManager
	 *
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

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
}
