package com.fileupload.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.fileupload.entity.User;
import com.fileupload.repository.UserRepository;
import com.fileupload.util.FileUploadUtil;

@Controller
public class UserController {
 
    @Autowired
    private UserRepository repo;
    
    
    public UserController(UserRepository repo) {
		super();
		this.repo = repo;
	}
@GetMapping("/list")
    public String listuser (Model m) {
		List<User> list=repo.findAll();
		m.addAttribute("list", list);
		return "list";
	}

@GetMapping("/new")
public String showform(User user)
{
	return "add";
}
     
    @PostMapping("/insert")
    public RedirectView saveUser(User user, @RequestParam("photo1") MultipartFile multipartFile) throws IOException  {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        
        if(fileName.length()> 3) {
            user.setPhotos(fileName);
             
            User savedUser = repo.save(user);

            String uploadDir = "user-photos/" + savedUser.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }
            else
            {
         	   User saveUser=repo.save(user);
            }
         
        return new RedirectView("/users", true);
    }
    
    @GetMapping("/users")
    public String insert() {
    	return "redirect:/list";
    }
    
    @GetMapping(value="/edit/{id}")
	public String editUser(@PathVariable int id, Model m)
	{
		Optional <User>user=repo.findById(id);
		User us = null;
		if(user.isPresent())
			us = user.get();
		
		m.addAttribute("user", us);
		return "edit";
			
	}
	
	@PostMapping(value="/update")
	public RedirectView updateUser( User user, @RequestParam("photo1") MultipartFile multipartFile) throws IOException  {
        
       String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
       if(fileName.length()> 3) {
       user.setPhotos(fileName);
        
       User savedUser = repo.save(user);

       String uploadDir = "user-photos/" + savedUser.getId();

       FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
       }
       else
       {
    	   User savedUser=repo.save(user);
       }
       return new RedirectView("/users", true);
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteUser(@PathVariable int id) 
	{
		repo.deleteById(id);
		return "redirect:/list";
	}
}