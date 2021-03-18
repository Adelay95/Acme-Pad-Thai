package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ClientRepository;
import security.LoginService;
import security.UserAccount;
import domain.Client;
import domain.Comment;
import domain.Recipe;
import domain.Taste;
import domain.User;


@Service
@Transactional
public class ClientService {
	
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private TasteService tasteService;
	
	@Autowired
	private RecipeService recipeService;
	
	
	

	public Client save(Client client){
		Assert.notNull(client);
		Client res;
		res=clientRepository.save(client);
		return res;
	}
	
	public Client findOne(int clientId){
		Client res;
		res = clientRepository.findOne(clientId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Client> allClients(){
		Collection<Client> clients=clientRepository.findAll();
		Assert.notNull(clients);
		return clients;
	}
	
	
	public Client seguirA(Client a,Client b){
		Assert.isTrue(!b.getFollowed().contains(a));
		b.getFollowed().add(a);
		return b;
	}
	public Client dejarDeSeguirA(Client a,Client b){
		Assert.isTrue(b.getFollowed().contains(a));
		b.getFollowed().remove(a);
		return b;
	}
	public Comment commentar(Comment comment){
		Comment res=commentService.save(comment);
		return res;
	}
	
	public Taste gusta(Recipe recipe, Boolean like){
		Client client;
		client=this.findByPrincipal();
		Taste taste;
		Taste res=null;
		Assert.isTrue(recipe.getUser().getId()!=client.getId());
		taste=tasteService.getDuplicateTaste(client.getId(), recipe.getId());
		if(taste==null){
		taste=tasteService.create(client);
		taste.setRecipe(recipe);
		taste.setLikee(like);
		res=tasteService.save(taste);
		client.getTastes().add(res);
		recipe.getTastes().add(res);
		recipeService.save(recipe);
		this.save(client);
		}
		else{
		taste.setLikee(like);
		res=tasteService.save(taste);
		}
		return res;
	}
	

	public Collection<Recipe> recetasClientSeguidos(Integer usuario){
		Assert.notNull(usuario);
		return clientRepository.recetasClientSeguidos(usuario);
	}
	
	public Client findByPrincipal() {
		Client res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByUserAccount(userAccount);
		Assert.notNull(res);
		return res;
	}

	private Client findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Client res;
		res = clientRepository.findByUserAccountId(userAccount.getId());
		return res;

	}
	public Collection<User> getFollowersUser(int clientId){
		return clientRepository.getFollowersUser(clientId);
	}

}
