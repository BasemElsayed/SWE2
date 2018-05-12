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
import Entity.Store;
import Entity.StoreProduct;
import Entity.User;
import Entity.firstPayment;
import Entity.storeOwnerActions;
import Repository.brandRepository;
import Repository.firstPaymentRepository;
import Repository.productRepository;
import Repository.storeOwnerActionsRepository;
import Repository.storeProductsRepository;
import Repository.storeRepository;
import Repository.userRepository;

@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class productController {
	@Autowired
	userRepository us;
	@Autowired
	productRepository pr;
	@Autowired
	brandRepository brandRepo;
	@Autowired
	productRepository prodRepo;
	@Autowired
	storeRepository storeRepo;
	@Autowired
	storeProductsRepository storeProdRepo;
	@Autowired
	storeOwnerActionsRepository storeActionRepo;
	@Autowired
	firstPaymentRepository firstPayRepo;
	
	
	
	@GetMapping("/adminAddProduct")
    public String adminAddProduct() {
        
        //System.out.println(name+"  "+pass + "  " + email + "  " + type);
        return "adminAddProduct";
    }

    @PostMapping("/adminAddProduct")
    public String adminAddProduct(@RequestParam String productName,@RequestParam String Code , @RequestParam String Type, @RequestParam String Description, @RequestParam String Brand , @RequestParam String Price, Model model)
    {    
        System.out.println(productName + "  " + Code + "  " + Type + "  " + Description + "  " + Brand + "  " + Price);
        Product prod = new Product(productName, Brand, Code, Description, Type, Price);

        if(pr.existsById(Code) == true)
        {
        	model.addAttribute("warningSameProduct", true);
        }
        else
        {
        	if(brandRepo.existsById(Brand))
        	{
        		pr.save(prod);
        	}
        	else
        	{
        		//System.out.println("Not Found Brand");
        		model.addAttribute("warningBrand", true);
        	}
        }
        
        return "adminAddProduct";
    }

    
    
    @GetMapping("/storeOwnerAddProduct")
    public String storeOwnerAddProduct(HttpServletRequest request, Model mod) 
    {
        try
        {
        	//User temp = (User) request.getSession().getAttribute("user");
        	List<Product> Products = new ArrayList<Product>();
        	Iterable<Product> productIterable = prodRepo.findAll();
        	for(Product product : productIterable)
        	{
        		Products.add(product);			
    			System.out.println("Store Name : " + product.getName());
        	}
        	mod.addAttribute("products", Products);
        	return "storeOwnerAddProduct";
        }
        catch(Exception e)
        {
        	return "index";
        }
       
    }
	
	
	
	 @PostMapping("/storeOwnerAddProduct")
	    public String storeOwnerAddProduct(HttpServletRequest request, Model mod, @RequestParam String prodCode, @RequestParam String storeName, @RequestParam int quantity) 
	    {
		 
		 try
            {
            	User tempUser = (User) request.getSession().getAttribute("user");
    			Optional<Product> tempProduct = prodRepo.findById(prodCode);
    			
    			
    			
    			Store storeOwnerStore = null;
    			
    			Iterable<Store> storeIterables = storeRepo.findAll();
    			for(Store store : storeIterables)
    			{
    				if( (store.getUser().getEmail().equals(tempUser.getEmail())) && (store.getStoreName().equals(storeName)) )
    				{
    					storeOwnerStore = store;
    				}
    			}
    			
    			
    			
    			if(storeOwnerStore != null)
    			{
    				StoreProduct storProduct = new StoreProduct(tempProduct.get(), storeOwnerStore, quantity);
    				storeProdRepo.save(storProduct);
    				
    				storeOwnerActions addProdAction = new storeOwnerActions("Add Product", quantity, 0,
    						0, tempProduct.get(), storeOwnerStore);
    				storeActionRepo.save(addProdAction);
    				
    			}
    			else
    			{
    				System.out.println("Not Founded");	
    				mod.addAttribute("warning", true);
    			}
    			
    			
    			List<Product> Products = new ArrayList<Product>();
            	Iterable<Product> productIterable = prodRepo.findAll();
            	for(Product product : productIterable)
            	{
            		Products.add(product);			
        			System.out.println("Store Name : " + product.getName());
            	}
            	mod.addAttribute("products", Products);
            	return "storeOwnerAddProduct";
            }
            catch(Exception e)
            {
            	return "index";
            }
	    }

    
    
	@PostMapping("/customerShowProduct")
    public String customerShowProduct(HttpServletRequest request, Model mod, @RequestParam String prodCode, 
    		@RequestParam String storeName, @RequestParam String address, @RequestParam String phone, @RequestParam int quantity) {
    	
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
    			Optional <Product> prod = prodRepo.findById(prodCode);
    			
    			Iterable <StoreProduct> storeProdsIT = storeProdRepo.findAll();
    			StoreProduct findProd = new StoreProduct();
    			
    			for(StoreProduct storePro : storeProdsIT)
    			{
    				if(prod.get().getCode().equals(storePro.getProduct().getCode()) && 
    						storePro.getStore().getStoreName().equals(storeName))
    				{
    					findProd = storePro;
    				}
    			}
    			
    			
    			
    			if(findProd.getNumberOfProduct() > 0 && findProd.getNumberOfProduct() >= quantity && quantity > 0)
    			{
    				System.out.println("Quantity : " + quantity);
    				double fatora = (quantity * Double.parseDouble(findProd.getProduct().getPrice()));
    				fatora -= (fatora * (findProd.getDiscount()/100.0));
    				System.out.println("fatora : " + fatora);
    				System.out.println("Balance : " + temp.getBalance());
    				if(fatora <= temp.getBalance())
    				{
    					findProd.setNumberOfSoldProduct(findProd.getNumberOfSoldProduct() + quantity);
        				findProd.setNumberOfProduct(findProd.getNumberOfProduct() - quantity);
        				storeProdRepo.save(findProd);
        				
        				if(quantity < 2)
        				{
        					fatora = Double.parseDouble(findProd.getProduct().getPrice());
        				}
        				
            			temp.setBalance(temp.getBalance() - fatora);
            			us.save(temp);
            			
            			findProd.getStore().getUser().setBalance(findProd.getStore().getUser().getBalance() + fatora);
            			us.save(findProd.getStore().getUser());
            			
            			mod.addAttribute("products", findProd);
        				mod.addAttribute("warningSucc", true);
    				}
    				else
    				{
    					mod.addAttribute("products", findProd);
    					mod.addAttribute("warning", true);
    				}
    			}
    			else
    			{ 
    				System.out.println("Elseeeee : " + quantity);
    				mod.addAttribute("products", findProd);
    				mod.addAttribute("warning", true);
    			}
    			return "customerShowProduct";
    		 }
    	}
		return "index";
    }
	
	
	
	
	
	//Buy Product
    @GetMapping("/storeOwnerBuyProduct")
    public String storeOwnerBuyProduct(HttpServletRequest request, Model mod) {
    	
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
    			
    			 return "storeOwnerBuyProduct";
    		 }
    	}
		return "index";
    }
	
	
	@PostMapping("/storeOwnerBuyProduct")
    public String storeOwnerBuyProduct(HttpServletRequest request, Model mod, @RequestParam String prodCode, @RequestParam String storeName) {
    	
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
    			
    			return "storeOwnerShowProduct";
    		 }
    	}
		return "index";
    }
	
	
	
	
	
	@PostMapping("/storeOwnerShowProduct")
    public String storeOwnerShowProduct(HttpServletRequest request, Model mod, @RequestParam String prodCode, 
    		@RequestParam String storeName, @RequestParam String address, @RequestParam String phone, @RequestParam int quantity) {
    	
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
    			Optional <Product> prod = prodRepo.findById(prodCode);
    			
    			Iterable <StoreProduct> storeProdsIT = storeProdRepo.findAll();
    			StoreProduct findProd = new StoreProduct();
    			
    			for(StoreProduct storePro : storeProdsIT)
    			{
    				if(prod.get().getCode().equals(storePro.getProduct().getCode()) && 
    						storePro.getStore().getStoreName().equals(storeName))
    				{
    					findProd = storePro;
    				}
    			}
    			
    			
    			
    			if(findProd.getNumberOfProduct() > 0 && findProd.getNumberOfProduct() >= quantity && quantity > 0)
    			{
    				System.out.println("Quantity : " + quantity);
    				double fatora = (quantity * Double.parseDouble(findProd.getProduct().getPrice()));
    				fatora -= (fatora * (findProd.getDiscount()/100.0));
    				System.out.println("fatora : " + fatora);
    				System.out.println("Balance : " + temp.getBalance());
    				if(fatora <= temp.getBalance())
    				{
    					findProd.setNumberOfSoldProduct(findProd.getNumberOfSoldProduct() + quantity);
        				findProd.setNumberOfProduct(findProd.getNumberOfProduct() - quantity);
        				storeProdRepo.save(findProd);
        				
        				
        				Iterable <firstPayment> firstPayments = firstPayRepo.findAll();
        				firstPayment findFirstPayment = new firstPayment();
        				for(firstPayment firstPay : firstPayments)
        				{
        					if(firstPay.getUser().getEmail().equals(temp.getEmail()) && firstPay.getStore().getStoreName().equals(findProd.getStore().getStoreName()))
        					{
        						findFirstPayment = firstPay;
        					}
        				}
        				
        				if(quantity < 2)
        				{
        					fatora = Double.parseDouble(findProd.getProduct().getPrice());
        				}
        				
        				
        				if(findFirstPayment.getId() == 0)
        				{
        					fatora -= (fatora * (5/100.0));
        					firstPayment First = new firstPayment(findProd.getStore(), temp);
        					firstPayRepo.save(First);
        				}
        				
        				
            			temp.setBalance(temp.getBalance() - fatora);
            			us.save(temp);
            			
            			findProd.getStore().getUser().setBalance(findProd.getStore().getUser().getBalance() + fatora);
            			us.save(findProd.getStore().getUser());
            			
            			mod.addAttribute("products", findProd);
        				mod.addAttribute("warningSucc", true);
    				}
    				else
    				{
    					mod.addAttribute("products", findProd);
    					mod.addAttribute("warning", true);
    				}
    			}
    			else
    			{ 
    				System.out.println("Elseeeee : " + quantity);
    				mod.addAttribute("products", findProd);
    				mod.addAttribute("warning", true);
    			}
    			return "storeOwnerShowProduct";
    		 }
    	}
		return "index";
    }
	
    
    
    //Edit Product
	@GetMapping("/storeOwnerEditProduct")
    public String storeOwnerEditProduct(HttpServletRequest request, Model mod) {
    	
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
    			
    			 return "storeOwnerEditProduct";
    		 }
    	}
		return "index";
    }
	
	
	@PostMapping("/storeOwnerEditProduct")
    public String storeOwnerEditProduct(HttpServletRequest request, Model mod, @RequestParam String prodCode, @RequestParam String storeName) {
    	
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
    			
    			return "storeOwnerShowProductEditVersion";
    		 }
    	}
		return "index";
    }
    
	
	@PostMapping("/storeOwnerShowProductEditVersion")
    public String storeOwnerShowProductEditVersion(HttpServletRequest request, Model mod, @RequestParam String prodCode, 
    		@RequestParam String storeName, @RequestParam int discount, @RequestParam int numberOfProduct) {
    	
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
				Optional <Product> prod = prodRepo.findById(prodCode);
				
				Iterable <StoreProduct> storeProdsIT = storeProdRepo.findAll();
				StoreProduct findProd = new StoreProduct();
				
				for(StoreProduct storePro : storeProdsIT)
				{
					if(prod.get().getCode().equals(storePro.getProduct().getCode()) && 
							storePro.getStore().getStoreName().equals(storeName))
					{
						findProd = storePro;
					}
				}
				
				storeOwnerActions action = new storeOwnerActions("Edit Product", findProd.getNumberOfProduct(), findProd.getNumberOfSoldProduct(),
						findProd.getNumberOfSoldProduct(), findProd.getProduct(), findProd.getStore());
				
				storeActionRepo.save(action);
				
				findProd.setDiscount(discount);
				findProd.setNumberOfProduct(numberOfProduct);
				
				storeProdRepo.save(findProd);
				
				mod.addAttribute("products", findProd);
				return "storeOwnerShowProductEditVersion";
    		 }
    		return "index";
    	}
    }

	
	
	
	
}
