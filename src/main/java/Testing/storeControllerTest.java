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
import com.example.demo.Controller.storeController;

import Repository.storeRepository;
import Repository.userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)


public class storeControllerTest {

	@Autowired
	storeController storControl;
	@Autowired
	globalController acc;
	@Autowired
	userRepository userepo;
	@Autowired
	storeRepository storRepo;
	
	@Test
	public void teststoreOwnerAddStoreValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		if(storRepo.existsById("Joy Kids"))
			storRepo.deleteById("Joy Kids");
		assertEquals("storeOwnerAddStore" , storControl.storeOwnerAddStore("Joy Kids", "Emirates", "le3b atfal", "Online", model, req));
	}
	
	@Test
	public void teststoreOwnerAddStoreInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		if(storRepo.existsById("Joy Kids' OR '1'='1"))
			storRepo.deleteById("Joy Kids' OR '1'='1");
		assertEquals("storeOwnerAddStore" , storControl.storeOwnerAddStore("Joy Kids' OR '1'='1", "Emirates", "le3b atfal", "Online", model, req));
	}
	
}
