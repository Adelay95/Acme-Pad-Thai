package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Category;
import domain.Client;
import domain.Comment;
import domain.Contest;
import domain.CookStep;
import domain.Folder;
import domain.Ingredient;
import domain.Message;
import domain.Quantity;
import domain.Recipe;
import domain.SocialIdentity;
import domain.Taste;
import domain.User;

@Service
@Transactional
public class UserService {
	
	
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private TasteService tasteService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ActorService actorService;
//	@Autowired
//	private LoginService loginService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private CookStepService cookStepService;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private QuantityService quantityService;
	
	public UserService(){
		super();
	}
	
	
	public User registrarUser(UserAccount userAccount){
		User u= create();
		u.setUserAccount(userAccount);
		return save(u);
		
		
	}
	
	public User create(){
		User res;
		res=new User();
		Collection<Client> followed=new HashSet<Client>();
		Collection<Taste> tastes=new HashSet<Taste>();
		Collection<SocialIdentity> socialIdentities=new HashSet<SocialIdentity>();
		Collection<Message> messageSent=new HashSet<Message>();
	    Collection<Message> messageReceived=new HashSet<Message>();
		Collection<Comment> comments=new HashSet<Comment>();
		Collection<Recipe> recipes=new HashSet<Recipe>();
		Collection<Folder> folders=new HashSet<Folder>();
		UserAccount userAccount=new UserAccount();
		Authority aut=new Authority();
		aut.setAuthority("USER");
		Collection<Authority> authorities=new HashSet<Authority>();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);
		res.setFolders(folders);
		res.setMessageReceived(messageReceived);
		res.setMessageSent(messageSent);
		res.setFollowed(followed);
		res.setUserAccount(userAccount);
		res.setTastes(tastes);
		res.setComments(comments);
		res.setNumOriginalRecipes(0);
		res.setRecipes(recipes);
		res.setSocialIdentities(socialIdentities);
		return res;
	}
	
	public User save(User user){
		Assert.notNull(user);
	    User res;
	    res=userRepository.save(user);
	    if(res.getFolders().isEmpty()){
	     Collection<Folder> folder= actorService.createFolderss(res);
	     res.setFolders(folder);
	          res=userRepository.save(res);
	    }
	   return res;
	}
	public void delete(User user){
		Assert.notNull(user);
		Assert.isTrue(user.getId()!=0);
		userRepository.delete(user);
	}
	
	public  Collection<User> allUsers(){
		Collection<User> res;
		res=userRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public User findOne(int id){
		User res;
		res=userRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public User findByPrincipal(){
		User res;
		UserAccount userAccount;
		userAccount=LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res=findByUserAccount(userAccount);
		Assert.notNull(res);
		return res;
	}


	private User findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		User res;
		res=userRepository.findByUserAccountId(userAccount.getId());
		return res;
		
	}
	//Nuestros
	public Collection<Recipe> recetasUsuario(Integer usuario){
		Assert.notNull(usuario);
		return userRepository.recetasUsuario(usuario);
	}
	public Collection<Recipe> recetasOriginalesUsuario(Integer usuario){
		Assert.notNull(usuario);
		return userRepository.recetasOriginalesUsuario(usuario);
	}

	public Collection<User> usuariosNombre(String nombre){
		Assert.notNull(nombre);
		String buscar="%"+nombre+"%";
		return userRepository.usuariosNombre(buscar);
	}
	
	public Recipe crearReceta(Recipe res){
		Recipe saved;
		Assert.notNull(res);
		Assert.isTrue(this.findByPrincipal().equals(res.getUser()));
		saved=recipeService.save(res);
		return saved;
	}
	
	public Quantity addIngrediente(Quantity quantity){
		Quantity saved;
		User user;
		user=this.findByPrincipal();
		Assert.isTrue(quantity.getRecipe().getUser().equals(user));
		saved=quantityService.save(quantity);
		Recipe recipe=quantity.getRecipe();
		recipe.getQuantities().add(quantity);
		recipeService.save(recipe);
		return saved;
	}
	public Quantity actualizarQuantity(Quantity quantity){
		Quantity saved;
		User user;
		user=this.findByPrincipal();
		Assert.isTrue(quantity.getRecipe().getUser().equals(user));
		this.quitarIngredienteReceta(quantity);
		saved=this.addIngrediente(quantity);
		return saved;
	}
	public CookStep addCookStep(CookStep cookStep){
		CookStep saved;
		User user;
		user=this.findByPrincipal();
		Recipe recipe=cookStep.getRecipe();
		Assert.isTrue(recipe.getUser().equals(user));
		saved=cookStepService.save(cookStep);
		recipe.getCookSteps().add(saved);
		recipeService.save(recipe);
		return saved;
	}
	public CookStep actualizarCookStep(CookStep cookStep){
		CookStep saved;
		User user;
		user=this.findByPrincipal();
		Recipe recipe=cookStep.getRecipe();
		Assert.isTrue(recipe.getUser().equals(user));
		saved=cookStepService.save(cookStep);
		return saved;
	}
	
	public void quitarIngredienteReceta(Quantity quantity){
		User user;
		user=this.findByPrincipal();
		Assert.isTrue(quantity.getRecipe().getUser().equals(user));
		quantityService.delete(quantity);
	}
	
	
	public void quitarCookStepReceta(CookStep cookstep){
		User user;
		user=this.findByPrincipal();
		Recipe recipe=cookstep.getRecipe();
		Assert.isTrue(recipe.getUser().equals(user));
		recipe.getCookSteps().remove(cookstep);
		recipeService.save(recipe);
		cookStepService.delete(cookstep);
	}
	public void quitarCategoriaReceta(Category category,Recipe recipe){
		Assert.isTrue(recipe.getCategories().size()>1);
		User user;
		user=this.findByPrincipal();
		Assert.isTrue(recipe.getUser().equals(user));
		recipe.getCategories().remove(category);
		recipeService.save(recipe);
		category.getRecipes().remove(recipe);
		categoryService.save(category);
	}
	public void addCategoriaReceta(Category category,Recipe recipe){
		User user;
		user=this.findByPrincipal();
		Assert.isTrue(recipe.getUser().equals(user));
		recipe.getCategories().add(category);
		recipeService.save(recipe);
		category.getRecipes().add(recipe);
		categoryService.save(category);
	}
	
	public void borrarReceta(Recipe recipe){
		Assert.notNull(recipe);
		User user;
		user=this.findByPrincipal();
		Assert.isTrue(recipe.getUser().equals(user));
		for(Category c:recipe.getCategories()){
			c.getRecipes().remove(recipe);
			categoryService.save(c);
		}
		for(Quantity q:recipe.getQuantities()){
			Ingredient i=q.getIngredient();
			i.getQuantities().remove(q);
			ingredientService.save(i);
			quantityService.delete(q);
		}
		recipeService.delete(recipe);
	}
	
	// PONER QUE MUESTRE LAS DEL USUARIO REGISTRADO
	
	public Collection<Recipe> misRecetas(){
		Collection<Recipe> res;
		User user;
		user=findByPrincipal();
		res=recetasUsuario(user.getId());
		return res;
	}
	
	// REVISAR ISTRUE
	
	public Recipe calificarReceta(int idReceta,int idConcurso){
		//Recipe calif;
		Contest concurso;
		Recipe original,copy,copy2;
		User user;
		user=findByPrincipal();
		//calif=recipeService.findOne(idReceta);
		//Assert.notNull(calif);
		concurso=contestService.findOne(idConcurso);
		Assert.notNull(concurso);
		original=recipeService.findOne(idReceta);
		Assert.isTrue(!concurso.getOriginalRecipes().contains(original));
		CheckConcursoActivo(concurso);
		CheckRecetaPosible(original);
		Assert.isTrue(original.getUser().equals(user));
		copy=recipeService.createCopy(idReceta);
		Assert.notNull(copy);
		concurso.getQualifiedRecipes().add(copy);
		concurso.getOriginalRecipes().add(original);
		Contest concurso2=contestService.save(concurso);
		copy.setQualifiedContests(concurso2);
		copy2=recipeService.saveWinnerRecipe(copy);
		
		return copy2;
		
	}


	public void CheckRecetaPosible(Recipe calif) {
		Collection<Taste> gustos;
		gustos=calif.getTastes();
		Integer likes=recipeService.likesRecipe(calif.getId());
		  Integer dislikes=gustos.size()-likes;
		Assert.isTrue(likes>=5 && dislikes==0);
		
	}


	private void CheckConcursoActivo(Contest concurso) {
		Date hoy;
		hoy=new Date();
		boolean expression;
		expression=concurso.getOpeningTime().before(hoy)&& concurso.getClosingTime().after(hoy);
		Assert.isTrue(expression);
		
	}
	
	public Client seguir(Client c){
		Assert.notNull(c);
		Client us;
		Client saved;
		User u;
		u=findByPrincipal();
		us=clientService.seguirA(u, c);
		saved=clientService.save(us);
		return saved;
		
	}
	public Client dejarDeSeguir(Client c){
		Assert.notNull(c);
		Client us;
		Client saved;
		User u;
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



	public Integer minRecipesUser() {
		return userRepository.getMinRecipesUser();
	}
	public Double avgRecipesUser() {
		return userRepository.getAvgRecipesUser();
	}
	public Integer maxRecipesUser() {
		return userRepository.getMaxRecipesUser();
	}

	public Collection<User> userMaxRecipes() {
		return userRepository.getUserMaxRecipes();
	}


	public Collection<User> popularityListing() {
		return userRepository.getPopularityListing();
	}


	public Collection<User> tastesUsers() {
		return userRepository.getTastesUsers();
	}
	
//	public Collection<Ingredient> IngredientNoUse(int idRecipe){
//		return userRepository.ingredientNoUse(idRecipe);
//	}
}
