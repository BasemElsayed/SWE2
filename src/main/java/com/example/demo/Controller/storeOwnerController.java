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

import Entity.StoreProduct;
import Entity.User;
import Entity.storeOwnerActions;
import Repository.storeOwnerActionsRepository;
import Repository.storeProductsRepository;
import Repository.userRepository;

@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class storeOwnerController {
	
	@Autowired
	userRepository us;
	@Autowired
	storeProductsRepository storeProdRepo;
	@Autowired
	storeOwnerActionsRepository storeActionRepo;
	
	
	
	
	@GetMapping("/storeOwnerHomePage")
	public String storeOwnerHomePage(HttpServletRequest request, Model mod) {
		
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
            	
            	mod.addAttribute("products", storeProducts);
				
				 return "storeOwnerHomePage";
			 }
		}
		return "index";
	}
	
	
	
	
	
	
	
	//Delete Product
	@PostMapping("/storeOwnerHomePage")
	public String storeOwnerHomePage(HttpServletRequest request, Model mod, @RequestParam String storeProdID) {
		
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
    			Iterable <StoreProduct> storeProdsIT = storeProdRepo.findAll();
    			for(StoreProduct storePro : storeProdsIT)
    			{
    				if(storePro.getProduct().getCode().equals(storeProdID) && storePro.getStore().getUser().getEmail().equals(temp.getEmail()))
    				{
    					storeProdRepo.deleteById(storePro.getId());
    					
    					storeOwnerActions addProdAction = new storeOwnerActions("Delete Product", storePro.getNumberOfProduct(), 
    							storePro.getNumberOfSoldProduct(), storePro.getNumberOfVisitedProduct(), storePro.getProduct(), storePro.getStore());
        				storeActionRepo.save(addProdAction);
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
            	
            	mod.addAttribute("products", storeProducts);
				
				 return "storeOwnerHomePage";
			 }
		}
		return "index";
	}
	
	


	/*
	public ResponseEntity<?> storeOwnerHomePage123(HttpServletRequest request) {
		
		if(request.getSession().getId() == null)
		{
			System.out.println("no Sessions");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{
			User temp = (User) request.getSession().getAttribute("user");
			if(temp == null)
	   		 {
	   			 System.out.println("sessions not belongs to you");
	   			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	   		 } 
			else if(temp.getType().equals("StoreOwner"))
			 {
				 System.out.println("equal Type");
				 return ResponseEntity.status(HttpStatus.OK).body(temp);
			 }
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();;
	}*/

}
