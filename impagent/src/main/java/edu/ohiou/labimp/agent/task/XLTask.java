package edu.ohiou.labimp.agent.task;

import edu.ohiou.labimp.agent.*;
import java.io.*;

public class XLTask extends FileReadTask implements Serializable {

  public XLTask() {
    this(null);
  }

  public XLTask(Object contents) {
    super(contents, IMPTask.XL);
  }
}
