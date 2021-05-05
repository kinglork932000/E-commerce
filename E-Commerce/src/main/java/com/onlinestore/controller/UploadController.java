package com.onlinestore.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	@Autowired
	ServletContext context;
	
	  @RequestMapping(value = "/uploadFile/{id}", method = RequestMethod.POST)
	  public String uploadFile(@RequestParam("file") MultipartFile multipartFile, Model model, @PathVariable("id") String id) {
	    model.addAttribute("message", "Upload success");
	    try {
	      String fileName = id + ".jpg";
	      File folderUpload = new File("src/main/upload");
	      File folderRealpath = new File(folderUpload.getAbsolutePath());
		    if (!folderRealpath.exists()) {
		      folderUpload.mkdirs();
		    }
		  System.out.println(folderRealpath.toString());
	      File file = new File(folderRealpath, fileName);
	      multipartFile.transferTo(file);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		return "redirect:/productList";
	  }
}
