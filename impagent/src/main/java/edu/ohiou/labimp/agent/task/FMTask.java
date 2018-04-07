package edu.ohiou.labimp.agent.task;

import edu.ohiou.labimp.agent.*;
import java.io.*;

public class FMTask extends IMPTask implements Serializable{

  private Object contents;

  public FMTask(Object contents) {
    this.contents = contents;
  }

  public Object execute() {
    return contents;
  }

}