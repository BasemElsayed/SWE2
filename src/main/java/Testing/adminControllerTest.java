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
import com.example.demo.Controller.adminController;
import com.example.demo.Controller.globalController;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)



public class adminControllerTest {

	@Autowired
	globalController acc;
	@Autowired
	adminController adminControl;
	
	
	@Test
	public void testAdminHomePageValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("adminHomePage" , adminControl.adminHomePage(req));
	}
	
	@Test
	public void testAdminHomePageInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "1233asdasd", model, req);
		assertEquals("index" , adminControl.adminHomePage(req));
	}
	
	
	@Test
	public void testadminAprroveStoresValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("adminAprroveStores" , adminControl.adminAprroveStores(req, model, "LC"));
	}
	
	
	@Test
	public void testadminAprroveStoresInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("index" , adminControl.adminAprroveStores(req, model, "cmasdaskdaskmdkmsad"));
	}
	
	
}
