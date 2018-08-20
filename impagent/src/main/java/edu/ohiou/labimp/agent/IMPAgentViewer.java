package edu.ohiou.labimp.agent;

import edu.ohiou.mfgresearch.labimp.basis.ViewObject;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class IMPAgentViewer extends ViewObject{

  private IMPAgent agent;
  private LinkedList drawList=new LinkedList();
  public static String DEFAUL_FILE_DIR = "C:\"";
  public IMPAgentViewer () {

  }
  public IMPAgentViewer(IMPAgent a){
    agent = a;
  }

  public static Class [] getConstructorParamTypes () {
    Class [] classes = new Class [1];
    return classes;
  }

  public void init () {
    if (agent == null) {
      try {
        Class agentClass = Class.forName(applet.getParameter("IMP_AGENT",
            "edu.ohiou.labimp.agent.agent.TXAgent"));

        agent = (IMPAgent) agentClass.newInstance();
//        agent.setName("Applet");
      }
      catch (IllegalAccessException ex) {
        ex.printStackTrace();
      }
      catch (InstantiationException ex) {
        ex.printStackTrace();
      }
      catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      }
    }
    panel = new JPanel ();
    panel.setLayout(new BorderLayout());
    panel.add(new JLabel(agent.getName(), SwingConstants.CENTER), BorderLayout.NORTH);
    panel.add(agent.getPanel(), BorderLayout.CENTER);
  }

  
  public String toString() {
    return "Viewer for " + agent.getName() + " agent";
  }
  
  //added by JING HUANG 
  public LinkedList geetDrawList(){
	  return drawList;
  }
  
  public void setDrawList(LinkedList dl){
	  drawList=dl;
  }
}
