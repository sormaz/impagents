package edu.ohiou.labimp.agent.task;

import edu.ohiou.labimp.agent.*;
import java.io.*;

public class PSTask extends IMPTask implements Serializable{

  private Object contents;

  public PSTask(Object contents) {
    this.contents = contents;
  }

  public int id () {
  return IMPTask.PS;
}

  public void setContents(Object contents){
    this.contents = contents;
  }
  public Object getContents(){
    return contents;
  }

  public Object execute() {
    //read file into char[]
    return null;
  }

}
