package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Entity.Product;
import Entity.User;
import Repository.productRepository;
import Repository.userRepository;



@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class pagesController {
	
	@Autowired
	userRepository us;
	@Autowired
	productRepository pr;
	
	@GetMapping("/index")
    public String loginIn() {
        
		//System.out.println(name+"  "+pass);
        return "index";
    }
    @PostMapping("/index")
    public String loginIn(@RequestParam String name,@RequestParam String pass) {
    	Optional <User> op = us.findById(name);
    	System.out.println(op.get().getUserName() + "  " + op.get().getPassword());
        
    	if(op.get().getPassword().equals(pass) )
    	{
    		if(op.get().getType().equals("Customer"))
    		{
    			return "";
    		}
    		if(op.get().getType().equals("StoreOwner"))
    		{
    			return "";
    		}
    		if(op.get().getType().equals("Admin"))
    		{
    			System.out.println("admin");
    			return "adminHomePage?userEmail=" + name;
    		}
    		
    		//'members.php?do=Activate&userid=". $row['userId']
    	}
    	
    	return "index";
    }
    
    @GetMapping("/signUp")
    public String signUp() {
        
        //System.out.println(name+"  "+pass + "  " + email + "  " + type);
        return "signUp";
    }
    
    @PostMapping("/signUp")
    public String signUp(@RequestParam String name,@RequestParam String pass , @RequestParam String email, @RequestParam String type) {
        
        System.out.println(name+"  "+pass + "  " + email + "  " + type);
        User user = new User(name,pass,email,type);

        if(us.existsById(email) == true)
        {
        	System.out.println("i find it");
        }
        else
        {
        	us.save(user);
        }
        return "signUp";
    }
    
    
    
    @GetMapping("/adminAddProduct")
    public String adminAddProduct() {
        
        //System.out.println(name+"  "+pass + "  " + email + "  " + type);
        return "adminAddProduct";
    }
    
    @PostMapping("/adminAddProduct")
    public String adminAddProduct(@RequestParam String productName,@RequestParam String Code , @RequestParam String Type, @RequestParam String Description, @RequestParam String Brand , @RequestParam String Price)
    {    
        System.out.println(productName + "  " + Code + "  " + Type + "  " + Description + "  " + Brand + "  " + Price);
        Product prod = new Product(productName, Brand, Code, Description, Type, Price);

        if(pr.existsById(Code) == true)
        {
        	System.out.println("i find it");
        }
        
        else
        {
        	pr.save(prod);
        }
        
        return "adminAddProduct";
    }
    
    
    @GetMapping("/adminHomePage")
    public String adminHomePage() {
        
		//System.out.println(name+"  "+pass);
        return "adminHomePage";
    }
}


