package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SpamRepository;
import domain.Spam;

@Service
@Transactional
public class SpamService {
	@Autowired
	private SpamRepository spamRepository;
	public SpamService(){
		super();
	}
	public Spam create(){
		Spam res;		
		res=new Spam();
		return res;
	}
	
	public Spam save(Spam spamo){
		Assert.notNull(spamo);
		if(spamo.getId()!=0){
		Spam spam=findOne(spamo.getId());
		Assert.isTrue(!spam.getTerms().equals("sex") && !spam.getTerms().equals("viagra") 
				&& !spam.getTerms().equals("cialis") && !spam.getTerms().equals("love"));
		}
		Spam res=spamRepository.save(spamo);
		return res;
	}
	public void delete(Spam spamo){
		Assert.notNull(spamo);
		Assert.isTrue(spamo.getId()!=0);
		Spam spam=findOne(spamo.getId());
		Assert.isTrue(!spam.getTerms().equals("sex") && !spam.getTerms().equals("viagra") 
				&& !spam.getTerms().equals("cialis") && !spam.getTerms().equals("love"));
		spamRepository.delete(spam);
	}
	
	public  Collection<Spam> allSpam(){
		Collection<Spam> res;
		res=spamRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Spam findOne(int id){
		Spam res;
		res=spamRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
}
