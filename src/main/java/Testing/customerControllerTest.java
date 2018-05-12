package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import com.example.demo.Swe2ProjectApplication;
import com.example.demo.Controller.customerController;
import com.example.demo.Controller.globalController;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)

public class customerControllerTest {

	@Autowired
	globalController acc;
	@Autowired
	customerController custController;

	@Test
	public void testCustomerHomePageValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class); 
		acc.loginIn("ebasem65@gmail.com", "123", model, req);
		assertEquals("customerShowProduct" , custController.customerHomePage(req, model, "123", "LC"));
	}
	
	
	@Test
	public void testCustomerHomePageInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class); 
		acc.loginIn("ebasem65@gmail.com", "123", model, req);
		assertEquals("index" , custController.customerHomePage(req, model, "333", "LC"));
	}
	
	@Test
	public void testCustomerHomePageWithOutSession() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class); 
		assertEquals("index" , custController.customerHomePage(req, model, "123", "LC"));
	}
}
