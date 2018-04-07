package edu.ohiou.labimp.agent;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class IMPAgentInfo implements Serializable {

  public int id;
  public int state;
  public String name;
  public int queueSize;
  public static final String INFO_REQUEST = "info";

  public IMPAgentInfo(int id, int state, String name, int queueSize) {
    this.id = id;
    this.state = state;
    this.name = name;
    this.queueSize = queueSize;
  }

  public static void main(String [] args){
    System.out.print("from IMPAgentInfo");
  }
}