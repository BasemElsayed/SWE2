package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Entity.Brand;
import Repository.brandRepository;

@Controller
@EnableJpaRepositories("Repository")
@EntityScan("Entity")
public class brandController {

	@Autowired
	brandRepository brandRepo;

	
	@GetMapping("/adminAddBrand")
    public String adminAddBrand() {
        
        //System.out.println(name+"  "+pass + "  " + email + "  " + type);
        return "adminAddBrand";
    }

    @PostMapping("/adminAddBrand")
    public String adminAddBrand(@RequestParam String brandName,@RequestParam String brandCategory ,@RequestParam String brandDescription, Model model)
    {    
        System.out.println(brandName + "  " + brandCategory + "  " + brandDescription);
        Brand brand = new Brand(brandName, brandCategory, brandDescription);

        if(brandRepo.existsById(brandName) == true)
        {
        	//System.out.println("i find it");
        	model.addAttribute("warning", true);
        }
        else
        {
        	brandRepo.save(brand);
        }
        
        return "adminAddBrand";
    }

    
    
	
}
