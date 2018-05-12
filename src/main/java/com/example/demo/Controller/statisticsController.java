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

import Entity.Statistics;
import Entity.Store;
import Entity.StoreProduct;
import Entity.User;
import Repository.statisticsRepository;
import Repository.storeProductsRepository;
import Repository.storeRepository;
import Repository.userRepository;

@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class statisticsController {
	
	@Autowired
	userRepository userRepo;
	@Autowired
	storeRepository storeRepo;
	@Autowired
	storeProductsRepository storeProdRepo;
	@Autowired
	statisticsRepository statsRepo;
	
	
	
	@GetMapping("/adminApproveStatistics")
    public String adminApproveStatistics(Model mod) 
    {
        try
        {
        	List<Statistics> statistics = new ArrayList<Statistics>();
        	Iterable<Statistics> statsIterable = statsRepo.findAll();
        	for(Statistics stat : statsIterable)
        	{
        		statistics.add(stat);
        	}
        	mod.addAttribute("statics", statistics);
        	return "adminApproveStatistics";
        }
        catch(Exception e)
        {
        	return "index";
        }
    }
	
	@PostMapping("/adminApproveStatistics")
    public String adminApproveStatistics(Model mod, @RequestParam String statName, @RequestParam int statCheck) 
    {
        try
        {
        	if(statCheck == 0 || statCheck == 1)
        	{

            	Optional <Statistics> updatedStat = statsRepo.findById(statName);
            	updatedStat.get().setStatCheck(statCheck);
            	statsRepo.save(updatedStat.get());
            	
            	List <Statistics> statistics = new ArrayList<Statistics>();
            	Iterable <Statistics> statsIterable = statsRepo.findAll();
            	for(Statistics stat : statsIterable)
            	{
            		statistics.add(stat);
            	}
            	mod.addAttribute("statics", statistics);
            	return "adminApproveStatistics"; 
        	}
        	else
        	{
        		return "index";
        	}
	    }
        catch(Exception e)
        {
        	return "index";
        }
    }
	
	
	
	
	
	
	@GetMapping("/storeOwnerProductsStatistics")
    public String storeOwnerProductsStatistics(Model mod) 
    {
        try
        {
        	if(statsRepo.findById("Products").get().getStatCheck() == 1)
        	{

            	
            	Iterable<StoreProduct> prodsIterable = storeProdRepo.findAll();
            	
            	int MaxProdNumberVisited = 0;
            	StoreProduct MaxProdVisited = new StoreProduct();
            	
            	int MaxProdNumberSold = 0;
            	StoreProduct MaxProdSold = new StoreProduct();
            	
            	
            	int MinProdNumberVisited = 2147483647;
            	StoreProduct MinProdVisited = new StoreProduct();
            	
            	int MinProdNumberSold = 2147483647;
            	StoreProduct MinProdSold = new StoreProduct();
            	
            	
            	for(StoreProduct prod : prodsIterable)
            	{
            		if(prod.getNumberOfVisitedProduct() > MaxProdNumberVisited)
            		{
            			MaxProdNumberVisited = prod.getNumberOfVisitedProduct();
            			MaxProdVisited = prod;
            		}
            		if(prod.getNumberOfSoldProduct() > MaxProdNumberSold)
            		{
            			MaxProdNumberSold = prod.getNumberOfSoldProduct();
            			MaxProdSold = prod;
            		}
            		if(prod.getNumberOfVisitedProduct() <= MinProdNumberVisited)
            		{
            			MinProdNumberVisited = prod.getNumberOfVisitedProduct();
            			MinProdVisited = prod;
            		}
            		if(prod.getNumberOfSoldProduct() <= MinProdNumberSold)
            		{
            			MinProdNumberSold = prod.getNumberOfSoldProduct();
            			MinProdSold = prod;
            		}
            	}
            	mod.addAttribute("maxProdVisited", MaxProdVisited);
            	mod.addAttribute("maxProdSold", MaxProdSold);
            	mod.addAttribute("minProdVisited", MinProdVisited);
            	mod.addAttribute("minProdSold", MinProdSold);
            	
            	return "storeOwnerProductsStatistics";
        	}
        	else
        	{
        		return "storeOwnerAccessStat";
        	}
        }
        catch(Exception e)
        {
        	return "index";
        }
    }
	
	
	@GetMapping("/storeOwnerUsersStatistics")
    public String storeOwnerUsersStatistics(Model mod) 
    {
        try
        {
        	if(statsRepo.findById("Users").get().getStatCheck() == 1)
        	{

        		int numberOfCustomers = 0, numberOfStoreOwners = 0;

            	Iterable<User> usersIterable = userRepo.findAll();
            	for(User user : usersIterable)
            	{
            		if(user.getType().equals("Customer"))
            		{
            			numberOfCustomers++;
            		}
            		if(user.getType().equals("StoreOwner"))
            		{
            			numberOfStoreOwners++;
            		}
            	}
            	
            	
            	
        		int numberOfApproveStores = 0, numberOfUnApproveStores = 0;

            	Iterable<Store> storesIterable = storeRepo.findAll();
            	for(Store store : storesIterable)
            	{
            		if(store.getStoreStatus().equals("0"))
            		{
            			numberOfUnApproveStores++;
            		}
            		if(store.getStoreStatus().equals("1"))
            		{
            			numberOfApproveStores++;
            		}
            	}
        		
            	
            	mod.addAttribute("numOfCustomers", numberOfCustomers);
            	mod.addAttribute("numberOfStoreOwners", numberOfStoreOwners);
            	mod.addAttribute("numberOfApproveStores", numberOfApproveStores);
            	mod.addAttribute("numberOfUnApproveStores", numberOfUnApproveStores);
            	
            	return "storeOwnerUsersStatistics";
        	}
        	else
        	{
        		return "storeOwnerAccessStat";
        	}
        }
        catch(Exception e)
        {
        	return "index";
        }
    }
	
	
	
	
	
	@GetMapping("/customerProductsStatistics")
    public String customerProductsStatistics(Model mod) 
    {
        try
        {
        	if(statsRepo.findById("Products").get().getStatCheck() == 1)
        	{

            	
            	Iterable<StoreProduct> prodsIterable = storeProdRepo.findAll();
            	
            	int MaxProdNumberVisited = 0;
            	StoreProduct MaxProdVisited = new StoreProduct();
            	
            	int MaxProdNumberSold = 0;
            	StoreProduct MaxProdSold = new StoreProduct();
            	
            	
            	int MinProdNumberVisited = 2147483647;
            	StoreProduct MinProdVisited = new StoreProduct();
            	
            	int MinProdNumberSold = 2147483647;
            	StoreProduct MinProdSold = new StoreProduct();
            	
            	
            	for(StoreProduct prod : prodsIterable)
            	{
            		if(prod.getNumberOfVisitedProduct() > MaxProdNumberVisited)
            		{
            			MaxProdNumberVisited = prod.getNumberOfVisitedProduct();
            			MaxProdVisited = prod;
            		}
            		if(prod.getNumberOfSoldProduct() > MaxProdNumberSold)
            		{
            			MaxProdNumberSold = prod.getNumberOfSoldProduct();
            			MaxProdSold = prod;
            		}
            		if(prod.getNumberOfVisitedProduct() <= MinProdNumberVisited)
            		{
            			MinProdNumberVisited = prod.getNumberOfVisitedProduct();
            			MinProdVisited = prod;
            		}
            		if(prod.getNumberOfSoldProduct() <= MinProdNumberSold)
            		{
            			MinProdNumberSold = prod.getNumberOfSoldProduct();
            			MinProdSold = prod;
            		}
            	}
            	mod.addAttribute("maxProdVisited", MaxProdVisited);
            	mod.addAttribute("maxProdSold", MaxProdSold);
            	mod.addAttribute("minProdVisited", MinProdVisited);
            	mod.addAttribute("minProdSold", MinProdSold);
            	
            	return "customerProductsStatistics";
        	}
        	else
        	{
        		return "customerAccessStat";
        	}
        }
        catch(Exception e)
        {
        	return "index";
        }
    }
	
	
	
	@GetMapping("/customerUsersStatistics")
    public String customerUsersStatistics(Model mod) 
    {
        try
        {
        	if(statsRepo.findById("Users").get().getStatCheck() == 1)
        	{

        		int numberOfCustomers = 0, numberOfStoreOwners = 0;

            	Iterable<User> usersIterable = userRepo.findAll();
            	for(User user : usersIterable)
            	{
            		if(user.getType().equals("Customer"))
            		{
            			numberOfCustomers++;
            		}
            		if(user.getType().equals("StoreOwner"))
            		{
            			numberOfStoreOwners++;
            		}
            	}
            	
            	
            	
        		int numberOfApproveStores = 0, numberOfUnApproveStores = 0;

            	Iterable<Store> storesIterable = storeRepo.findAll();
            	for(Store store : storesIterable)
            	{
            		if(store.getStoreStatus().equals("0"))
            		{
            			numberOfUnApproveStores++;
            		}
            		if(store.getStoreStatus().equals("1"))
            		{
            			numberOfApproveStores++;
            		}
            	}
            	mod.addAttribute("numOfCustomers", numberOfCustomers);
            	mod.addAttribute("numberOfStoreOwners", numberOfStoreOwners);
            	mod.addAttribute("numberOfApproveStores", numberOfApproveStores);
            	mod.addAttribute("numberOfUnApproveStores", numberOfUnApproveStores);
            	
            	return "customerUsersStatistics";
        	}
        	else
        	{
        		return "customerAccessStat";
        	}
        }
        catch(Exception e)
        {
        	return "index";
        }
    }
	
	
	@GetMapping("/storeOwnerShowStat")
    public String storeOwnerShowStat(HttpServletRequest request, Model mod) 
    {    
    	try {
        	
        	User temp = (User) request.getSession().getAttribute("user");
            System.out.println("Email : " + temp.getEmail());

            if(temp.getType().equals("StoreOwner"))
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
				
				return "storeOwnerShowStat";
			 }
            else
            {
            	return "index";
            }
                        
        }
        catch(Exception e)
        {
        	return "index";
        }
    }

	
	
}
