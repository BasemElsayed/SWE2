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
import com.example.demo.Controller.globalController;
import com.example.demo.Controller.storeOwnerController;

import Repository.storeProductsRepository;
import Repository.userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)


public class storeOwnerControllerTest {

	
	@Autowired
	storeOwnerController storOwnerControl;
	@Autowired
	globalController acc;
	@Autowired
	userRepository userepo;
	@Autowired
	storeProductsRepository storProdRepo;
	
	
	
	@Test
	public void testStoreOwnerHomePageRequest() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerHomePage" , storOwnerControl.storeOwnerHomePage(req, model));
	}
	
	@Test
	public void testStoreOwnerHomePageInvlaidRequest() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		assertEquals("index" , storOwnerControl.storeOwnerHomePage(req, model));
	}
	
	@Test
	public void testStoreOwnerHomePageDeleteProductValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		if(storProdRepo.existsById(89))
			assertEquals("storeOwnerHomePage" , storOwnerControl.storeOwnerHomePage(req, model, "123"));
	}
	

}
