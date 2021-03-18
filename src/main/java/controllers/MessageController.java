package controllers;

import java.util.Collection;

import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import services.MessageService;
import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.Priority;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController{
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private FolderService folderService;
	
	public MessageController(){
		super();
	}
	
	@RequestMapping(value = "/move", method = RequestMethod.GET)
	public ModelAndView move(@RequestParam int messageId) {
		ModelAndView result;
		Collection<Folder> folders;
		Actor actor=actorService.findByPrincipal();
		folders=actor.getFolders();
		result= new ModelAndView("folder/list");
		result.addObject("folders",folders);
		result.addObject("messageId",messageId);
		result.addObject("requestURI","message/move.do");

		return result;
	}
	
	@RequestMapping(value="/defMov", method = RequestMethod.GET)
	public ModelAndView moverDef(@RequestParam int folderId,@RequestParam int messageId){
		ModelAndView result;
		Message message=messageService.findOne(messageId);
		Folder folder=folderService.findOne(folderId);
		try{
			actorService.moveMessage(message, folder);
			result=new ModelAndView("redirect:/folder/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(message,"message.commit.error");
		}
		
		return result;
	}
	
	
	
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam  int folderId) {
		ModelAndView result;
		Collection<Message> messages;
		Folder folder=folderService.findOne(folderId);
		messages=folder.getMessages();
		result= new ModelAndView("message/list");
		result.addObject("messages",messages);
		result.addObject("requestURI","message/list.do");
		
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int actorId){
		ModelAndView result;
		Actor actor1,actor2;
		actor1=actorService.findOne(actorId);
		actor2=actorService.findByPrincipal();
		Assert.notNull(actor1);
		Assert.notNull(actor2);
		Message message=messageService.create(actor2, actor1);
	    result = createEditModelAndView(message);
		return result;
		
		
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int messageId){
		ModelAndView result;
		Message message=messageService.findOne(messageId);
		try{
			actorService.deleteMessage(message);
			result=new ModelAndView("redirect:/folder/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(message,"message.commit.error");
		}
		
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@ModelAttribute("messager")@Valid Message message, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createEditModelAndView(message);
		}else{
				try{
				     actorService.sendMessage(message);
					result = new ModelAndView("redirect:/");}
				catch(Exception oops){
					result=createEditModelAndView(message, "message.commit.error");}
				
			}
		
			return result;
		
	}
	
	protected ModelAndView createEditModelAndView(Message message){
		ModelAndView result;
		
		result = createEditModelAndView(message, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Message messager, String message){
		ModelAndView result;
		result = new ModelAndView("message/edit");
		Collection<String> priorities=new HashSet<String>();
	     for(Priority p:Priority.values()){
	      priorities.add(p.toString());
	     }
		result.addObject("messager",messager);
		result.addObject("requestURI", "message/edit.do");
		result.addObject("message",message);
		result.addObject("priorities", priorities);
		return result;
		
		
	}
}
	