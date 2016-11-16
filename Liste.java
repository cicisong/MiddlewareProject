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
public class Liste {
    @XmlElementWrapper(name = "liste")  @XmlElement(name="liste") ArrayList<Integer>liste=new ArrayList<Integer>();
	
    public Liste(){}

    public Liste(ArrayList<Integer>liste){
		this.liste=liste;
	}
        
	public void createListe(Integer idMailingList){
		this.liste.add(idMailingList);
	}
	public ArrayList<Integer> getListe(){
		return this.liste;
	}


}
