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
public class ListeString {
    @XmlElementWrapper(name = "liste")  @XmlElement(name="liste") ArrayList<String>liste=new ArrayList<String>();
	
    public ListeString(){}

    public ListeString(ArrayList<String>liste){
		this.liste=liste;
	}
        
	public void createListeString(String name){
		this.liste.add(name);
	}


}
