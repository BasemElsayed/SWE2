package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import com.example.demo.Swe2ProjectApplication;
import com.example.demo.Controller.globalController;

import Repository.userRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)

public class globalControllerTest {

	@Autowired
	globalController acc;
	@Autowired
	userRepository userepo;
	
	@Test
	public void testLoginInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class); 
		assertEquals("customerHomePage" , acc.loginIn("ebasem65@gmail.com", "123", model, req));
	}
	
	@Test
	public void testLoginInInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class); 
		assertEquals("index" , acc.loginIn("ebasem65@gmail.com", "12345", model, req));
	}
	
	@Test
	public void testLoginSQLInjection() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class); 
		assertEquals("index" , acc.loginIn("ebasem65@gmail.com", "123' OR '1'='1", model, req));
	}

	@Test
	public void testSignUpValidData() 
	{	
		Model model = Mockito.mock(Model.class);
		if(userepo.existsById("x@gmail.com"))
			userepo.deleteById("x@gmail.com");
		assertEquals("index" , acc.signUp("x", "123", model, "x@gmail.com", "Customer"));
	}
	
	@Test
	public void testSignUpInvalidData() 
	{	
		Model model = Mockito.mock(Model.class);
		assertEquals("signUp" , acc.signUp("x", "123' OR '1'='1", model, "ebasem65@gmail.com", "Customer"));
	}
	
	@Test
	public void testSignUpExistData() 
	{	
		Model model = Mockito.mock(Model.class);
		assertEquals("signUp" , acc.signUp("x", "123", model, "ebasem65@gmail.com", "Customer"));
	}


	
}
