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

import services.MasterClassService;
import services.PresentationService;
import services.TextService;
import services.VideoService;
import domain.MasterClass;
import domain.Presentation;
import domain.Text;
import domain.Videos;

@Controller
@RequestMapping("/learning-material")
public class LearningMaterialController {
	
	@Autowired 
	private MasterClassService masterClassService;
	@Autowired 
	private TextService textService;
	@Autowired 
	private VideoService videosService;
	@Autowired 
	private PresentationService presentationService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		Collection<Text> text = textService.allTexts();
	    Collection<Videos> videos= videosService.allVideos();
		Collection<Presentation> presentation= presentationService.allPresentations();
		
		result = new ModelAndView("learning-material/list");
		result.addObject("requestURI", "learning-material/list.do");
		result.addObject("text", text);
		result.addObject("videos", videos);
		result.addObject("presentation", presentation);
	
		
		return result;
	}
	
	@RequestMapping(value = "/text/create", method = RequestMethod.GET)
	public ModelAndView createText() {
		ModelAndView result;
		Text text;
		text = textService.create();
		result = createEditModelAndView2(text);

		return result;
	}
	
	@RequestMapping(value = "/videos/create", method = RequestMethod.GET)
	public ModelAndView createVideos() {
		ModelAndView result;
		Videos videos;
		videos = videosService.create();
		result = createEditModelAndView2(videos);

		return result;
	}
	
	@RequestMapping(value = "/presentation/create", method = RequestMethod.GET)
	public ModelAndView createPresentation() {
		ModelAndView result;
		Presentation presentation;
		presentation = presentationService.create();
		result = createEditModelAndView2(presentation);

		return result;
	}
	
	@RequestMapping(value = "/list-learning-material", method = RequestMethod.GET)
	public ModelAndView listLearningMaterial(@RequestParam int masterClassId){
		ModelAndView result;
		
		Collection<Text> text = textService.textByMasterClass(masterClassId);
	    Collection<Videos> videos= videosService.videosByMasterClass(masterClassId);
		Collection<Presentation> presentation= presentationService.presentationsByMasterClass(masterClassId);
		
		
		result = new ModelAndView("learning-material/list");
		result.addObject("requestURI", "learning-material/list-learning-material.do");
		result.addObject("text", text);
		result.addObject("videos", videos);
		result.addObject("presentation", presentation);
		result.addObject("masterClass",masterClassId);
		return result;
	
	}
	
	@RequestMapping(value = "/addlearning-material", method = RequestMethod.GET)
	public ModelAndView addLearningMaterial(@RequestParam int masterClassId){
    ModelAndView result;
		
		Collection<Text> text = textService.allTexts();
	    Collection<Videos> videos= videosService.allVideos();
		Collection<Presentation> presentation= presentationService.allPresentations();
		MasterClass master= masterClassService.findOne(masterClassId);
		
		result = new ModelAndView("learning-material/list");
		result.addObject("requestURI", "learning-material/addlearning-material.do");
		result.addObject("text", text);
		result.addObject("videos", videos);
		result.addObject("presentation", presentation);
		result.addObject("mci",masterClassId);
	    result.addObject("master",master.getLearningMaterials());
		
		return result;
		
	}
	
	
	@RequestMapping(value = "/text/edit", method = RequestMethod.GET)
	public ModelAndView editText(@RequestParam int textId) {
		ModelAndView result;
		Text text;

		text = textService.findOne(textId);		
		Assert.notNull(text);
		result = createEditModelAndView(text);

		return result;
	}

	@RequestMapping(value = "/text/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Text text, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(text);
		} else {
			try {
				textService.save(text);				
				result = new ModelAndView("redirect:/master-class/cook/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(text, "masterClass.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/text/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Text text, BindingResult binding) {
		ModelAndView result;

		try {			
			textService.delete(text);
			result = new ModelAndView("redirect:/master-class/cook/list.do");						
		} catch (Throwable oops) {
			result = createEditModelAndView(text, "masterClass.commit.error");
		}

		return result;
	}
	
	protected ModelAndView createEditModelAndView(Text text) {
		ModelAndView result;
		result = createEditModelAndView(text, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Text text, String message) {
		ModelAndView result;
		result = new ModelAndView("learning-material/text/edit");
		
		result.addObject("requestURI", "learning-material/text/edit.do");
		result.addObject("text", text);
		result.addObject("message", message);
		return result;
	}
	
	protected ModelAndView createEditModelAndView2(Text text) {
		ModelAndView result;
		result = createEditModelAndView2(text, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView2(Text text, String message) {
		ModelAndView result;
		result = new ModelAndView("learning-material/text/create");
		
		result.addObject("requestURI", "learning-material/text/create.do");
		result.addObject("text", text);
		result.addObject("message", message);
		return result;
	}
	
	@RequestMapping(value = "/videos/edit", method = RequestMethod.GET)
	public ModelAndView editVideos(@RequestParam int videosId) {
		ModelAndView result;
		Videos videos;

		videos = videosService.findOne(videosId);		
		Assert.notNull(videos);
		result = createEditModelAndView(videos);

		return result;
	}

	@RequestMapping(value = "/videos/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Videos videos, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(videos);
		} else {
			try {
				videosService.save(videos);				
				result = new ModelAndView("redirect:/master-class/cook/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(videos, "masterClass.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/videos/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Videos videos, BindingResult binding) {
		ModelAndView result;

		try {			
			videosService.delete(videos);
			result = new ModelAndView("redirect:/master-class/cook/list.do");						
		} catch (Throwable oops) {
			result = createEditModelAndView(videos, "masterClass.commit.error");
		}

		return result;
	}
	
	protected ModelAndView createEditModelAndView(Videos videos) {
		ModelAndView result;
		result = createEditModelAndView(videos, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Videos videos, String message) {
		ModelAndView result;
		result = new ModelAndView("learning-material/videos/edit");
		
		result.addObject("requestURI", "learning-material/videos/edit.do");
		result.addObject("videos", videos);
		result.addObject("message", message);
		return result;
	}
	
	protected ModelAndView createEditModelAndView2(Videos videos) {
		ModelAndView result;
		result = createEditModelAndView2(videos, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView2(Videos videos, String message) {
		ModelAndView result;
		result = new ModelAndView("learning-material/videos/create");
		
		result.addObject("requestURI", "learning-material/videos/create.do");
		result.addObject("videos", videos);
		result.addObject("message", message);
		return result;
	}
	
	@RequestMapping(value = "/presentation/edit", method = RequestMethod.GET)
	public ModelAndView editPresentation(@RequestParam int presentationId) {
		ModelAndView result;
		Presentation presentation;

		presentation = presentationService.findOne(presentationId);		
		Assert.notNull(presentation);
		result = createEditModelAndView(presentation);

		return result;
	}

	@RequestMapping(value = "/presentation/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Presentation presentation, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(presentation);
		} else {
			try {
				presentationService.save(presentation);				
				result = new ModelAndView("redirect:/master-class/cook/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(presentation, "masterClass.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/presentation/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Presentation presentation, BindingResult binding) {
		ModelAndView result;

		try {			
			presentationService.delete(presentation);
			result = new ModelAndView("redirect:/master-class/cook/list.do");						
		} catch (Throwable oops) {
			result = createEditModelAndView(presentation, "masterClass.commit.error");
		}

		return result;
	}
	
	protected ModelAndView createEditModelAndView(Presentation presentation) {
		ModelAndView result;
		result = createEditModelAndView(presentation, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Presentation presentation, String message) {
		ModelAndView result;
		result = new ModelAndView("learning-material/presentation/edit");
		
		result.addObject("requestURI", "learning-material/presentation/edit.do");
		result.addObject("presentation", presentation);
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView createEditModelAndView2(Presentation presentation) {
		ModelAndView result;
		result = createEditModelAndView2(presentation, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView2(Presentation presentation, String message) {
		ModelAndView result;
		result = new ModelAndView("learning-material/presentation/create");
		
		result.addObject("requestURI", "learning-material/presentation/create.do");
		result.addObject("presentation", presentation);
		result.addObject("message", message);
		return result;
	}
}
