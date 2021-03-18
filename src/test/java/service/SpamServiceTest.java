package service;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.SpamService;
import utilities.AbstractTest;
import domain.Spam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml"})
@Transactional

public class SpamServiceTest extends AbstractTest{
	
	@Autowired
	private SpamService spamService;
	
	@Test
	public void TestCreateAndSaveSpam(){
		Spam spam, saved;
		Collection<Spam> spams;
		spam=spamService.create();
		saved=spamService.save(spam);
		spams=spamService.allSpam();
		Assert.isTrue(spams.contains(saved));
	}
	
//	@Test
//	public void TestDefaultTermsSpam(){
//		Spam spam, saved;
//		spam=spamService.create();
//		saved=spamService.save(spam);
//		Assert.isTrue(saved.getTerms().size()==4);
//	}
	
	

}
