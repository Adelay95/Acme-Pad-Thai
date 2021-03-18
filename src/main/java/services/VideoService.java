package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VideosRepository;
import domain.MasterClass;
import domain.Videos;

@Service
@Transactional
public class VideoService {
	@Autowired
	private VideosRepository videosRepository;
	
	public VideoService(){
		super();
	}
	
	public Videos create(){
	
		Videos res;
		res=new Videos();
		Collection<MasterClass> ma= new ArrayList<MasterClass>();
		res.setMasterClass(ma);
		return res;
		
	}
	
	public Videos save(Videos videos){
		Assert.notNull(videos);
		Videos res;
		res=videosRepository.save(videos);
		return res;
	}
	public void delete(Videos videos){
		Assert.notNull(videos);
		Assert.isTrue(videos.getId()!=0);
		videosRepository.delete(videos);
	}
	
	public Videos findOne(int id){
		Videos res;
		res=videosRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public  Collection<Videos> allVideos(){
		Collection<Videos> res;
		res=videosRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Videos> videosByMasterClass(int mcId){
		return videosRepository.videosByMasterClass(mcId);
	}
}
