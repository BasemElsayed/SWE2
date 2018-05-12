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

import Entity.Product;
import Entity.StoreProduct;
import Entity.User;
import Repository.productRepository;
import Repository.storeProductsRepository;
import Repository.userRepository;

@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class customerController {
	
	@Autowired
	userRepository us;
	@Autowired
	productRepository prodRepo;
	@Autowired
	storeProductsRepository storeProdRepo;
	
	
	@GetMapping("/customerHomePage")
    public String customerHomePage(HttpServletRequest request, Model mod) {
    	
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
    		else if(temp.getType().equals("Customer"))
    		 {
    			 //System.out.println("equal Type");
    			//System.out.println("bara loop");
    			List<StoreProduct> Products = new ArrayList<StoreProduct>();
            	Iterable<StoreProduct> productIterable = storeProdRepo.findAll();
            	for(StoreProduct product : productIterable)
            	{
            		//System.out.println("gwa loop");
            		Products.add(product);			
        			//.out.println("Store Name : " + product.getProduct().getName());
            	}
            	mod.addAttribute("products", Products);
    			
    			 return "customerHomePage";
    		 }
    	}
		return "index";
    }
	
	
	@PostMapping("/customerHomePage")
    public String customerHomePage(HttpServletRequest request, Model mod, @RequestParam String prodCode, @RequestParam String storeName) {
    	
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
    		else if(temp.getType().equals("Customer"))
    		 {
    			 //System.out.println("equal Type");
    			Optional <Product> prod = prodRepo.findById(prodCode);
    			StoreProduct findProd = new StoreProduct();
    			
    			Iterable <StoreProduct> storeProdsIT = storeProdRepo.findAll();
    			
    			for(StoreProduct storePro : storeProdsIT)
    			{
    				if(prod.get().getCode().equals(storePro.getProduct().getCode()) && 
    						storePro.getStore().getStoreName().equals(storeName))
    				{
    					findProd = storePro;
    				}
    			}
    			
    			findProd.setNumberOfVisitedProduct(findProd.getNumberOfVisitedProduct()+1);
    			storeProdRepo.save(findProd);
    			
    			mod.addAttribute("products", findProd);
    			
    			return "customerShowProduct";
    		 }
    	}
		return "index";
    }
	

}
