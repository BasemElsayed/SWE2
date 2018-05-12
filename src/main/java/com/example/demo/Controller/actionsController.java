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


@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class actionsController {

	@Autowired
	storeProductsRepository storeProdRepo;
	@Autowired
	storeOwnerActionsRepository storeActionRepo;
	
	

	@GetMapping("/storeOwnerActionsHistory")
    public String storeOwnerActionsHistory(HttpServletRequest request, Model mod) 
    {
        try
        {
        	User temp = (User) request.getSession().getAttribute("user");
        	List<storeOwnerActions> actions = new ArrayList<storeOwnerActions>();
        	Iterable<storeOwnerActions> actionsIterable = storeActionRepo.findAll();
        	for(storeOwnerActions action : actionsIterable)
        	{
        		if(action.getStore().getUser().getEmail().equals(temp.getEmail()))
        		{
        			actions.add(action);
        		}
        	}
        	mod.addAttribute("Actions", actions);
        	return "storeOwnerActionsHistory";
        }
        catch(Exception e)
        {
        	return "index";
        }  
    }
	
	
	
	@PostMapping("/storeOwnerActionsHistory")
    public String storeOwnerActionsHistory(HttpServletRequest request, Model mod, 
    		@RequestParam String actionName, @RequestParam String storeName, @RequestParam String productCode) 
    {
        try
        {
        	User temp = (User) request.getSession().getAttribute("user");
        	
        	
        	Iterable<storeOwnerActions> actionsIterable = storeActionRepo.findAll();
        	for(storeOwnerActions action : actionsIterable)
        	{
        		if(action.getStore().getUser().getEmail().equals(temp.getEmail()) && 
        				action.getStore().getStoreName().equals(storeName) && 
        				action.getProduct().getCode().equals(productCode) && 
        				action.getActionName().equals(actionName))
        		{
        			if(actionName.equals("Delete Product"))
        			{
        				StoreProduct storeProd = new StoreProduct(action.getProduct(), action.getStore(), action.getNumberOfProduct());
        				storeProd.setNumberOfSoldProduct(action.getNumberOfSoldProduct());
        				storeProd.setNumberOfVisitedProduct(action.getNumberOfVisitedProduct());
        				storeProdRepo.save(storeProd);
        				
        				storeActionRepo.deleteById(action.getId());
        			}
        			
        			else if(actionName.equals("Add Product"))
        			{
        				Iterable <StoreProduct> storeProdsIT = storeProdRepo.findAll();
            			for(StoreProduct storePro : storeProdsIT)
            			{
            				if(storePro.getProduct().getCode().equals(productCode) && storePro.getStore().getStoreName().equals(storeName))
            				{
            					storeProdRepo.deleteById(storePro.getId());
            				}
            			}
        				storeActionRepo.deleteById(action.getId());
        			}
        			
        			else if(actionName.equals("Edit Product"))
        			{
        				Iterable <StoreProduct> storeProdsIT = storeProdRepo.findAll();
            			for(StoreProduct storePro : storeProdsIT)
            			{
            				if(storePro.getProduct().getCode().equals(productCode) && storePro.getStore().getStoreName().equals(storeName))
            				{
            					storePro.setNumberOfProduct(action.getNumberOfProduct());
            					storeProdRepo.save(storePro);
            				}
            			}
            			storeActionRepo.deleteById(action.getId());
        			}
        		}
        	}
        	
        	
        	
        	List<storeOwnerActions> actions = new ArrayList<storeOwnerActions>();
        	actionsIterable = storeActionRepo.findAll();
        	for(storeOwnerActions action : actionsIterable)
        	{
        		if(action.getStore().getUser().getEmail().equals(temp.getEmail()))
        		{
        			actions.add(action);
        		}
        	}
        	mod.addAttribute("Actions", actions);
        	return "storeOwnerActionsHistory";
        }
        catch(Exception e)
        {
        	return "index";
        }
       
    }
	
	
	
	
	
	
}
