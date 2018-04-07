package edu.ohiou.labimp.agent;

import javax.swing.JPanel;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class BirthAgent extends ServiceAgent {
  public BirthAgent() {
  }
  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.BA,state.getCurrent(),name,0);
  }
  public Object serviceTask(IMPTask parm1) {
    /**@todo Implement this edu.ohiou.labimp.agent.ServiceAgent abstract method*/
    throw new java.lang.UnsupportedOperationException("Method serviceTask() not yet implemented.");
  }
@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}
}