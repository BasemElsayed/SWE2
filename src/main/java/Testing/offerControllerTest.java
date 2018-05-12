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
import com.example.demo.Controller.offerController;

import Repository.productRepository;
import Repository.storeOwnerActionsRepository;
import Repository.storeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)

public class offerControllerTest 
{
	@Autowired
	offerController offrsControl;
	@Autowired
	globalController acc;
	@Autowired
	storeOwnerActionsRepository actionRepo;
	@Autowired
	storeRepository storeRepo;
	@Autowired
	productRepository prodRepo;

	@Test
	public void testStoreOwnerAddOfferValidRequest() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerAddOffer" , offrsControl.storeOwnerAddOffer(req, model));
	}
	
	@Test
	public void testStoreOwnerAddOfferInValidRequest() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		assertEquals("index" , offrsControl.storeOwnerAddOffer(req, model));
	}
	
	@Test
	public void testStoreOwnerAddOfferValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerAddOffer" , offrsControl.storeOwnerAddOffer(req, model, "123", 5));
	}
	
	@Test
	public void testStoreOwnerAddOfferInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerAddOffer" , offrsControl.storeOwnerAddOffer(req, model, "123", -5));
	}
	
}
