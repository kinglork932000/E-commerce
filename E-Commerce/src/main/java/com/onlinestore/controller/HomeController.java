package com.onlinestore.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.Category;
import com.onlinestore.domain.Product;
import com.onlinestore.domain.User;
import com.onlinestore.domain.security.PasswordResetToken;
import com.onlinestore.service.CategoryService;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserService;
import com.onlinestore.service.impl.UserSecurityService;
import com.onlinestore.utility.MailConstructor;
import com.onlinestore.utility.SecurityUtility;

@Controller
public class HomeController {
	@Autowired
	ServletContext context;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private	ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@RequestMapping({"/","/index","/home"})
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	boolean hasAdminRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    	if(hasAdminRole)
    		return "redirect:/adminPortal";
		List<Product> phones = productService.randomProduct("Phone", 8);
		List<Product> computers = productService.randomProduct("Computer", 8);
		List<Product> accessories = productService.randomProduct("Accessories", 8);
		List<Product> others = productService.randomProduct("Other", 8);
		model.addAttribute("phones", phones);
		model.addAttribute("computers", computers);
		model.addAttribute("accessories", accessories);
		model.addAttribute("others", others);
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		if(SecurityContextHolder.getContext().getAuthentication().getName()!="anonymousUser") {
			return "redirect:/myprofile";
		}
		return "account";
	}
	
	@RequestMapping("/login-error")
	public String loginerror(Model model) {
		if(SecurityContextHolder.getContext().getAuthentication().getName()!="anonymousUser") {
			return "redirect:/myprofile";
		}
		model.addAttribute("loginError", true);
		return "account";
	}
	
    @RequestMapping("/success")
    public String loginPageRedirect(HttpServletRequest request, Principal principal) throws IOException, ServletException {

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	boolean hasAdminRole = authentication.getAuthorities().stream()
    	          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if(hasAdminRole){
        	return "redirect:/adminPortal";                            
         }
         else {
             return "redirect:/";
         }
    }
    
    @RequestMapping("/adminPortal")
    public String adminPortal() {
    	return "adminPortal";
    }
    
    @RequestMapping(value = "/addproduct", method = RequestMethod.GET)
    public String addProduct(Model model) {
    	List<Category> categoryList = categoryService.findAll();
    	model.addAttribute("categoryList", categoryList);
    	return "addProduct";
    }
	
    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    public String addProductPost(
    		@ModelAttribute("product") Product product,
    		@ModelAttribute("categoryID") String categoryID,
    		HttpServletRequest request
    		) {
    	product.setCategory(categoryService.findByCategoryID(categoryID));
    	productService.save(product);
    	return "redirect:productList";
    }
    
	/*
	 * @RequestMapping("/productList") public String productList(Model model) {
	 * List<Product> productList = productService.findAll();
	 * model.addAttribute("productList", productList); return "productList"; }
	 */
    
    @RequestMapping("/productList")
    public String productList(
    		Model model,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@ModelAttribute("sorttype") String sorttype
    		) {
    	String optionActiveSort;
		Pageable pageRequest;
		if (sorttype.equalsIgnoreCase("3")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("price").descending());
			optionActiveSort = "t3";
		}else if (sorttype.equalsIgnoreCase("2")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("price").ascending());
			optionActiveSort = "t2";
		}else if (sorttype.equalsIgnoreCase("4")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("product_name").ascending());
			optionActiveSort = "t4";
		}else if (sorttype.equalsIgnoreCase("5")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("product_name").descending());
			optionActiveSort = "t5";
		}else {
			pageRequest = PageRequest.of(page, 12);
			optionActiveSort = "t1";
		}
		model.addAttribute(optionActiveSort, true);
		
		Page<Product> productList = productService.findPaginated(pageRequest);
		int totalPage = productList.getTotalPages();
		List<Integer> pages = new ArrayList<Integer>();
		if (totalPage==0) {
			pages.add(0);
		}else {
		for (int i = 0; i <totalPage ; i++) {
			pages.add(i);
			}
		}
		/* List<Product> productList = productService.findPaginated(pageable); */
		model.addAttribute("productList", productList);
		model.addAttribute("pages", pages);
		model.addAttribute("totalpage", totalPage-1);
		model.addAttribute("curpage", page);
		
    	return "productList";
    }
    
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String newUserPost(HttpServletRequest request,
			@ModelAttribute("email") String userEmail,
			@ModelAttribute("username") String username,
			@ModelAttribute("password") String password,
			Model model) throws Exception {
		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);
		
		if (userService.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);
			return "account";
		}
		
		if (userService.findByEmail(userEmail) != null) {
			model.addAttribute("emailExists", true);
			return "account";
		}
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(userEmail);
		String encryptedpassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedpassword);
		
		userService.createUser(user);
		UserDetails userDetails = userSecurityService.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/myprofile";
	}

	@RequestMapping("/myprofile")
	public String myprofile(Model model, Principal principal) {
		User user = new User();
        String username = principal.getName();
        user = userService.findByUsername(username);
        model.addAttribute(user);
		return "myProfile";
	}
	
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public String newUserPost(HttpServletRequest request,
			@ModelAttribute("user") User userUpdate,
			@ModelAttribute("currentPassword") String curPass,
			RedirectAttributes redirectAttribute,
			Model model, Principal principal) throws Exception {
		User user = userService.findByUsername(principal.getName());
		BCryptPasswordEncoder encoded = new BCryptPasswordEncoder();
		boolean matches = encoded.matches(curPass, user.getPassword());
		if(!matches) {
			redirectAttribute.addFlashAttribute("wrongPassword", true);
			return "redirect:/myprofile";
		}
		if(user.getEmail().compareTo(userUpdate.getEmail()) != 0) {
			if (userService.findByEmail(userUpdate.getEmail()) != null) {
				redirectAttribute.addFlashAttribute("emailExists", true);
				return "redirect:/myprofile";
			}
		}
		userService.updateInfo(user, userUpdate);
		return "redirect:/myprofile";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/newUser")
	public String newUser(Locale locale,
			@RequestParam("token") String token,
			Model model)	 {
		
		PasswordResetToken passToken = userService.getPasswordResetToken(token);
		
		if(passToken == null) {
			String message = "Invalid Token.";
			model.addAttribute("message", message);
			return "redirect:/badRequest";
		}
		
		User user = passToken.getUser();
		String username = user.getUsername();
		
		UserDetails userDetails = userSecurityService.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		model.addAttribute("user",user);
		
		return "myProfile";
	}
	
	@RequestMapping("/resetPassword")
	public String resetPassword(
			HttpServletRequest request,
			@ModelAttribute("email") String email,
			@ModelAttribute("username") String username,
			Model model,
			RedirectAttributes redirectAttribute
			) {
		User user = userService.findByUsername(username);
		
		if (user == null) {
			redirectAttribute.addFlashAttribute("emailNotExist", true);
			
			return "redirect:/login";
		}
		if (user.getEmail().compareTo(email)!=0) {
			redirectAttribute.addFlashAttribute("emailNotExist", true);
			
			return "redirect:/login";
		}
		
		String password =  SecurityUtility.randomPassword();
		String encryptedpassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedpassword);
		
		userService.save(user);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		mailSender.send(newEmail);
		
		redirectAttribute.addFlashAttribute("forgetPasswordEmailSent","true");
		
		
		return "redirect:/login";
	}
	
	@RequestMapping("/changePassword")
	public String changePw(
			@ModelAttribute("oldPassword") String oldpassword,
			@ModelAttribute("newPassword") String newpassword,
			RedirectAttributes redirectAttribute,
			Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		System.out.println(oldpassword);
		BCryptPasswordEncoder encoded = new BCryptPasswordEncoder();
		boolean matches = encoded.matches(oldpassword, user.getPassword());
		if(!matches) {
			redirectAttribute.addFlashAttribute("passwordIncorrect", true);
			return "redirect:/myprofile";
		}
		String encryptedpassword = SecurityUtility.passwordEncoder().encode(newpassword);
		user.setPassword(encryptedpassword);
		userService.save(user);
		redirectAttribute.addFlashAttribute("passwordChanged", true);
		return "redirect:/myprofile";
	}
}
