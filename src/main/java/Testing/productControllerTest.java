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
import com.example.demo.Controller.productController;

import Repository.productRepository;
import Repository.storeProductsRepository;
import Repository.userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)


public class productControllerTest {

	@Autowired
	productController prodControl;
	@Autowired
	globalController acc;
	@Autowired
	userRepository userepo;
	@Autowired
	storeProductsRepository storProdRepo;
	@Autowired
	productRepository prodRepo;
	
	
	
	@Test
	public void testAdminAddProductValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		if(prodRepo.existsById("9"))
			prodRepo.deleteById("9");
		assertEquals("adminAddProduct" , prodControl.adminAddProduct("vbuwesada", "9","Online", "dijq3ekqnwdsm", "ay7aga", "5", model));
	}
	
	
	@Test
	public void testAdminAddProductInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("adminHomePage" , prodControl.adminAddProduct("vbuwesada", "999","Online", "dijq3ekqnwdsm", "ay7agajhhftd", "5", model));
	}
	

	@Test
	public void testAdminAddProductExistData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("adminHomePage" , prodControl.adminAddProduct("vbuwesada", "9","Online", "dijq3ekqnwdsm", "ay7aga", "5", model));
	}
	
	
	@Test
	public void testStoreOwnerAddProductValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerAddProduct" , prodControl.storeOwnerAddProduct(req, model, "9", "LC", 999));
	}
	
	@Test
	public void testStoreOwnerAddProductInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerHomePage" , prodControl.storeOwnerAddProduct(req, model, "9", "LCasdasd", 999));
	}
	
		 
	@Test
	public void testCustomerShowProductValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("ebasem65@gmail.com", "123", model, req);
		assertEquals("customerShowProduct" , prodControl.customerShowProduct(req, model, "9", "LC", "asdasdmb", "12166125", 1));
	}
	
	@Test
	public void testCustomerShowProductInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("ebasem65@gmail.com", "123", model, req);
		assertEquals("customerHomePage" , prodControl.customerShowProduct(req, model, "9", "LC", "asdasdmb", "12166125", -11));
	}

	
	@Test
	public void testStoreOwnerBuyProductValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerShowProduct" , prodControl.storeOwnerBuyProduct(req, model, "9", "LC"));
	}
	
    	
	@Test
	public void testStoreOwnerShowProductValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerShowProduct" , prodControl.storeOwnerShowProduct(req, model, "9", "LC", "asdasdmb", "12166125", 1));
	}
    
	
	@Test
	public void testStoreOwnerShowProductInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerHomePage" , prodControl.storeOwnerShowProduct(req, model, "9", "LC", "asdasdmb", "12166125", -11));
	}

		
	@Test
	public void testStoreOwnerEditProductValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerShowProductEditVersion" , prodControl.storeOwnerEditProduct(req, model, "9", "LC"));
	}
	
	@Test
	public void testStoreOwnerShowProductEditVersionValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerShowProductEditVersion" , prodControl.storeOwnerShowProductEditVersion(req, model, "9", "LC", 5, 2000));
	}
	
	@Test
	public void testStoreOwnerShowProductEditVersionInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerHomePage" , prodControl.storeOwnerShowProductEditVersion(req, model, "9", "LC", -5, 2000));
	}

	@Test
	public void testStoreOwnerShowProductEditVersionInValidData2() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerHomePage" , prodControl.storeOwnerShowProductEditVersion(req, model, "9", "LC", 5, -2000));
	}
	
	
	
}
