package edu.ohiou.imse.ise589;

/**
 * <p>Title: ise 589 project for messge center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */

import java.awt.Color;

public interface Connectable {

  public static Color OK_COLOR = Color.green;
  public static Color ERROR_COLOR = Color.red;
  public static Color INACTIVE_COLOR = Color.blue;

  public Object receive (Object o);

  public void updateStatus (boolean isRunning);
  public void updateStatus (boolean isRunning, int port);

}