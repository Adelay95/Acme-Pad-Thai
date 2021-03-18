  package controllers;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ContestService;
import domain.Contest;


@Controller
@RequestMapping("/contest")
public class ContestController {
	// Services ---------------------------------------------------------------

				@Autowired
				private ContestService contestService;
				@Autowired
				private AdministratorService administratorService;
		
		// Listing ----------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			
			Collection<Contest> contests;
			contests =contestService.allContest();
			
			result = new ModelAndView("contest/list");
			result.addObject("requestURI", "contest/list.do");
			result.addObject("contests", contests);
			return result;
		}
		
		@RequestMapping(value = "/administrator/set-winner-contest", method = RequestMethod.GET)
		public ModelAndView listW() {
			ModelAndView result=new ModelAndView("contest/list");
			Collection<Contest> contests;
			try{
			contests =contestService.getClosedContest();
			for(Contest c:contests){
				administratorService.calcularGanador(c.getId());
				result.addObject("requestURI", "contest/list.do");
				result.addObject("contests", contests);
			}
			}catch(Exception oops){
				result=list();
				result.addObject("message","message.commit.error");
			}
			
			return result;
		}
		@RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			
			Contest contest = contestService.create();
			result = createCreateModelAndView(contest);
			return result;
		}

		@RequestMapping(value= "/administrator/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int contestId){
			ModelAndView result;
			Contest contest;
			contest=contestService.findOne(contestId);
			Assert.notNull(contest);
		    result = createEditModelAndView(contest);
			return result;	
			
		}
		
		@RequestMapping(value="/administrator/edit", method = RequestMethod.POST,params="delete")
		public ModelAndView delete(Contest contest, BindingResult binding){
			ModelAndView result;
			
			try{
				administratorService.borrarContest(contest.getId());
				result=new ModelAndView("redirect:/contest/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(contest,"contest.commit.error");
			}
			return result;
		}
		
		@RequestMapping(value="/administrator/edit", method=RequestMethod.POST,params="save")
		public ModelAndView save(@Valid Contest contest, BindingResult binding){
			ModelAndView result;
			if(binding.hasErrors()){
				result= createEditModelAndView(contest);
			}else{
					try{
						administratorService.modificarContest(contest);
						result = new ModelAndView("redirect:/contest/list.do");}
					catch(Exception oops){
						result=createEditModelAndView(contest, "contest.commit.error");}
					
				}
				return result;
			
		}
		
		@RequestMapping(value="/administrator/create", method=RequestMethod.POST,params="save")
		public ModelAndView save2(@Valid Contest contest, BindingResult binding){
			ModelAndView result;
			if(binding.hasErrors()){
				result= createCreateModelAndView(contest);
			}else{
					try{
						contestService.save(contest);
						result = new ModelAndView("redirect:/contest/list.do");}
					catch(Exception oops){
						result=createCreateModelAndView(contest, "contest.commit.error");}
					
				}
				return result;
			
		}
		
		

		protected ModelAndView createCreateModelAndView(Contest contest) {
			ModelAndView result;
			
			result = createCreateModelAndView(contest, null);
			
			return result;
		}

		protected ModelAndView createCreateModelAndView(Contest contest,
				String message) {
			ModelAndView result;
			result = new ModelAndView("contest/create");
			result.addObject("contest",contest);
			result.addObject("requestURI", "contest/administrator/create.do");
			result.addObject("message",message);
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Contest contest){
			ModelAndView result;
			
			result = createEditModelAndView(contest, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Contest contest, String message){
			ModelAndView result;
			result = new ModelAndView("contest/edit");
			result.addObject("contest",contest);
			Date date =new Date();
			try{
			if(!contest.getQualifiedRecipes().isEmpty()){
			result.addObject("requestURI", "contest/administrator/edit.do?contesthasRecipesId="+ contest.getId());
			}else if(date.after(contest.getOpeningTime()) && date.before(contest.getClosingTime())){
				result.addObject("requestURI", "contest/administrator/edit.do?contestisActiveId="+ contest.getId());
			}else if(date.before(contest.getOpeningTime())){
				result.addObject("requestURI", "contest/administrator/edit.do?contestisNormalId="+ contest.getId());
			}else{
				result.addObject("requestURI", "contest/administrator/edit.do?contesthasRecipesId="+ contest.getId());
			}
			result.addObject("message",message);
			} catch (Exception oops){
				result = list();
				result.addObject("message","contest.commit.date.error");
			}
			return result;
			
			
		}
		
		
}

