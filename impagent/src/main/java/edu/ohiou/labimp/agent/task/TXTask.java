package edu.ohiou.labimp.agent.task;

import edu.ohiou.labimp.agent.*;
import java.io.*;

public class TXTask extends FileReadTask implements Serializable{

  public TXTask() {
    this(null);
  }

  public TXTask(Object contents) {
    super(contents, IMPTask.TX);
  }

}