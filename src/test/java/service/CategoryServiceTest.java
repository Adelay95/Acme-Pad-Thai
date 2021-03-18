package service;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.CategoryService;
import utilities.AbstractTest;

import domain.Category;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml"})
@Transactional
public class CategoryServiceTest extends AbstractTest {
//Service under test
	@Autowired
	private CategoryService categoryService;
	
//Test
	@Test
	public void TestSaveCategory(){
		Category category, saved;
		Collection<Category> categorys;
		category=categoryService.create();
		category.setName("Categoria1");
		category.setDescription("Categoria 1 descripcion");
		saved=categoryService.save(category);
		categorys=categoryService.allCategory();
		Assert.isTrue(categorys.contains(saved));
	}
}

