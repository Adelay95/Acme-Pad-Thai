package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class FolderService {
	
	@Autowired
	private FolderRepository folderRepository;
	@Autowired
	private MessageService messageService;
	
	
	public FolderService(){
		super();
	}
	
	public Folder create(Actor actor){
		Assert.notNull(actor);
		Folder res;
		Collection<Message> messages= new ArrayList<Message>();
		res=new Folder();
		res.setMessages(messages);
		res.setActor(actor);
		return res;
		
	}
	
	public Folder save(Folder folder){
		Assert.notNull(folder);
		Folder res;
		res=folderRepository.save(folder);
		return res;
	}
	public void delete(Folder folder){
		Assert.notNull(folder);
		for(Message m:folder.getMessages()){
			messageService.delete(m);
		}
		folderRepository.delete(folder);
				
	}
	
	public Folder findOne(int id){
		Folder res;
		res=folderRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public  Collection<Folder> allFolders(){
		Collection<Folder> res;
		res=folderRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Folder findINBOX(Actor actor){
		Assert.notNull(actor);
		Folder folder=folderRepository.getINBOXByActorId("INBOX",actor.getId());
		return folder;
		
	}
	public Folder findOUTBOX(Actor actor){
		Assert.notNull(actor);
		Folder folder=folderRepository.getOUTBOXByActorId("OUTBOX",actor.getId());
		return folder;
		
	}
	
	public Folder findSPAMBOX(Actor actor){
		Assert.notNull(actor);
		Folder folder=folderRepository.getOUTBOXByActorId("SPAMBOX",actor.getId());
		return folder;
	}
	public Folder findTRASHBOX(Actor actor){
		Assert.notNull(actor);
		Folder folder=folderRepository.getOUTBOXByActorId("TRASHBOX",actor.getId());
		return folder;
	}

}
