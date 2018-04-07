package edu.ohiou.labimp.agent;

import java.io.*;
import java.util.*;

public class FileWriteTask extends IMPTask{

  TaskAgent agent;

  public FileWriteTask() {
    this(null);
  }

  public FileWriteTask(Object contents) {
    super(contents, -1);
  }

  public FileWriteTask(TaskAgent agent, String fileName) {
    this.agent = agent;
    this.contents = fileName;
  }

  public Object execute() {
    File file = new File ((String) contents);
    file.exists(); //check if file exists??
    try {
      file.createNewFile();
      FileWriter writer = new FileWriter(file);
      FileOutputStream outputStream = new FileOutputStream (file);
      outputStream.write( (byte[]) agent.getContents());
      outputStream.flush();
      outputStream.close();
    }
    catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    return file;
  }

}