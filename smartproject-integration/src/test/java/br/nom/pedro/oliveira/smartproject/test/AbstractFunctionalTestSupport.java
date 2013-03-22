package br.nom.pedro.oliveira.smartproject.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext-test.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class AbstractFunctionalTestSupport extends AbstractTransactionalJUnit4SpringContextTests {
	
	/**
	 * The JPA Entity Manager.
	 */
	@PersistenceContext
	protected EntityManager entityManager;
}
