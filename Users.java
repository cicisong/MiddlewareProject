package Server;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap; 


@XmlRootElement        
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    @XmlElementWrapper(name = "users")  @XmlElement(name="users") Map<String, FinalUser>users=new HashMap<String, FinalUser>();
	
    public Users(){}

    public Users(Map<String, FinalUser>users){
		this.users=users;
	}
        
	public void createUser(String email, FinalUser utilisateur){
		this.users.put(email, utilisateur);
	}


}
