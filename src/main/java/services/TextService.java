package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TextRepository;
import domain.MasterClass;
import domain.Text;

@Service
@Transactional
public class TextService {
	@Autowired
	private TextRepository textRepository;
	
	public TextService(){
		super();
	}
	
	public Text create(){
	
		Text res;
		res=new Text();
		Collection<MasterClass> ma= new ArrayList<MasterClass>();
		res.setMasterClass(ma);
		return res;
		
	}
	
	public Text save(Text text){
		Assert.notNull(text);
		Text res;
		res=textRepository.save(text);
		return res;
	}
	public void delete(Text text){
		Assert.notNull(text);
		Assert.isTrue(text.getId()!=0);
		textRepository.delete(text);
	}
	
	public Text findOne(int id){
		Text res;
		res=textRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public  Collection<Text> allTexts(){
		Collection<Text> res;
		res=textRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Text> textByMasterClass(int mcId){
		return textRepository.textByMasterClass(mcId);
	}
}
