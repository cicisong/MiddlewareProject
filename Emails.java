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
public class Emails {
    @XmlElementWrapper(name = "emails")  @XmlElement(name="emails") ArrayList<Email> emails=new ArrayList<Email>();
	
    public Emails(){}

    public Emails(ArrayList<Email> emails){
		this.emails=emails;
	}
        
	public void createEmail(Email email){
		this.emails.add(email);
	}


}
