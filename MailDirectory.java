package Server;

import java.io.IOException;

import java.util.Properties;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;


@Path("/maildirectory")
public class MailDirectory {

	public MailDirectory(){
		System.out.println("toto");
	}
	
/**REST : to list all emails
*@return list of emails 
*/
	@GET
	@Path("/allmail/{user}")
	@Produces(MediaType.TEXT_XML)
	public Emails lookupAllMails(@PathParam("user")String username) throws IOException {
		//System.out.println("all");
		return DirectoryManager.getEmailsDB(DirectoryManager.getUserIdDB(username));
	}
	
/**REST : to read an email 
* @param id of the email
*@return the email 
*/
	@GET
	@Path("/mail/{id}")
	@Produces(MediaType.TEXT_XML)
	public Email readEmail(@PathParam("id")int id) throws IOException {
		//System.out.println("all");
		Email email = DirectoryManager.getAEmailDB(id);
		DirectoryManager.setReadDB(email);
		return DirectoryManager.getAEmailDB(id);
	}
	
	
/**REST : to create an email
*@param  username
*@param  password
*@param  address
*/	
	@POST
	@Path("/addEmail")
  @Consumes({MediaType.APPLICATION_XML})
	public void addEmail(Email email){
			
		int sender = DirectoryManager.getUserIdDB(email.getSender());
		int receiver = DirectoryManager.getUserIdDB(email.getReceiver());
		String title = email.getTitre();
		String body = email.getBody();
		DirectoryManager.insertEmailDB(sender, receiver, title, body);

	}
	
/**REST : to update read status of an email
*@param  idMail
*/	
	@POST
	@Path("/setAsRead/{idMail}")
	public void setAsRead(@PathParam("idMail")int idMail){
	
		System.out.println("readEmail");
		Email email = DirectoryManager.getAEmailDB(idMail);
		int sender = DirectoryManager.getUserIdDB(email.getSender());
		int receiver = DirectoryManager.getUserIdDB(email.getReceiver());
		String title = email.getTitre();
		String body = email.getBody();
		DirectoryManager.setReadDB(email);

	}


}
