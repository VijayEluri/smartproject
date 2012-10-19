package br.nom.pedro.oliveira.smartproject.application;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.nom.pedro.oliveira.smartproject.domain.AcessLevel;
import br.nom.pedro.oliveira.smartproject.domain.Repository;
import br.nom.pedro.oliveira.smartproject.domain.User;
import br.nom.pedro.oliveira.smartproject.domain.UserCredentials;
import br.nom.pedro.oliveira.smartproject.domain.UserId;
import br.nom.pedro.oliveira.smartproject.domain.UserToken;
import br.nom.pedro.oliveira.smartproject.domain.common.Email;

import com.ppm.model.Identity;

/**
 * 
 * @author Pedro T. Oliveira <pedro.oliveira20@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServicesTest {

	@Mock
	Repository<User> userRepository;
	private UserServices service;

	@Before
	public void setUp() {
		service = new UserServicesProvider(userRepository);
	}

	/**
     *
     */
	@After
	public void tearDown() {
		service = null;
		userRepository = null;
	}

	@Test
	public void testWithAValidUserId() throws Exception {
		
		 final UserId id = UserId.newId(new Email("pedro@test.com.br"), "passwordtest");
		 final UserCredentials cred = UserCredentials.newCredentials(UserToken.newToken("1234"), AcessLevel.DEFAULT);
		 final User expectedUser = User.createUser(id, cred);
		
		 when(userRepository.findById(new Identity<UserId>(id))).thenReturn(expectedUser);
		
		 Assert.assertEquals("testWithAValidUserId", expectedUser, service.authenticate(id));
	}
	
	@Test
	public void testRegister() throws Exception {
		fail("Not tested");
	}
}
