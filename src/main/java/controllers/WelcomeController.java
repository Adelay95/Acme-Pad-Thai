/* WelcomeController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import services.CampaignService;




@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {
	@Autowired
	private CampaignService campaignService;
	
	// Constructors -----------------------------------------------------------
	
	public WelcomeController() {
		super();
	}
		
	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		
		
		result = new ModelAndView("welcome/index");
		result.addObject("moment", moment);
		result.addObject("banner",campaignService.bannerWelcome());

		return result;
	}
}