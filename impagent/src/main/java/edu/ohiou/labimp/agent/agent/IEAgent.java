package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import javax.swing.*;

import java.io.*;

public class IEAgent
    extends ServiceAgent {
  public IEAgent() {
  }

  public IEAgent(String name, int port) {
    super(name, port);
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.IE, state.getCurrent(), name,
                            agentQueue.size());
  }

  public Object serviceTask(IMPTask task) {
    System.out.println("Processing IE task...");
    File file = (File) task.execute(); //executing FileWriteTask
    System.out.println("file " + file.getAbsolutePath());
    String command =
        "\"C:\\Program Files\\Internet Explorer\\iexplore.exe\" \"" +
        file.getAbsolutePath() + "\"";
    System.out.println("command " + command);
    try {
      Runtime.getRuntime().exec(command);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    return file;
  }

  public static void main(String[] args) {
    IEAgent agent = new IEAgent("ie", 0);
    agent.display();
  }



}