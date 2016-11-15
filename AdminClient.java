package Client;
import java.net.URI;
import Server.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class AdminClient {
	static final String REST_URI = "http://localhost:9999/MyServer/";

	public static void main(String[] args) {
	
		Client client = Client.create(new DefaultClientConfig());
		URI uri=UriBuilder.fromUri(REST_URI).build();
		WebResource service = client.resource(uri);	
		
		System.out.println("Creation of 3 users : \n");
		
		service.path("/userdirectory/addUser/FirstUser").type(MediaType.TEXT_PLAIN).post("pass1");
		System.out.println("Creation of FirstUser ! \n");
		
		System.out.println("Check that FirstUser has been created \n");
		System.out.println(service.path("/userdirectory/user/FirstUser").type(MediaType.TEXT_XML).get(String.class));		

		service.path("/userdirectory/addUser/SecondUser").type(MediaType.TEXT_PLAIN).post("pass2");
		System.out.println("Creation of SecondUser ! \n");
				
		service.path("/userdirectory/addUser/ThirdUser").type(MediaType.TEXT_PLAIN).post("pass3");
		System.out.println("Creation of ThirdUser ! \n");
				
		System.out.println("---------------------------------------------------");
			
		System.out.println("Check all the users we have : \n");	
		System.out.println(service.path("/userdirectory/alluser").type(MediaType.TEXT_XML).get(String.class));
		
		System.out.println("---------------------------------------------------");
		
		System.out.println("Delete the ThirdUser : \n");
		service.path("/userdirectory/delete/ThirdUser").delete();
		System.out.println("ThirdUser deleted ! \n");			
		
		System.out.println("---------------------------------------------------");	
		
		service.path("/userdirectory/addML/FirstML").type(MediaType.TEXT_PLAIN).post();
		System.out.println("Creation of a Mailing list  (FirstML) ! \n");		
		
		System.out.println("---------------------------------------------------");
		
		/*System.out.println("Check all the mailinglists we have : \n");	
		System.out.println(service.path("/userdirectory/allListsNames").type(MediaType.TEXT_XML).get(String.class));
		
		System.out.println("---------------------------------------------------");*/								
			
	
		}
} 
