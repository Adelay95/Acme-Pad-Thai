package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ClientService;
import domain.Actor;
import domain.Client;


@Controller
@RequestMapping("/actor")
public class ActorController {
	
	@Autowired
	private ActorService actorService;
	@Autowired
	private ClientService clientService;

// Listing ----------------------------------------------------------------

            @RequestMapping(value = "/list", method = RequestMethod.GET)
            public ModelAndView list() {
              ModelAndView result;
             
              Collection<Actor> actors;
               actors =actorService.allActors();
                   result = new ModelAndView("actor/list");
                    result.addObject("requestURI", "actor/list.do");
                      result.addObject("actors", actors);
                        return result;
                           }
            
            
            @RequestMapping(value = "/listClients", method = RequestMethod.GET)
            public ModelAndView listC() {
              ModelAndView result;
              Collection<Client> clients;
              clients =clientService.allClients();
                 Client client=clientService.findByPrincipal();
                   result = new ModelAndView("actor/listClients");
                    result.addObject("requestURI", "actor/listClients.do");
                      result.addObject("clients", clients);
                      result.addObject("cliento", client);
                        return result;
                           }
            
            @RequestMapping(value="/follow", method=RequestMethod.GET)
            public ModelAndView follow(@RequestParam int clientId){
            	ModelAndView result;
            	try{
            	Client client=clientService.findOne(clientId);
            	Client client2=clientService.findByPrincipal();
            	clientService.seguirA(client2,client);
            	result=listC();
            	}catch(Exception oops){
            		result=listC();
            		result.addObject("message","actor.follow.error");
            	}
            	return result;
            	
            	
            }
            
            @RequestMapping(value="/unfollow", method=RequestMethod.GET)
            public ModelAndView unfollow(@RequestParam int clientId){
            	ModelAndView result;
            	try{
            	Client client=clientService.findOne(clientId);
            	Client client2=clientService.findByPrincipal();
            	clientService.dejarDeSeguirA(client2,client);
            	result=listC();
            	}catch(Exception oops){
            		result=listC();
            		result.addObject("message","actor.follow.error");
            	}
            	return result;
            	
            	
            }

}
