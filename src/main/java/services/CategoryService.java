package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;
import domain.Recipe;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryService(){
		super();
	}
	
	public Category create(){
		Category res;
		 Collection<Category> sons=new HashSet<Category>();
		 Collection<Category> parents=new HashSet<Category>();
		Collection<Recipe> recipes=new ArrayList<Recipe>();
		res=new Category();
		res.setSons(sons);
		res.setParents(parents);
		res.setRecipes(recipes);
		return res;
	}
	
	public Category save(Category category){
		Assert.notNull(category);
		Category res;
		res=categoryRepository.save(category);
		return res;
	}
	public void delete(Category category){
		Assert.notNull(category);
		Assert.isTrue(category.getId()!=0);
		Assert.isTrue(category.getRecipes().isEmpty());
		if(!category.getParents().isEmpty()){
			for(Category parent:category.getParents()){
				parent.getSons().remove(category);
				save(parent);
			}
		}
		categoryRepository.delete(category);
	}
	
	public  Collection<Category> allCategory(){
		Collection<Category> res;
		res=categoryRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Category findOne(int id){
		Category res;
		res=categoryRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public void serHijoDelPadre(Category son,Category parent){
		Assert.notNull(son);
		Assert.notNull(parent);
		Assert.isTrue(!parent.getParents().contains(son));
		Assert.isTrue(!son.getParents().contains(parent));
		Assert.isTrue(son.getId()!=parent.getId());
		son.getParents().add(parent);
		Category savedSon=save(son);
		parent.getSons().add(savedSon);
		save(parent);
	}

	public void quitarHijoDelPadre(Category son, Category parent) {
		Assert.notNull(son);
		Assert.notNull(parent);
		Assert.isTrue(son.getParents().contains(parent));
		son.getParents().remove(parent);
		Category savedSon=save(son);
		parent.getSons().remove(savedSon);
		save(parent);
		
	}

}
