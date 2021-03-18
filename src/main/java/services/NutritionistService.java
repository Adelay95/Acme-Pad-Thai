package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NutritionistRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Client;
import domain.Comment;
import domain.Curricula;
import domain.Folder;
import domain.Ingredient;
import domain.Message;
import domain.Nutritionist;
import domain.Property;
import domain.SocialIdentity;
import domain.Taste;

@Service
@Transactional
public class NutritionistService {
	@Autowired
	private NutritionistRepository nutritionistRepository;
	@Autowired
	private CurriculaService curriculaService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private TasteService tasteService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private ActorService actorService;
	
	
	
	public NutritionistService(){
		super();
	}
	
	

	public Nutritionist create(){
		  Nutritionist res;
		  Collection<Curricula> cu=new HashSet<Curricula>();
		  Collection<Client> seg=new HashSet<Client>();
		  Collection<Taste> ta=new HashSet<Taste>();
		  Collection<Comment> co=new HashSet<Comment>();
		  Collection<Folder> folders=new HashSet<Folder>();
		  Collection<SocialIdentity> socialIdentities=new HashSet<SocialIdentity>();
		  Collection<Message> messageSent=new HashSet<Message>();
		  Collection<Message> messageReceived=new HashSet<Message>();
		  UserAccount userAccount=new UserAccount();
			Authority aut=new Authority();
			aut.setAuthority("NUTRITIONIST");
			Collection<Authority> authorities=new HashSet<Authority>();
			authorities.add(aut);
			userAccount.setAuthorities(authorities);
		  res=new Nutritionist();
		  res.setUserAccount(userAccount);
		  res.setSocialIdentities(socialIdentities);
		  res.setMessageReceived(messageReceived);
		  res.setMessageSent(messageSent);
		  res.setFolders(folders);
		  res.setCurricula(cu);
		  res.setFollowed(seg);
		  res.setTastes(ta);
		  res.setComments(co);
		  return res;
		 }
		 
		 public Nutritionist save(Nutritionist nutritionist){
		  Assert.notNull(nutritionist);
		  Nutritionist res;
		  res=nutritionistRepository.save(nutritionist);
		  if(res.getFolders().isEmpty()){
		   Collection<Folder> folder= actorService.createFolderss(res);
		   res.setFolders(folder);
	         res=nutritionistRepository.save(res);
	   }
	  return res;
	 }
	public void delete(Nutritionist nutritionist){
		Assert.notNull(nutritionist);
		Assert.isTrue(nutritionist.getId()!=0);
		nutritionistRepository.delete(nutritionist);
	}
	
	public  Collection<Nutritionist> allNutritionist(){
		Collection<Nutritionist> res;
		res=nutritionistRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Nutritionist findOne(int id){
		Nutritionist res;
		res=nutritionistRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public Nutritionist findByPrincipal(){
		Nutritionist res;
		UserAccount userAccount;
		userAccount=LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res=findByNutritionistAccount(userAccount);
		Assert.notNull(res);
		return res;
	}


	private Nutritionist findByNutritionistAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Nutritionist res;
		res=nutritionistRepository.findByUserAccountId(userAccount.getId());
		return res;
		
	}
	
	//NUESTROS
	
	public Collection<Curricula> curriculasPorNutricionista(Integer nutritionist){
		  Assert.notNull(nutritionist);
		  return nutritionistRepository.curriculasDelNutricionista(nutritionist);
		  }
	
	public Curricula crearCurricula(Curricula res){
		Nutritionist nutritionist;
		nutritionist=this.findByPrincipal();
		Curricula saved;
		Assert.notNull(res);
		res.setNutritionist(nutritionist);
		saved =curriculaService.save(res);
		nutritionist.getCurricula().add(saved);
		save(nutritionist);
		return saved;
	}
	
	public void borrarCurricula(Curricula curricula){
		Assert.notNull(curricula);
		Nutritionist nutritionist;
		nutritionist=this.findByPrincipal();
		Assert.isTrue(curricula.getNutritionist().equals(nutritionist));
		nutritionist.getCurricula().remove(curricula);
		curriculaService.delete(curricula);
		save(nutritionist);
		
	}
	public void actualizarCurricula(Integer idCurricula,String photo,String educationSection,String experience,String hobby,
			Integer numReferences){
		Curricula modificar;
		Nutritionist nutritionist;
		nutritionist=this.findByPrincipal();
		modificar=curriculaService.findOne(idCurricula);
		Assert.notNull(modificar);
		Assert.isTrue(modificar.getNutritionist().equals(nutritionist));
		modificar.setEducationSection(educationSection);
		modificar.setExperience(experience);
		modificar.setHobby(hobby);
		modificar.setPhoto(photo);
		curriculaService.save(modificar);
	}
	
	public Ingredient crearIngredient(Ingredient i){
		Assert.notNull(i);
		return ingredientService.save(i);
	}
	
	public Collection<Ingredient> mostrarTodosIngredientes(){
		return ingredientService.allIngredient();
	}
	
	public void deleteIngredient(Ingredient ingre){
		Assert.notNull(ingre);
		//checkIngrediente(ingre);
		ingredientService.delete(ingre);
	}
	

	
	public Property crearProperty(Property res){
		
		//res=propertyService.create();
		Assert.notNull(res);
		return propertyService.save(res);
	}
	
	public Collection<Property> mostrarTodasPorpertys(){
		return propertyService.allProperty();
	}
	
	public void deleteProperty(Property prope){
		Assert.notNull(prope);
		//checkProperty(prope);
		Assert.isTrue(prope.getHas().isEmpty());
		propertyService.delete(prope);
	}
	

	
	public Client seguir(Client c){
		  Assert.notNull(c);
		  Client us;
		  Client saved;
		  Nutritionist u;
		  u=findByPrincipal();
		  us=clientService.seguirA(u, c);
		  saved=clientService.save(us);
		  return saved;
		  
		 }
		 public Client dejarDeSeguir(Client c){
		  Assert.notNull(c);
		  Client us;
		  Client saved;
		  Nutritionist u;
		  u=findByPrincipal();
		  us=clientService.dejarDeSeguirA(u, c);
		  saved=clientService.save(us);
		  return saved;
		  
		 }
		 
		 public Comment comentar(Comment a){
				//Assert.notNull(p);
				Comment saved;
				saved=commentService.save(a);
				return saved;
			}
			
			
			public Taste gustarReceta(Taste a){
				//Assert.notNull(p);
				Taste saved;
				saved=tasteService.save(a);
				return saved;
			}
			public Collection<Property> propertyNoUse(int idIngre){
				return nutritionistRepository.propertyNoUsed(idIngre);
			}

}
