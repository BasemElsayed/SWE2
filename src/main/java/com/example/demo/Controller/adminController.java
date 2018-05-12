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


import Entity.Store;
import Entity.User;
import Repository.storeRepository;
import Repository.userRepository;

@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class adminController {

	@Autowired
	userRepository us;
	@Autowired
	storeRepository storeRepo;
	
	
	@GetMapping("/adminHomePage")
    public String adminHomePage(HttpServletRequest request) {
    	
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
    		else if(temp.getType().equals("Admin"))
    		 {
    			 System.out.println("equal Type");
    			 return "adminHomePage";
    		 }
    	}
		return "index";
    }
	
	
    @GetMapping("/adminAprroveStores")
    public String adminAprroveStores(HttpServletRequest request, Model mod) 
    {
        
        try
        {
        	//User temp = (User) request.getSession().getAttribute("user");
        	List<Store> stores = new ArrayList<Store>();
        	Iterable<Store> storesIterable = storeRepo.findAll();
        	for(Store store : storesIterable)
        	{
        		if(store.getStoreStatus().equals("0"))
        		{
        			stores.add(store);
        			
        			System.out.println("Store Name : " + store.getStoreName());
        		}
        	}
        	mod.addAttribute("stores", stores);
        	return "adminAprroveStores";
        }
        catch(Exception e)
        {
        	return "index";
        }
       
    }
    
    @PostMapping("/adminAprroveStores")
    public String adminAprroveStores(HttpServletRequest request, Model mod, @RequestParam String storeName) 
    {
    	if(storeName == null)
    	{
    		System.out.println("name is null");
    	}
    	else
    	{
    		 try
    	        {
    			 	System.out.println("name is : " + storeName);
    	    		Optional<Store> temp = storeRepo.findById(storeName);
    	    		temp.get().setStoreStatus("1");
    	    		storeRepo.save(temp.get());
    	        	List<Store> stores = new ArrayList<Store>();
    	        	Iterable<Store> storesIterable = storeRepo.findAll();
    	        	for(Store store : storesIterable)
    	        	{
    	        		if(store.getStoreStatus().equals("0"))
    	        		{
    	        			stores.add(store);
    	        			
    	        			System.out.println("Store Name : " + store.getStoreName());
    	        		}
    	        	}
    	        	mod.addAttribute("stores", stores);
    	        	return "adminAprroveStores";
    	        }
    	        catch(Exception e)
    	        {
    	        	return "index";
    	        }
    	       
    	}
    	
    	return "adminAprroveStores";
    }

    
}
