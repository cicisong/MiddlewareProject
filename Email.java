package Server;

import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Collection;  

@XmlRootElement        
@XmlAccessorType(XmlAccessType.FIELD)
public class Email {

	int id;
	String sender;
	String receiver;
	String titre;
	String body;
	int lu;
	
	public Email(){
		
	}
	
	public Email(int id,String sender, String receiver, String titre, String body, int lu){
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.titre = titre;
		this.body = body;	
		this.lu = lu;	
	}
	
	
	public int getId(){
		return this.id;
	}
	public String getSender(){
		return this.sender;
	}
	
	public String getReceiver(){
		return this.receiver;
	}
	
	public String getTitre(){
		return this.titre;
	}

	public String getBody(){
		return this.body;
	}	
	
	public int getLu(){
		return this.lu;
	}		
		
}
