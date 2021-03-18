package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private FolderService folderService;
	@Autowired
	private ActorService actorService;
	
	public MessageService(){
		super();
	}
	
	public Message create(Actor sender,Actor receiver){
		Assert.notNull(sender);
		Assert.notNull(receiver);
		Message res;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1000);
		res=new Message();
        res.setMoment(moment);
        res.setSender(sender);
        res.setReceiver(receiver);
        Folder folder=folderService.findOUTBOX(sender);
        res.setFolder(folder);
		return res;
		
	}
	
	public Message save(Message message){
		Assert.notNull(message);
		Message res;
		res=messageRepository.save(message);
		return res;
	}
	public void delete(Message message){
		Assert.notNull(message);
		Assert.isTrue(message.getId()!=0);
		Actor actor=actorService.findByPrincipal();
		Folder basura=folderService.findTRASHBOX(actor);
		Assert.notNull(basura);
		if(message.getFolder().getName().equals("TRASHBOX")){
			basura.getMessages().remove(message);
			folderService.save(basura);
			messageRepository.delete(message);
		}else{
			Folder bef=message.getFolder();
			bef.getMessages().remove(message);
			folderService.save(bef);
			message.setFolder(basura);
			Message saved=save(message);
			basura.getMessages().add(saved);
			folderService.save(basura);
		}
		
	}
	
	public Message findOne(int id){
		Message res;
		res=messageRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public  Collection<Message> allMessages(){
		Collection<Message> res;
		res=messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public void deleteFinish(Message message) {
		Assert.notNull(message);
		messageRepository.delete(message);
		
	}
}
