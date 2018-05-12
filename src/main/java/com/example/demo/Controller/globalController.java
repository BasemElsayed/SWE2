package com.example.demo.Controller;


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

import Entity.*;
import Repository.*;



@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class globalController {

	
	@Autowired
	userRepository us;

	
	
	@GetMapping("/index")
    public String loginIn() {
        
		//System.out.println(name+"  "+pass);
        return "index";
    }
    @PostMapping("/index")
    public String loginIn(@RequestParam String name,@RequestParam String pass, Model model, HttpServletRequest request) {
    	
    	try
    	{
    		Optional <User> op = us.findById(name);
        	System.out.println(op.get().getUserName() + "  " + op.get().getPassword());
    		if(op.get().getPassword().equals(pass) )
        	{
    			request.getSession().setAttribute("user", op.get());   			
    			//session.getAttribute("ID");
    			//model.addAttribute("session",session.getValue(arg0));
        		if(op.get().getType().equals("Customer"))
        		{
        			return "customerHomePage";
        		}
        		if(op.get().getType().equals("StoreOwner"))
        		{
        			return "storeOwnerHomePage";
        		}
        		if(op.get().getType().equals("Admin"))
        		{
        			return "adminHomePage";
        		}
        	}
    		model.addAttribute("warning", true);
    		return "index";
    	}
    	catch(Exception e)
    	{
    		return "index";
    	}
	}
    
    @GetMapping("/signUp")
    public String signUp() {
        
        //System.out.println(name+"  "+pass + "  " + email + "  " + type);
        return "signUp";
    }
    
    @PostMapping("/signUp")
    public String signUp(@RequestParam String name,@RequestParam String pass , Model model, @RequestParam String email, @RequestParam String type) {
        
        System.out.println(name+"  "+pass + "  " + email + "  " + type);
        User user = new User(name,pass,email,type);

        if(us.existsById(email) == true)
        {
        	model.addAttribute("warning", true);
        }
        else
        {
        	us.save(user);
        }
        return "signUp";
    }
    
}


