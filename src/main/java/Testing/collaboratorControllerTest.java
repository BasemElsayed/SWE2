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
import com.example.demo.Controller.collaboratorController;
import com.example.demo.Controller.globalController;

import Entity.Collaborator;
import Entity.Store;
import Entity.User;
import Repository.collaboratorRepository;
import Repository.storeRepository;
import Repository.userRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Swe2ProjectApplication.class
)

public class collaboratorControllerTest {

	@Autowired
	collaboratorController collaborControl;
	@Autowired
	globalController acc;
	@Autowired
	collaboratorRepository collaborRepo;	
	@Autowired
	userRepository userepo;
	@Autowired
	storeRepository storRepo;
	
	
	
	@Test
	public void testStoreOwnerShowCollaboratorValidRequest() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerShowCollaborator" , collaborControl.storeOwnerShowCollaborator(req, model));
	}
	
	@Test
	public void testStoreOwnerShowCollaboratorInValidRequest() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		assertEquals("index" , collaborControl.storeOwnerShowCollaborator(req, model));
	}
	
	@Test
	public void storeOwnerShowCollaboratorValidDataDelete() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		Optional<User> storOwner = userepo.findById("sss@gmail.com");
		Optional<User> collaborrr = userepo.findById("sss2@gmail.com");
		Optional<Store> stor = storRepo.findById("macccc");
		
		Collaborator colabor = new Collaborator(storOwner.get(), stor.get(), collaborrr.get());
		collaborRepo.save(colabor);
		assertEquals("storeOwnerShowCollaborator" , collaborControl.storeOwnerShowCollaborator(req, model, colabor.getId()));
	}
	@Test
	public void storeOwnerAddCollaboratorInValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerAddCollaborator" , collaborControl.storeOwnerAddCollaborator("Joy Kissssssssssds", "sss2@gmail.com", model, req));
	}
	
	@Test
	public void storeOwnerAddCollaboratorValidData() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("storeOwnerAddCollaborator" , collaborControl.storeOwnerAddCollaborator("Joy Kids", "sss2@gmail.com", model, req));
	}
	
	
	@Test
	public void storeOwnerAddCollaboratorInValidDataEmail() 
	{	
		MockHttpServletRequest req = new MockHttpServletRequest();
		Model model = Mockito.mock(Model.class);
		acc.loginIn("sss@gmail.com", "123", model, req);
		assertEquals("index" , collaborControl.storeOwnerAddCollaborator("Joy Kids", "sss2ssssss@gmail.com", model, req));
	}
	
}
