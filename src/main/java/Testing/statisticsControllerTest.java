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
import com.example.demo.Controller.globalController;
import com.example.demo.Controller.statisticsController;

import Entity.Statistics;
import Repository.statisticsRepository;
import Repository.userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)


public class statisticsControllerTest 
{
	@Autowired
	statisticsController statsControl;
	@Autowired
	globalController acc;
	@Autowired
	userRepository userepo;
	@Autowired
	statisticsRepository statsRepo;
	
	@Test
	public void testAdminApproveStatisticsValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("adminApproveStatistics", statsControl.adminApproveStatistics(model, "Users", 0));
	}

	@Test
	public void testAdminApproveStatisticsInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("index" , statsControl.adminApproveStatistics(model, "Users", 54545));
	}

	@Test
	public void testAdminApproveStatisticsValidData2() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("adminApproveStatistics" , statsControl.adminApproveStatistics(model, "Products", 1));
	}
	
	@Test
	public void testAdminApproveStatisticsInValidData2() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("bbb@gmail.com", "123", model, req);
		assertEquals("index" , statsControl.adminApproveStatistics(model, "Products", -545));
	}

	
	@Test
	public void testStoreOwnerProductsStatisticsValid() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		Optional<Statistics> stattt = statsRepo.findById("Products");
		if(stattt.get().getStatCheck() == 1)
			assertEquals("storeOwnerProductsStatistics" , statsControl.storeOwnerProductsStatistics(model));
		else
			assertEquals("storeOwnerAccessStat" , statsControl.storeOwnerProductsStatistics(model));
	}
	
	@Test
	public void testStoreOwnerUsersStatisticsValid() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		Optional<Statistics> stattt = statsRepo.findById("Users");
		if(stattt.get().getStatCheck() == 1)
			assertEquals("storeOwnerUsersStatistics" , statsControl.storeOwnerUsersStatistics(model));
		else
			assertEquals("storeOwnerAccessStat" , statsControl.storeOwnerUsersStatistics(model));
	}
	
	@Test
	public void testCustomerProductsStatisticsValid() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		Optional<Statistics> stattt = statsRepo.findById("Products");
		if(stattt.get().getStatCheck() == 1)
			assertEquals("customerProductsStatistics" , statsControl.customerProductsStatistics(model));
		else
			assertEquals("customerAccessStat" , statsControl.customerProductsStatistics(model));
	}
	

	
	@Test
	public void testCustomerUsersStatisticsValid() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		Optional<Statistics> stattt = statsRepo.findById("Users");
		if(stattt.get().getStatCheck() == 1)
			assertEquals("customerUsersStatistics" , statsControl.customerUsersStatistics(model));
		else
			assertEquals("customerAccessStat" , statsControl.customerUsersStatistics(model));
	}
	

    @Test
	public void testStoreOwnerShowStatValid() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerShowStat" , statsControl.storeOwnerShowStat(req, model));
	}
    
    @Test
	public void testStoreOwnerShowStatInValid() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		assertEquals("index" , statsControl.storeOwnerShowStat(req, model));
	}

}
