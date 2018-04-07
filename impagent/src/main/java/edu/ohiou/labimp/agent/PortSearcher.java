package edu.ohiou.labimp.agent;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import edu.ohiou.imse.ise589.StreamClient;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.*;

public class PortSearcher {
  public static int FIRST_MIN = 132;
  public static int FIRST_MAX = 132;
  public static int SEC_MIN = 235;
  public static int SEC_MAX = 235;
  public static int THIRD_MIN = 16;
  public static int THIRD_MAX = 17;
  public static int FOURTH_MIN = 1;
  public static int FOURTH_MAX = 255;
  public static int PORT_MIN = 1101;
  public static int PORT_MAX = 1121;
  int firstMin = FIRST_MIN, firstMax = FIRST_MAX, secMin = SEC_MIN,
      secMax = SEC_MAX,
      thirdMin = THIRD_MIN, thirdMax = THIRD_MAX,
      fourthMin = FOURTH_MIN, fourthMax = FOURTH_MAX,
      portMin = PORT_MIN, portMax = PORT_MAX;

  public PortSearcher() {
  }

  public PortSearcher(int fMin, int fMax, int sMin, int sMax, int tMin,
                      int tMax, int foMin, int foMax, int pMin, int pMax) {
    firstMin = fMin;
    firstMax = fMax;
    secMin = sMin;
    secMax = sMax;
    thirdMin = tMin;
    thirdMax = tMax;
    fourthMin = foMin;
    fourthMax = foMax;
    portMin = pMin;
    portMax = pMax;

  }

  protected StreamClient search(int taskID) throws Exception {
    System.err.println("search - start");
    StreamClient answer = null;
    StreamClient client = new StreamClient();
    int att = 0;

    for (int first = firstMin; first <= firstMax; first++) {
      for (int second = secMin; second <= secMax; second++) {
        for (int third = thirdMin; third <= thirdMax; third++) {
          for (int fourth = fourthMin; fourth <= fourthMax; fourth++) {
            String host = "" + first + "." + second + "." + third + "." + fourth;
            System.out.println("looking for host " + host);
            for (int port = portMin; port <= portMax; port++) {
              try {
                System.out.println("looking for host " + host + ":" + port);
                client.connect(host, port);
                Object object = client.send(IMPAgentInfo.INFO_REQUEST, true); //true to get reply
                if (object != null && object instanceof IMPAgentInfo) {
                  IMPAgentInfo info = (IMPAgentInfo) object;
                  System.out.println("connection at host " + host + ":" + port + " -> " + info.id);
                  if (info.id == taskID) {
                    answer = client; //////////////////////CHECK QUEUE SIZE ?
                  }
                  else {
                    client.disconnect();
                  }
                }
              }
              catch (IOException ex) {
                System.out.println("exception at host " + host + ":" + port + " -> " + ex.getMessage());
              }
            }

          }

        }

      }

    }

    if (answer == null)
      System.out.println("Can't find available " + IMPTask.toString(taskID) +
                         " agent at this time. attempt " + att);

    System.err.println("search - out");

    return answer;
  }

  public static void main(String[] args) {
//    PortSearcher portSearcher1 = new PortSearcher(132,132,235,235,17,17,54,54,1201,1221);
    PortSearcher portSearcher1 = new PortSearcher(192,192,168,168,1,1,4,4,1201,1221);
    Calendar c = new GregorianCalendar();
    System.out.println("before" + c.getTime().toString());
    try {
      portSearcher1.search(900);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    Calendar after = new GregorianCalendar();
    System.out.println("after" + after.getTime().toString());
    System.exit(0);
  }
}
