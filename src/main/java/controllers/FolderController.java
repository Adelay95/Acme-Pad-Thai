package controllers;

import java.util.Collection;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import domain.Actor;
import domain.Folder;

@Controller
@RequestMapping("/folder")
public class FolderController extends AbstractController{
	
	@Autowired
	private FolderService folderService;
	@Autowired
	private ActorService actorService;
	
	public FolderController(){
		super();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Actor actor=actorService.findByPrincipal();
		Folder folder = folderService.create(actor);
		result = createEditModelAndView(folder);

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Folder> folders;
		Actor actor=actorService.findByPrincipal();
		folders=actor.getFolders();
		result= new ModelAndView("folder/list");
		result.addObject("folders",folders);
		result.addObject("requestURI","folder/list.do");
		
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int folderId){
		ModelAndView result;
		Folder folder;
		folder=folderService.findOne(folderId);
		Assert.notNull(folder);
	    result = createEditModelAndView(folder);
		return result;
		
		
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(Folder folder, BindingResult binding){
		ModelAndView result;
		if(folder.getName().equals("INBOX") || folder.getName().equals("OUTBOX") || folder.getName().equals("TRASHBOX")
				|| folder.getName().equals("SPAMBOX")){
			result=createEditModelAndView(folder, "folder.commit.error");
		}else{
		try{
			if(folder.getId()!=0){
				Folder folder2=folderService.findOne(folder.getId());
				Assert.isTrue(!folder2.getName().equals("INBOX") && !folder2.getName().equals("OUTBOX") && !folder2.getName().equals("TRASHBOX")
						&& !folder2.getName().equals("SPAMBOX"));
				}
			folderService.delete(folder);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(folder,"folder.commit.error");
		}
		}
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Folder folder, BindingResult binding){
		ModelAndView result;
		if(folder.getName().equals("INBOX") || folder.getName().equals("OUTBOX") || folder.getName().equals("TRASHBOX")
				|| folder.getName().equals("SPAMBOX")){
			result=createEditModelAndView(folder, "folder.commit.error");
		}else{
		if(binding.hasErrors()){
			result= createEditModelAndView(folder);
		}else{
				try{
					if(folder.getId()!=0){
					Folder folder2=folderService.findOne(folder.getId());
					Assert.isTrue(!folder2.getName().equals("INBOX") && !folder2.getName().equals("OUTBOX") && !folder2.getName().equals("TRASHBOX")
							&& !folder2.getName().equals("SPAMBOX"));
					}
					folderService.save(folder);
					result = new ModelAndView("redirect:list.do");}
				catch(Exception oops){
					result=createEditModelAndView(folder, "folder.commit.error");}
				
			}
		}
			return result;
		
	}
	
	protected ModelAndView createEditModelAndView(Folder folder){
		ModelAndView result;
		
		result = createEditModelAndView(folder, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Folder folder, String message){
		ModelAndView result;
		result = new ModelAndView("folder/edit");
		result.addObject("folder",folder);
		result.addObject("requestURI", "folder/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}
