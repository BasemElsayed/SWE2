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
import com.example.demo.Controller.brandController;
import com.example.demo.Controller.globalController;

import Repository.brandRepository;
import Repository.userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)


public class brandControllerTest {

	@Autowired
	brandController brandControl;
	@Autowired
	globalController acc;
	@Autowired
	userRepository userepo;
	@Autowired
	brandRepository brandRepo;

	
	@Test
	public void testAdminAddBrandValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		if(brandRepo.existsById("ZARA"))
			brandRepo.deleteById("ZARA");
		assertEquals("adminAddBrand" , brandControl.adminAddBrand("ZARA", "LebS", "blablalwsdasdjo", model));
	}
	
	@Test
	public void testAdminAddBrandInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		if(brandRepo.existsById("ZARA' OR '1'='1"))
			brandRepo.deleteById("ZARA' OR '1'='1");
		assertEquals("adminAddBrand" , brandControl.adminAddBrand("ZARA' OR '1'='1", "LebS", "blablalwsdasdjo", model));
	}
	
}
