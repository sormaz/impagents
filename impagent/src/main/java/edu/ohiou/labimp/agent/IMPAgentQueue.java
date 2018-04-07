package edu.ohiou.labimp.agent;

import java.util.Vector;

public class IMPAgentQueue extends Vector {
  public IMPAgentQueue() {
  }

  public void join(IMPAgent agent){
    add(agent);
  }

  public IMPAgent peek(){
    try { return (IMPAgent) firstElement(); }
    catch (Exception ex) { return null; }
  }

  public IMPAgent next(){
    try { return (IMPAgent) remove(0); }
    catch (Exception ex) {
      return null;
    }
  }

}