package edu.ohiou.labimp.agent;

import java.io.*;

public class FileReadTask extends IMPTask implements Serializable{
  public FileReadTask(Object contents, int taskID) {
    super(contents, taskID);
  }

  public Object execute() {
    File f = (File) contents;
    System.out.println("I am "+IMPTask.toString(id)+"Task - executing " + f.getAbsolutePath());
    byte [] bytes = new byte[ (int) f.length()];
//    FileReader reader = null;
    try {
//      reader = new FileReader(f);
      FileInputStream inputStream = new FileInputStream(f);
//      System.out.println("encoding is:" + reader.getEncoding());
      inputStream.read(bytes);
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
      return null;
    } catch (IOException ex1) {
      ex1.printStackTrace();
      return null;
    }
    System.out.println("byte");
    return bytes;
  }
}