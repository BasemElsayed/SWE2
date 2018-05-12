package com.example.demo.Controller;

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
import Repository.storeProductsRepository;
import Repository.storeRepository;
import Repository.userRepository;

@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class storeController {
	
	@Autowired
	userRepository us;
	@Autowired
	storeRepository storeRepo;
	@Autowired
	storeProductsRepository storeProdRepo;
	
	
	
	@GetMapping("/storeOwnerAddStore")
    public String storeOwnerAddStore() {
        
        //System.out.println(name+"  "+pass + "  " + email + "  " + type);
        return "storeOwnerAddStore";
    }

    @PostMapping("/storeOwnerAddStore")
    public String storeOwnerAddStore(@RequestParam String storeName,@RequestParam String storeAddress ,@RequestParam String storeCategory, @RequestParam String storeType, Model model, HttpServletRequest request)
    {    
        System.out.println(storeName + "  " + storeAddress + "  " + storeCategory + "  " + storeType);
        
        try {
        	
        	User temp = (User) request.getSession().getAttribute("user");
            System.out.println("Email : " + temp.getEmail());
            
            Store store = new Store(storeName, storeAddress, storeCategory, storeType, temp);
            
            if(storeRepo.existsById(storeName) == true)
            {
            	//System.out.println("i find it");
            	model.addAttribute("warning", true);
            }
            else
            {
            	storeRepo.save(store);
            }
        }
        catch(Exception e)
        {
        	return "index";
        }
                
        return "storeOwnerAddStore";
    }

}
