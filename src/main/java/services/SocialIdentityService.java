package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.Actor;
import domain.SocialIdentity;
@Service
@Transactional
public class SocialIdentityService {
	
	@Autowired
	private SocialIdentityRepository socialIdentityRepository;
	
	public SocialIdentityService(){
		super();
	}
	
	public SocialIdentity create(Actor actor){
		Assert.notNull(actor);
		SocialIdentity res;
		res=new SocialIdentity();
		res.setActor(actor);
		return res;
		
	}
	
	public SocialIdentity save(SocialIdentity socialIdentity){
		Assert.notNull(socialIdentity);
		SocialIdentity res;
		res=socialIdentityRepository.save(socialIdentity);
		return res;
	}
	public void delete(SocialIdentity socialIdentity){
		Assert.notNull(socialIdentity);
		Assert.isTrue(socialIdentity.getId()!=0);
		socialIdentityRepository.delete(socialIdentity);
	}
	
	public SocialIdentity findOne(int id){
		SocialIdentity res;
		res=socialIdentityRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public  Collection<SocialIdentity> allSocialIdentity(){
		Collection<SocialIdentity> res;
		res=socialIdentityRepository.findAll();
		Assert.notNull(res);
		return res;
	}

}
