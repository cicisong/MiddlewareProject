package Server;

import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Collection;  

@XmlRootElement        
@XmlAccessorType(XmlAccessType.FIELD)
public class FinalUser {

	String userName;
	String address;
//(TODO : droit are associated to one mailing list!!)
	int rReading;
	int rWrite;
	
	public FinalUser(){
			this.userName = "totot";
	}
	
	public FinalUser(String username, String address, int rReading, int rWrite){
		this.userName = username;
		this.address = address;
		this.rReading = rReading;
		this.rWrite = rWrite;		
	}
	
	public int getUserReadingRight(String username){
	
		return this.rReading;
	}
	
	public int getUserWritingRight(String username){	
	
		return this.rWrite;
	}
	
	public void updateUserReadingRight(String username, int rReading){
	
		this.rReading = rReading;
	}
	
	public void updateUserWritingRight(String username, int rWrite){
	
		this.rWrite = rWrite;		
	}

}
