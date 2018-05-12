package Testing;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import com.example.demo.Swe2ProjectApplication;
import com.example.demo.Controller.actionsController;
import com.example.demo.Controller.globalController;

import Entity.Product;
import Entity.Store;
import Entity.storeOwnerActions;
import Repository.productRepository;
import Repository.storeOwnerActionsRepository;
import Repository.storeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)

public class actionControllerTest {

	@Autowired
	actionsController actionControl;
	@Autowired
	globalController acc;
	@Autowired
	storeOwnerActionsRepository actionRepo;
	@Autowired
	storeRepository storeRepo;
	@Autowired
	productRepository prodRepo;

	
	@Test
	public void testStoreOwnerActionsHistoryValidDataDelete() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		Optional<Store> finded = storeRepo.findById("macccc");
		Optional<Product> findedProd = prodRepo.findById("123");
		storeOwnerActions storAction = new storeOwnerActions("Delete Product", 1, 1, 1, findedProd.get(), finded.get());
		actionRepo.save(storAction);
		assertEquals("storeOwnerActionsHistory" , actionControl.storeOwnerActionsHistory(req, model, "Delete Product", "macccc", "123"));
	}

	
	@Test
	public void testStoreOwnerActionsHistoryValidDataEdit() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		Optional<Store> finded = storeRepo.findById("LC");
		Optional<Product> findedProd = prodRepo.findById("123");
		storeOwnerActions storAction = new storeOwnerActions("Edit Product", 1, 1, 1, findedProd.get(), finded.get());
		actionRepo.save(storAction);
		assertEquals("storeOwnerActionsHistory" , actionControl.storeOwnerActionsHistory(req, model, "Edit Product", "LC", "123"));
	}
	
	
	@Test
	public void testStoreOwnerActionsHistoryValidDataAdd() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		Optional<Store> finded = storeRepo.findById("macccc");
		Optional<Product> findedProd = prodRepo.findById("123");
		storeOwnerActions storAction = new storeOwnerActions("Add Product", 1, 1, 1, findedProd.get(), finded.get());
		actionRepo.save(storAction);
		assertEquals("storeOwnerActionsHistory" , actionControl.storeOwnerActionsHistory(req, model, "Add Product", "macccc", "123"));
	}
	
	@Test
	public void testStoreOwnerActionsHistoryValidDataRequest() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerActionsHistory" , actionControl.storeOwnerActionsHistory(req, model));
	}
	
	@Test
	public void testStoreOwnerActionsHistoryInValidDataRequest() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		assertEquals("index" , actionControl.storeOwnerActionsHistory(req, model));
	}
	
	
	
	
}
