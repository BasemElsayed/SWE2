package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Entity.Collaborator;
import Entity.Store;
import Entity.User;
import Repository.collaboratorRepository;
import Repository.storeRepository;
import Repository.userRepository;



@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class collaboratorController {

	@Autowired
	collaboratorRepository collabratorRepo;
	@Autowired
	storeRepository storeRepo;
	@Autowired
	userRepository us;
	
	@GetMapping("/storeOwnerShowCollaborator")
    public String storeOwnerShowCollaborator(HttpServletRequest request, Model mod)
    {
        try
        {
        	User temp = (User) request.getSession().getAttribute("user");
        	List<Collaborator> collaborators = new ArrayList<Collaborator>();
        	Iterable<Collaborator> collaboratorsIterable = collabratorRepo.findAll();
        	for(Collaborator collaborator : collaboratorsIterable)
        	{
        		if(temp.getEmail().equals(collaborator.getStoreOwner().getEmail()))
        		{
        			collaborators.add(collaborator);
        		}			
        	}
        	
        	mod.addAttribute("collaborators", collaborators);
        	return "storeOwnerShowCollaborator";
        }
        catch(Exception e)
        {
        	return "index";
        }
    }
	
	@PostMapping("/storeOwnerShowCollaborator")
    public String storeOwnerShowCollaborator(HttpServletRequest request, Model mod, @RequestParam int collaID) 
    {
        try
        {
        	
        	collabratorRepo.deleteById(collaID);
        	
        	User temp = (User) request.getSession().getAttribute("user");
        	List<Collaborator> collaborators = new ArrayList<Collaborator>();
        	Iterable<Collaborator> collaboratorsIterable = collabratorRepo.findAll();
        	for(Collaborator collaborator : collaboratorsIterable)
        	{
        		if(temp.getEmail().equals(collaborator.getStoreOwner().getEmail()))
        		{
        			collaborators.add(collaborator);
        		}			
        	}
        	
        	mod.addAttribute("collaborators", collaborators);
        	return "storeOwnerShowCollaborator";
        }
        catch(Exception e)
        {
        	return "index";
        }
    }
	
	
	
	
	
	
	@GetMapping("/storeOwnerAddCollaborator")
    public String storeOwnerAddCollaborator() {
        
        //System.out.println(name+"  "+pass + "  " + email + "  " + type);
        return "storeOwnerAddCollaborator";
    }

    @PostMapping("/storeOwnerAddCollaborator")
    public String storeOwnerAddCollaborator(@RequestParam String storeName,@RequestParam String collaboratoEmail, Model model, HttpServletRequest request)
    {    
        System.out.println(storeName + "  " + collaboratoEmail);
        
        try {
        	
        	User temp = (User) request.getSession().getAttribute("user");
            System.out.println("Email : " + temp.getEmail());
            
            
            Optional<Store> storeColl = storeRepo.findById(storeName);
            Optional<User> collaborator = us.findById(collaboratoEmail);
            
            
            
            
            if(us.existsById(collaboratoEmail) && storeRepo.existsById(storeName))
            {
            	Collaborator coll = new Collaborator(temp, storeColl.get(), collaborator.get()); 
            	collabratorRepo.save(coll);
            	model.addAttribute("succWarning", true);
            }
            else
            {
            	model.addAttribute("warning", true);
            }
            return "storeOwnerAddCollaborator";
        }
        catch(Exception e)
        {
        	return "index";
        }
                
    }

    
    
    
    
    
	
}
