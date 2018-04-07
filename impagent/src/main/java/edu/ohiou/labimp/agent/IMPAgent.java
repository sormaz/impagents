package edu.ohiou.labimp.agent;

import java.util.*;
import edu.ohiou.imse.ise589.*;
import edu.ohiou.mfgresearch.labimp.table.ClassNameRenderer;

import javax.swing.*;
import java.io.*;
import java.net.*;

public abstract class IMPAgent implements Runnable, Serializable {

  protected String name;
  protected IMPState state = new IMPState(IMPState.UNINITIATED);
  public final static int TIMEOUT = 10000;
  transient Thread runner= new Thread (this);
  public static Properties properties = new Properties();
  

  static {
	    
	    try {
	      URL resourcePropertyURL = IMPAgent.class.getResource(
	    		  "agent.properties");
	      properties.load(resourcePropertyURL.openStream());
	      System.out.println(
	      "Agent properties loaded from IMPAgent resource, " + resourcePropertyURL);
	    }
	    catch (Exception ex) {
	      System.err.println(
	      "Agent properties not loaded from IMPAgent resource file.");
	    }
	    
	    try {
	      File propertyFile = new File(System.getProperty("user.home"),
	    		  "agent.properties");
	      properties.load(new FileInputStream(propertyFile));
	      System.out.println(
	      "Agent properties loaded from user home, file " + propertyFile);
	    }
	    catch (Exception ex) {
	      System.err.println(
	      "Agent properties not loaded from user home.");
	    }
	    
	    try {
	      File propertyFile = new File(System.getProperty("user.dir"),
	    		  "agent.properties");
	      properties.load(new FileInputStream(propertyFile));
	      System.out.println(
	      "Agent properties loaded from current folder, file " + propertyFile);
	    }
	    catch (Exception ex) {
	      System.err.println(
	      "Agent properties not loaded from current folder.");
	    }
	  }
  
//  static {
//    try {
//      properties.load(IMPAgent.class.getResourceAsStream(
//	  "agent.properties"));
//    }
//    catch (IOException ex) {
//      ex.printStackTrace();
//    }
//  }

  public IMPAgent() {
    this (getLocalHost());
  }

  public IMPAgent(String inName) {
    name = inName;
    runner = new Thread(this);
    runner.start();
  }

  public void display (String title) {
    initialize ();
    IMPAgentViewer viewer = new IMPAgentViewer (this);
    viewer.display(title);
  }
  
  public  void display() {
	  display(toString()); 
  }

  public static String getLocalHost() {
    String localHost = "none";
    try {
	localHost = InetAddress.getLocalHost().toString();
      } catch (UnknownHostException ex) {}
    return localHost;
  }

  public void setState(IMPState state){ this.state = state; }
  public IMPState getState(){ return state; }
  void setName(String name){this.name = name;}
  public String getName(){return name;}

  abstract public IMPAgentInfo getInfo ();
  abstract public JPanel getPanel();
  public void initialize(){}
  public String toString () {
	  return ClassNameRenderer.getShortClassName (this);
	  }
  
	 public final static Object ccloneObject(Object obj){  
		 ByteArrayOutputStream  byteOut = new ByteArrayOutputStream();    
		 ObjectOutputStream out;
		 Object o=null;
		try {
			out = new ObjectOutputStream(byteOut);
			out.writeObject(obj);           
			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());    
			ObjectInputStream in =new ObjectInputStream(byteIn);    
			try {
				o=in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		return o; 
		 
}  

}
