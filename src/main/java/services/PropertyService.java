package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PropertyRepository;
import domain.Has;
import domain.Property;

@Service
@Transactional
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;
	
	public PropertyService(){
		super();
	}
	
	public Property create(){
		Property res;
		Collection<Has> has=new HashSet<Has>();
		res=new Property();
		res.setHas(has);
		return res;
	}
	
	public Property save(Property property){
		Assert.notNull(property);
		Property res;
		res=propertyRepository.save(property);
		return res;
	}
	public void delete(Property property){
		Assert.notNull(property);
		Assert.isTrue(property.getId()!=0);
		propertyRepository.delete(property);
	}
	
	public  Collection<Property> allProperty(){
		Collection<Property> res;
		res=propertyRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Property findOne(int id){
		Property res;
		res=propertyRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
}
