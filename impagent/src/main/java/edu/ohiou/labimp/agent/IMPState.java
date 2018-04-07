package edu.ohiou.labimp.agent;

public class IMPState implements java.io.Serializable{
  public static final int UNINITIATED = 0; //initial value for all agents in IMPAgent line 10
  public static final int IDLE = 1;//for ServiceAgent in ServiceAgent line 59
  public static final int BUSY = 2;//for ServiceAgent in ServiceAgent line 56
  public static final int WAITING = 3; //in queue or for file, for PartAgent in ServiceAgent line 36
  public static final int IN_TRANSIT = 4;//for PartAgent in PartAgent line 41
  public static final int IN_SERVICE = 5;//for PartAgent in ServiceAgent line 54
  public static final int CONTENT_SET_NOW_SEARCHING = 6;//for PartAgent in PartAgent line 146
  public static final int COMPLETE = 7;//for PartAgent in PartAgent line 146

  private int current;

  public IMPState(int state){current = state;}

  public void setCurrent(int state){current = state;}

  public int getCurrent(){return current;}

  public String toString(){return toString(current);}

  public static String toString(int state){
    switch(state){
      case UNINITIATED:  return "UNINITIATED";
      case IDLE:  return "IDLE";
      case BUSY:  return "BUSY";
      case WAITING:   return "WAITING";
      case IN_TRANSIT:  return "IN_TRANSIT";
      case IN_SERVICE:  return "IN_SERVICE";
      case CONTENT_SET_NOW_SEARCHING: return "CONTENT_SET_NOW_SEARCHING";
      case COMPLETE: return "COMPLETE";
      default:  return "-NONE-";
    }
  }
}