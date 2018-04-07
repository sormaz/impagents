package edu.ohiou.labimp.agent.task;

import edu.ohiou.labimp.agent.*;
import java.io.*;

public class IETask extends FileReadTask implements Serializable{

  public IETask() {
    this(null);
  }

  public IETask(Object contents) {
    super(contents, IMPTask.IE);
  }

}
