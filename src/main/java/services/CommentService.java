package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Client;
import domain.Comment;
import domain.Recipe;

@Service
@Transactional
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private ClientService clientService;
	
	public CommentService(){
		super();
	}
	//Duda:Quitarle recipe(como en mensaje)
	public Comment create(Client a){
		Comment res;
		Assert.notNull(a);
		Date hoy=new Date(System.currentTimeMillis() - 1000);
		//Assert.notNull(r);
		res=new Comment();
		res.setClient(a);
		res.setCreationDate(hoy);
		//res.setRecipe(r);
		return res;
	}
	
	public Comment save(Comment comment){
		Assert.notNull(comment);
		Assert.isTrue(comment.getId()==0);
		Comment res;
		Recipe res1=comment.getRecipe();
		Client c1=comment.getClient();
		res=commentRepository.save(comment);
		c1.getComments().add(res);
		res1.getComments().add(res);
		recipeService.save(res1);
		clientService.save(c1);
		return res;
	}
	//Requisito de que no pieden ser modificado
	public void delete(Comment comment){
		Assert.notNull(comment);
		Assert.isTrue(comment.getId()!=0);
		commentRepository.delete(comment);
	}
	
	public  Collection<Comment> allComment(){
		Collection<Comment> res;
		res=commentRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Comment findOne(int id){
		Comment res;
		res=commentRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	

}
