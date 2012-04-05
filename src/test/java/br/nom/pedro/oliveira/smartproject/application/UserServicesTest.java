/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.pedro.oliveira.smartproject.application;

import br.nom.pedro.oliveira.smartproject.domain.*;
import com.ppm.model.Identity;
import org.junit.*;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira20@gmail.com>
 */
public class UserServicesTest {
    
    @Mock Repository<User> userRepository;
    private UserServices service;

    /**
     * 
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UserServicesImpl(userRepository);
    }

    /**
     * 
     */
    @After
    public void tearDown() {
    	service = null;
    	userRepository = null;
    }

    /**
     * Test of authenticate method, of class UserServices.
     * @throws Exception 
     */
    @Test
    public void testAuthenticate() throws Exception {        
        
    	//TestCase 1
        testWithAValidUserId(service);
    }

    private void testWithAValidUserId(UserServices service) throws Exception {
        
        UserId id = UserId.newId("pedro", "pedro00");
       
        User expectedUser = User.createUser(id, UserCredentials.newCredentials(UserToken.newToken("1234"), AcessLevel.DEFAULT));
                
        when(userRepository.findById(new Identity<>(id))).thenReturn(expectedUser);
        
        User user = service.authenticate(id);
        
        assertEquals("testWithAValidUserId", expectedUser, user);
    }
    
    public void testRegister() throws Exception {
    	fail("Not tested");
    }
}
