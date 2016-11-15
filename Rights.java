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
public class Rights {
    @XmlElementWrapper(name = "rights")  @XmlElement(name="rights") Map<Integer, Integer>rights=new HashMap<Integer, Integer>();
	
    public Rights(){}

    public Rights(Map<Integer, Integer>rights){
		this.rights=rights;
	}
        
	public void createRR(Integer idMailingList, Integer droit){
		this.rights.put(idMailingList, droit);
	}


}
