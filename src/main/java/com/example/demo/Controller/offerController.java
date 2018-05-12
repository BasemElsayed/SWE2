package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

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
import Entity.StoreProduct;
import Entity.User;
import Repository.collaboratorRepository;
import Repository.storeOwnerActionsRepository;
import Repository.storeProductsRepository;
import Repository.userRepository;

@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class offerController {

	@Autowired
	userRepository us;
	@Autowired
	storeProductsRepository storeProdRepo;
	@Autowired
	storeOwnerActionsRepository storeActionRepo;
	@Autowired
	collaboratorRepository collabratorRepo;
	
	//Add Offer
	@GetMapping("/storeOwnerAddOffer")
	public String storeOwnerAddOffer(HttpServletRequest request, Model mod) {
	
		if(request.getSession().getId() == null)
		{
			System.out.println("no Sessions");
			return "index";
		}
		else
		{
			User temp = (User) request.getSession().getAttribute("user");
			if(temp == null)
	   		 {
	   			 System.out.println("sessions not belongs to you");
	   			 return "index";
	   		 } 
			else if(temp.getType().equals("StoreOwner"))
			 {
            	List<StoreProduct> storeProducts = new ArrayList<StoreProduct>();
            	Iterable<StoreProduct> productIterable = storeProdRepo.findAll();
            	for(StoreProduct product : productIterable)
            	{
            		if(product.getStore().getUser().getEmail().equals(temp.getEmail()))
            		{
            			storeProducts.add(product);			
            			System.out.println("Store Name : " + product.getStore().getUser().getEmail());
            		}
            	}
            	
            	
            	Iterable<Collaborator> collaIterables = collabratorRepo.findAll();
    			for(Collaborator collaborator : collaIterables)
    			{
    				if( (collaborator.getCollaborator().getEmail().equals(temp.getEmail())) )
    				{
    					for(StoreProduct product : productIterable)
    	            	{
    	            		//System.out.println("gwa loop");
    	            		if(collaborator.getStore().getStoreName().equals(product.getStore().getStoreName()))
    	            		{
    	            			storeProducts.add(product);
    	            		}
    	            		//.out.println("Store Name : " + product.getProduct().getName());
    	            	}
    				}
    			}
            	
            	
        		mod.addAttribute("products", storeProducts);
				
        		return "storeOwnerAddOffer";
			 }
		}
		return "index";
	}

			
			
		//Add Offer
		@PostMapping("/storeOwnerAddOffer")
		public String storeOwnerAddOffer(HttpServletRequest request, Model mod, @RequestParam String storeProdID, @RequestParam int discount) {
		
			if(request.getSession().getId() == null)
			{
				System.out.println("no Sessions");
				return "index";
			}
			else
			{
				User temp = (User) request.getSession().getAttribute("user");
				if(temp == null)
		   		 {
		   			 System.out.println("sessions not belongs to you");
		   			 return "index";
		   		 } 
				else if(temp.getType().equals("StoreOwner"))
				 {
	    			
					if(discount < 0)
					{
						discount *= -1;
					}
	    			Iterable <StoreProduct> storeProdsIT = storeProdRepo.findAll();
	    			for(StoreProduct storePro : storeProdsIT)
	    			{
	    				if(storePro.getProduct().getCode().equals(storeProdID) && storePro.getStore().getUser().getEmail().equals(temp.getEmail()))
	    				{
	    					storePro.setDiscount(discount);
	    					storeProdRepo.save(storePro);
	    				}
	    			}		
					
	    			Iterable<Collaborator> collaIterables = collabratorRepo.findAll();
	    			storeProdsIT = storeProdRepo.findAll();
	    			for(Collaborator collaborator : collaIterables)
	    			{
	    				if( (collaborator.getCollaborator().getEmail().equals(temp.getEmail())) )
	    				{
	    					for(StoreProduct storePro : storeProdsIT)
	    	    			{
	    	    				if(storePro.getProduct().getCode().equals(storeProdID))
	    	    				{
	    	    					storePro.setDiscount(discount);
	    	    					storeProdRepo.save(storePro);
	    	    				}
	    	    			}
	    				}
	    			}
	    			
	    			
	    			
					
	            	List<StoreProduct> storeProducts = new ArrayList<StoreProduct>();
	            	Iterable<StoreProduct> productIterable = storeProdRepo.findAll();
	            	for(StoreProduct product : productIterable)
	            	{
	            		if(product.getStore().getUser().getEmail().equals(temp.getEmail()))
	            		{
	            			storeProducts.add(product);			
	            			System.out.println("Store Name : " + product.getStore().getUser().getEmail());
	            		}
	            	}
	            	
	            	collaIterables = collabratorRepo.findAll();
	    			for(Collaborator collaborator : collaIterables)
	    			{
	    				if( (collaborator.getCollaborator().getEmail().equals(temp.getEmail())) )
	    				{
	    					for(StoreProduct product : productIterable)
	    	            	{
	    	            		//System.out.println("gwa loop");
	    	            		if(collaborator.getStore().getStoreName().equals(product.getStore().getStoreName()))
	    	            		{
	    	            			storeProducts.add(product);
	    	            		}
	    	            		//.out.println("Store Name : " + product.getProduct().getName());
	    	            	}
	    				}
	    			}
	        		mod.addAttribute("products", storeProducts);
	        		return "storeOwnerAddOffer";
				 }
			}
			return "index";
		}
}
