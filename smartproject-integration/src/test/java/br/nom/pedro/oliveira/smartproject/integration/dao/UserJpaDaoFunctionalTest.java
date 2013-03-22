package br.nom.pedro.oliveira.smartproject.integration.dao;

import br.nom.pedro.oliveira.smartproject.domain.User;
import br.nom.pedro.oliveira.smartproject.domain.UserId;
import br.nom.pedro.oliveira.smartproject.domain.common.Email;
import br.nom.pedro.oliveira.smartproject.integration.mapper.UserDataMapper;
import br.nom.pedro.oliveira.smartproject.test.AbstractFunctionalTestSupport;
import com.ppm.infrastructure.dao.EntityStateManager;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
public class UserJpaDaoFunctionalTest extends AbstractFunctionalTestSupport {

	private UserJpaDao userDao;
	@Autowired
	private UserDataMapper userDataMapper;
	@Autowired
	private EntityStateManager entityStateManager;

	public UserJpaDaoFunctionalTest() {
		super();
	}

	@Before
	public void beforeTest() {
		entityStateManager.begin();
		userDao = new UserJpaDao();
		userDao.setDataMapper(userDataMapper);
		userDao.setEntityManager(entityManager);
	}

	@After
	public void afterTest() {		
	}

	
	@Test
	public void testAllDaoOperations() throws Exception {
		addTenUsersToDatabase();
		entityStateManager.commit();
		findTheTenUsersInDatabase();
	}
	
	private void addTenUsersToDatabase() {
		for (int i = 0; i < 10; i++) {
			userDao.create(newTestUser(i));
		}
	}

	private void findTheTenUsersInDatabase() {
		for (int i = 0; i < 10; i++) {
			String userName = "pedro-teste" + i;
			assertThat(userDao.findByUserName(userName), equalTo(newTestUser(i)));
		}
	}

	private User newTestUser(int i) {
		User testUser = new User(UserId.newId(new Email("pedro" + i + "@teste.com.br"), "testepassword" + i));
		testUser.setUserName("pedro-teste" + i);
		return testUser;
	}
}