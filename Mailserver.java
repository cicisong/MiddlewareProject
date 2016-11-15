package Server;

import javax.xml.ws.Endpoint;
import java.sql.*;
import java.lang.String;
import java.util.Properties;


public class Mailserver {
  
    public static void main(String args[]) throws Exception {

		Connection c = DirectoryManager.initConnection();
		Publisher.publish();
		System.out.println("It works !");
    c.close();
		System.out.println("Server exiting");
      System.exit(0);
    }
}

