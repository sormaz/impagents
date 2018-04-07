package edu.ohiou.labimp.agent.task;

import edu.ohiou.labimp.agent.*;
import java.io.*;

public class UGTask extends FileReadTask implements Serializable {

  public UGTask(){
    this(null);
  }

  public UGTask(Object contents) {
    super(contents, IMPTask.UG);
  }
}