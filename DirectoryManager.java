package Server;

import javax.xml.ws.Endpoint;

import java.sql.*;

import java.lang.String;

import java.util.*;



public class DirectoryManager {
	
  private static final String dbClassName = "com.mysql.jdbc.Driver";

  private static final String CONNECTION =
                          "jdbc:mysql://127.0.0.1/mailServer?user=root&password=root";
                          
  public static Connection c;
  
/**
* @param username the new user's username
* @param password the new user's password
* @param password the new user's email address
*/
	public static Connection initConnection() throws Exception {
		Class.forName(DirectoryManager.dbClassName);
		DirectoryManager.c = DriverManager.getConnection(DirectoryManager.CONNECTION);
		return DirectoryManager.c;
	}
	
	
	/**to insert a user in the database
	* @param username the new user's username
	* @param password the new user's password
	* @param password the new user's email address
	*/
	public static void insertUserDB(String username, String password, String address){
		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("INSERT INTO utilisateur (username, password, address) VALUES('"+username+"','"+password+"', '"+address+"')");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**Delete a user in the database
	* @param id
	*/
	public static void deleteUserDB(int id){
		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("DELETE FROM utilisateur WHERE idUser = "+id);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**To get all the users from the database
	* @return map of users
	*/
	public static Users getUsersDB(){
		Statement s;
		Users liste = new Users ();

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT username,address, droitLecture, droitEcriture FROM utilisateur u, rel r WHERE u.idUser = r.idUser");
			while ( rs.next() ){
			
				FinalUser user = new FinalUser(rs.getString("username"),rs.getString("address"), rs.getInt("droitLecture"), rs.getInt("droitEcriture"));
				liste.createUser(rs.getString("username"), user);
 
 			}
 		
 			return liste;
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null; 
	}



	/**To get one user from the database (TODO : droit are associated to one mailing list!!)
	* @param username
	* @return a user
	*/
	public static FinalUser getAUserDB(String username){
		Statement s;

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT username,address, droitLecture, droitEcriture FROM utilisateur u, rel r WHERE u.idUser = r.idUser");
			rs.next();			
			FinalUser user = new FinalUser(rs.getString("username"),rs.getString("address"), rs.getInt("droitLecture"), rs.getInt("droitEcriture"));
 		
 			return user;
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null; 
	}
	

/** get the id of a user in the database
* @param username the new user's username
* @return idUser the id of the user
*/
	public static int getUserIdDB(String user){
		Statement s;

		try{

			s = c.createStatement();
			System.out.println("test");
			System.out.println(user);
			ResultSet rs = s.executeQuery("SELECT idUser FROM utilisateur WHERE username = '"+user+"'");
			rs.next();
			return rs.getInt("idUser");
 
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return -1; 
	}
	
/** get the username of a user in the database
* @param id id of the user
* @return username the name of the user
*/
	public static String getUserNameDB(int id){
		Statement s;

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT username FROM utilisateur WHERE idUser = '"+id+"'");
			rs.next();
			return rs.getString("username");
 
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null; 
	}	


/** Set the reading or the writing rights of a user
* @param idUser the id of the user
* @param idMailingList the id of the mailing list we want to subscribe
* @param readingRight the value we want for the reading right of the user for this mailing list
* @param writingRight the value we want for the writing right of the user for this mailing list
*/
	public static void setRightsDB(int idUser, int idMailingList, int readingRight, int writingRight){
		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("INSERT INTO rel (idUser, idMailingList,  	droitLecture, droitEcriture) VALUES("+idUser+","+idMailingList+","+readingRight+", "+writingRight+")");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


/** Update the reading right of a user
* @param idUser the id of the user
* @param idMailingList the id of the mailing list we want to subscribe
* @param readingRight the value we want for the reading right of the user for this mailing list
* @param writingRight the value we want for the writing right of the user for this mailing list
*/
	public static void updateRRightsDB (int idUser, int idMailingList, int readingRight) {

		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("UPDATE rel SET droitLecture = "+ readingRight +" WHERE idUser = "+idUser+" AND idMailingList = "+idMailingList);

		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
	
/** Update the writing right of a user
* @param idUser the id of the user
* @param idMailingList the id of the mailing list we want to subscribe
* @param readingRight the value we want for the reading right of the user for this mailing list
* @param writingRight the value we want for the writing right of the user for this mailing list
*/
	public static void updateWRightsDB (int idUser, int idMailingList, int writingRight) {

		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("UPDATE rel SET droitEcriture = "+ writingRight +" WHERE idUser = "+idUser+" AND idMailingList = "+idMailingList);

		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
		
		
	/** Get the reading right of a user from the database for a mailinglist
	* @param idUser the id of the user
	* @param idMailingList the id of the mailing list
	* @return readingRight the value we want for the reading right of the user for this mailing list  
	*/
	public static int getUserReadingRightDB(int idUser, int idMailingList){
		Statement s;

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT droitLecture FROM rel WHERE idUser = '"+idUser +"' AND idMailingList = '"+idMailingList+"'");
			rs.next();
			return rs.getInt("droitLecture");
 
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return -1; 
	}
	
	/** Get the reading right of a user from the database for all the mailinglists
	* @param idUser the id of the user
	* @return readRights the value for the reading right of the user for all the mailinglists  
	*/	
	public static Rights getUserRRsDB(String username){
	
		Statement s;
		Rights readRights = new Rights ();

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT droitLecture, idMailingList FROM utilisateur u, rel r WHERE u.username = '" + username + "' AND u.idUser = r.idUser");
			while ( rs.next() ){
			
				readRights.createRR(rs.getInt("idMailingList"), rs.getInt("droitLecture"));
 
 			}
 		
 			return readRights;
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null;
	
	}
	
	
	/** Get the writing right of a user from the database for a mailinglists
	* @param idUser the id of the user
	* @param idMailingList the id of the mailing list
	* @return readRights the value for the writing right of the user for this mailing list  
	*/	
	public static int getUserWritingRightDB(int idUser, int idMailingList){
		Statement s;

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT droitEcriture FROM rel WHERE idUser = '"+idUser +"' AND idMailingList = '"+idMailingList+"'");
			rs.next();
			return rs.getInt("droitEcriture");
 
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return -1; 
	}

	
	/** Get the writing right of a user from the database for all the mailinglists
	* @param idUser the id of the user
	* @return readRights the value for the reading right of the user for all the mailinglists 
	*/	
	public static Rights getUserWRsDB(String username){
	
		Statement s;
		Rights writeRights = new Rights ();

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT  droitEcriture , idMailingList FROM utilisateur u, rel r WHERE u.username = '" + username + "' AND u.idUser = r.idUser");
			while ( rs.next() ){
			
				writeRights.createRR(rs.getInt("idMailingList"), rs.getInt("droitEcriture"));
 
 			}
 		
 			return writeRights;
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null;
	
	}
	


	//delete user's rights when a user is deleted
	public static void deleteRightUserDB(int idUser){
		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("DELETE FROM rel WHERE idUser = "+idUser);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	//delete user's rights when a mailinglist is deleted
	public static void deleteRightUserMailingDB(int idMailingList){
		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("DELETE FROM rel WHERE idMailingList = "+idMailingList);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
/**to create a Mailinglist in the database
* @param Mailinglist name
*/
	public static void insertMailinglistDB(String name){
		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("INSERT INTO mailinglist (name) VALUES('"+name+"')");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
/**To get all the mailing lists id from the database
* @return list of id
*/
	public static Liste getMailListsIdDB(){
		Statement s;
		Liste liste = new Liste ();

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT idMailingList FROM mailinglist");
			while ( rs.next() ){
			
				liste.createListe(rs.getInt("idMailingList"));
 
 			}
 		
 			return liste;
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null; 
	}
	
/**To get  the mailing list id from the database
* @param mailinglist name
* @return id of the mailinlist
*/
	public static int getMLIdDB(String name){
		Statement s;

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT idMailinglist FROM mailinglist WHERE name = '"+name+"'");
			rs.next();		 					
 			return rs.getInt("idMailingList") ;
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return -1; 
	}
	
/**To get all the mailing lists names from the database
* @return map of users
*/
	public static ListeString getMailListsNameDB(){
		Statement s;
		ListeString liste = new ListeString ();

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT name FROM mailinglist");
			while ( rs.next() ){
			
				liste.createListeString(rs.getString("name"));
 
 			}
 		
 			return liste;
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null; 
	}
		
	
//Email	
	
/**to insert an email in the database
* @param 
* @param 
* @param 
*/
	public static void insertEmailDB(int sender, int receiver, String title, String body){
		Statement s;		
		try{
			s = c.createStatement();
			System.out.println("insert");
			s.executeUpdate("INSERT INTO mail (idExpediteur, idRecepteur, titre, texte) VALUES('"+sender+"','"+receiver+"', '"+title+"', '"+body+"')");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
/**Delete a mail in the database
* @param id of the email we want to delete
*/
	public static void deleteMailDB(int id){
		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("DELETE FROM mail WHERE idMail = "+id);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
	
/**To get all the emails of a user from the database
* @param idReceiver : the id of the receiver == the user we are interested in
* @return map of emails
*/
	public static Emails getEmailsDB(int idReceiver){
		Statement s;
		Emails liste = new Emails();

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT idMail,idExpediteur,titre, texte, lu FROM mail WHERE idRecepteur = "+idReceiver);
			while ( rs.next() ){
			
				Email email = new Email(rs.getInt("idMail"), getUserNameDB(rs.getInt("idExpediteur")), getUserNameDB(idReceiver),rs.getString("titre"), rs.getString("texte"), rs.getInt("lu"));
				liste.createEmail(email);
 
 			}
 		
 			return liste;
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null; 
	}
		
/**To get an email from the database
* @param idMail : the id of the email
* @return email
*/
	public static Email getAEmailDB(int idMail){
		Statement s;

		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT idMail,idExpediteur, idRecepteur, titre, texte, lu FROM mail WHERE idMail = "+idMail);
			rs.next();			
			Email email = new Email(rs.getInt("idMail"),getUserNameDB(rs.getInt("idExpediteur")), getUserNameDB(rs.getInt("idRecepteur")),rs.getString("titre"), rs.getString("texte"), rs.getInt("lu"));
 		
 			return email;
 		
		}catch (SQLException e) {
			System.out.println(e.getMessage());

		}

		return null; 
	}
	
/**To get the status of an email
* @param sender 
* @param receiver
* @param titre
* @return 0 if not read 1 if read
*/
	public static int getReadDB(int sender, int receiver, String titre){
		Statement s;
		
		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT lu FROM mail WHERE idRecepteur = "+receiver+" AND idExpediteur ="+sender+" AND titre = '"+titre+"' ");
			rs.next();		
			
 			return rs.getInt("lu");
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return -1; 
	}
	
/**To get the body of an email
* @param sender 
* @param receiver
* @param titre
* @return the body
*/
	public static String getBodyDB(int sender, int receiver, String titre){
		Statement s;
		
		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT texte FROM mail WHERE idRecepteur = "+receiver+" AND idExpediteur ="+sender+" AND titre = '"+titre+"' ");
			rs.next();		
			
 			return rs.getString("texte");
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return null; 
	}
	
/**To get the id of an email
* @param sender 
* @param receiver
* @param titre
* @return the email id
*/
	public static int getEmailIdDB(int sender, int receiver, String titre){
		Statement s;
		
		try{

			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT idMail FROM mail WHERE idRecepteur = "+receiver+" AND idExpediteur ="+sender+" AND titre = '"+titre+"' ");
			rs.next();		
			
 			return rs.getInt("idMail");
 		
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return -1; 
	}
	
	
/** Set as read
* @param idUser the id of the user
* @param idMailingList the id of the mailing list we want to subscribe
* @param readingRight the value we want for the reading right of the user for this mailing list
* @param writingRight the value we want for the writing right of the user for this mailing list
*/
	public static void setReadDB(int idmail){
		Statement s;		
		try{
			s = c.createStatement();
			s.executeUpdate("UPDATE mail SET lu = 1 WHERE idMail = "+idmail);

		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
	

}
