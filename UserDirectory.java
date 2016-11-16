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


@Path("/userdirectory")
public class UserDirectory {

	public UserDirectory(){
	}
	
//User	
	
	
	/**REST : to add a user and set his default rights to 0 for all the mailing lists
	*@param  username
	*@param  password
	*@param  address
	*/	
	@POST
	@Path("/addUser/{username}")
  @Consumes({MediaType.TEXT_PLAIN})
	public void addUser(@PathParam("username")String username, String password){
	
		String address = username+"@gmail.com";
		DirectoryManager.insertUserDB(username, password, address);
		
		int idUser = DirectoryManager.getUserIdDB(username);
		Liste liste = DirectoryManager.getMailListsIdDB();
		
		for (Integer id : liste.getListe()){
			DirectoryManager.setRightsDB(idUser, id, 0, 0);
		}
	}
	
	
	/**REST : to show one user
	*@param username
	*@return the user 
	*/
	@GET
	@Path("/user/{username}")
	@Produces(MediaType.TEXT_XML)
	public FinalUser lookupOneUser(@PathParam("username")String username) throws IOException {
		return DirectoryManager.getAUserDB(username);
	}
		
	
	/**REST : to list all users
	*@return list of users 
	*/
	@GET
	@Path("/alluser")
	@Produces(MediaType.TEXT_XML)
	public Users lookupAllUsers() throws IOException {
		return DirectoryManager.getUsersDB();
	}

	

	/**REST : to delete a user 
	* @param username 
	*/
	@DELETE
	@Path("/delete/{user}")
	public void removeUser(@PathParam("user")String username) throws IOException{
		int id = DirectoryManager.getUserIdDB(username);
		DirectoryManager.deleteUserDB(id);
	
	}



//RIGHTS

	
/**REST : to create a mailing list
*@param  name
*/	
	@POST
	@Path("/addML/{name}")
  @Consumes({MediaType.TEXT_PLAIN})
	public void addMailingList(@PathParam("name")String name){
		DirectoryManager.insertMailinglistDB(name);

	}
	
/**REST : to list the name of the all mailinglist
*@return list of mails 
*/
	@GET
	@Path("/allListsNames")
	@Produces(MediaType.TEXT_XML)
	public ListeString lookupAllMLNames() throws IOException {
		
		ListeString liste = DirectoryManager.getMailListsNameDB();
		return liste;
	}
	
/**REST : to list the id of all mailinglist
*@return list of mails 
*/
	@GET
	@Path("/allListsId")
	@Produces(MediaType.TEXT_XML)
	public Liste lookupAllMLId() throws IOException {
		return DirectoryManager.getMailListsIdDB();
	}
	
	
	/**REST : Look the reading right of a user 
	* @param username
	* @return a hashmap<idMailinglist, readingRight>
	*/
	@GET
	@Path("/getrights/R/{user}")
	@Produces(MediaType.TEXT_XML)
	public Rights lookupAUserRRights(@PathParam("user")String username){
		return DirectoryManager.getUserRRsDB(username);
	
	}
	
	
	/**REST : Look the writing right of a user
	* @param username
	* @return a hashmap<idMailinglist, writingRight>
	*/
	@GET
	@Path("/getrights/W/{user}")
	@Produces(MediaType.TEXT_XML)
	public Rights lookupAUserWRights(@PathParam("user")String username){
		System.out.println("getAUserRights W");
		return DirectoryManager.getUserWRsDB(username);
	
	}

	
	
	/**Set the reading right of a user for a mailing list
	* @param username
	* @param mailName the name of the mailing list 
	*/
	@POST
	@Path("/setReadRight/{username}")
  @Consumes({MediaType.TEXT_PLAIN})
	public void setReadRight(@PathParam("username")String username, String idMailList){
	
		int idMail = Integer.valueOf(idMailList);
		int id = DirectoryManager.getUserIdDB(username);
		DirectoryManager.updateRRightsDB( id, idMail, 1);

	}
	
	/**Unset the reading right of a user for a mailing list
	* @param username
	* @param mailName the name of the mailing list  
	*/
	@POST
	@Path("/unsetReadRight/{name}")
  @Consumes({MediaType.TEXT_PLAIN})
	public void unsetReadRight(@PathParam("name")String username, String idMailList){

		int idMail = Integer.valueOf(idMailList);
		int id = DirectoryManager.getUserIdDB(username);
		DirectoryManager.updateRRightsDB( id, idMail, 0);

	}
	
	/**Set the writing right of a user for a mailing list
	* @param username
	* @param mailName the name of the mailing list  
	*/
	@POST
	@Path("/setWriteRight/{name}")
  @Consumes({MediaType.TEXT_PLAIN})
	public void setWriteRight(@PathParam("name")String username, String idMailList){

		int idMail = Integer.valueOf(idMailList);
		int id = DirectoryManager.getUserIdDB(username);
		DirectoryManager.updateWRightsDB( id, idMail, 1);

	}
	
	/**Unset the writing right of a user for a mailing list
	* @param username
	* @param mailName the name of the mailing list  
	*/
	@POST
	@Path("/unsetWriteRight/{name}")
  @Consumes({MediaType.TEXT_PLAIN})
	public void unsetWriteRight(@PathParam("name")String username, String idMailList){

		int idMail = Integer.valueOf(idMailList);
		int id = DirectoryManager.getUserIdDB(username);
		DirectoryManager.updateWRightsDB( id, idMail, 0);

	}

}
