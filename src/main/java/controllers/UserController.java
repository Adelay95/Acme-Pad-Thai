package controllers;

import java.util.Collection;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import services.UserService;

import domain.SocialIdentity;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{
	
	// Services ---------------------------------------------------------------

			@Autowired
			private UserService userService;
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		String search="";
		Collection<User> users;
		users =userService.allUsers();
		
		result = new ModelAndView("user/list");
		result.addObject("requestURI", "user/list.do");
		result.addObject("users", users);
		result.addObject("search", search);
		return result;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView listSearchUser(@RequestParam String search,String searchs) {
		ModelAndView result;
		Collection<User> users;
		users =userService.usuariosNombre(search);
		
		result = new ModelAndView("user/list");
		result.addObject("requestURI", "user/search.do");
		result.addObject("users", users);
		result.addObject("search", search);
		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView displayUser(@RequestParam int userId) {
		ModelAndView result;
		
		User user;
		user =userService.findOne(userId);
		Collection<SocialIdentity> socialIdentities;
		socialIdentities=user.getSocialIdentities();
		Integer followers=user.getFollowed().size();
	
		result = new ModelAndView("user/display");
		result.addObject("requestURI", "user/display.do");
		result.addObject("user", user);
		result.addObject("followers", followers);
		result.addObject("socialIdentities", socialIdentities);
		return result;
	}
}
