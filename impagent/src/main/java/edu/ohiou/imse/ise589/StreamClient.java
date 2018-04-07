package edu.ohiou.imse.ise589;


/**
 * <p>Title: ise 589 project for messge center, spring 02/03</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ohio University</p>
 * @author Dusan N. Sormaz
 * @version 1.0
 */
import edu.ohiou.mfgresearch.labimp.basis.ViewObject;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StreamClient extends ViewObject {
  Socket socket = null;
  InputStreamReader isr = null;
  BufferedReader inBuffer = null;
  PrintWriter out  = null;
  Action action;
  int localPort=1111;
 ObjectInputStream inStream;
 
 public StreamClient(int localPort){
	 this();
	 this.localPort=localPort;
 }
 
 

public StreamClient() {
    this (new AbstractAction () {
      public void actionPerformed (ActionEvent e) {
    	  
      }
    } );
  }

  public StreamClient(Action action) {
    this.action = action;
  }


/*
 * @ coded by Suvo
 */
  public void connect (String hostName, int port) throws IOException {
	//InetAddress remoteAddr = InetAddress.getByName("0.0.0.0");
	InetAddress localAddr = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
    socket =  new Socket (hostName, port, localAddr, localPort);
//    socket =  new Socket (hostName, port);
    System.out.println(InetAddress.getLocalHost().getHostAddress());
/*	  socket= new Socket (hostName, port);*/
    out = new PrintWriter (socket.getOutputStream(), true);
    inStream = new ObjectInputStream(socket.
	getInputStream());
    action.actionPerformed(null);
  }
  /**
   * david changed
   */
  public void disconnect () {
    try {
      send("QUIT"); // tell server to break connection
      socket.close();
    } catch (IOException ex) {ex.printStackTrace();}
  }

  public Object send (Object o) {
    return send (o,false);
  }

  public Object send (Object o, boolean needReply) {
    Object reply = null;
    try {
      ObjectOutputStream outStream = new ObjectOutputStream (socket.getOutputStream());
      outStream.writeObject(o);
      outStream.flush();
      if (needReply) {
	while (true) {
	  reply = inStream.readObject();
	  System.out.println("object " + reply);
	  return reply;
	}
      } else return null;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }

  }

  public void init () {
    panel = new ClientPanel();
  }

  public JPanel createPanel () {
    return panel = new ClientPanel();
  }

  public static void main(String[] args) {
    StreamClient streamClient1 = new StreamClient();
    streamClient1.display();
  }

  class ClientPanel extends JPanel {
    JLabel statusLabel= new JLabel();
    JTextField portField;
    JTextField hostField;
    JButton connectButton;
    ClientPanel () {
      init();
    }

    public void init() {
      try  {
	jbInit();
      }
      catch(Exception e)  {
	e.printStackTrace();
      }
    }

    public void jbInit () {
      setLayout(new BorderLayout());
      setBorder (BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "Client"));
      statusLabel.setForeground(Connectable.INACTIVE_COLOR);
      statusLabel.setToolTipText("shows current client connected");
      statusLabel.setBackground(Color.white);
      statusLabel.setText("Not connected to server");
      connectButton = new JButton ("Connect");
      connectButton.addActionListener(new ActionListener() {
	public void actionPerformed (ActionEvent e) {
	  try {
	    connect(hostField.getText(),
				  Integer.parseInt(portField.getText()));

	    System.out.println ("Passed connection, should be connected" + socket);
	    System.out.println(socket.getLocalPort() + "" + socket.getLocalAddress());
	    statusLabel.setForeground(Connectable.OK_COLOR);
	    action.actionPerformed(e);
	    statusLabel.setText("connected" + socket.toString());
	  }
	  catch (NumberFormatException nfe) {
		    System.out.println ("Port format is not valid. " + nfe);
		    statusLabel.setForeground(Connectable.ERROR_COLOR);
		    statusLabel.setText("Enter correct port number");
		  }

	  catch (IllegalArgumentException iae) {
		    System.out.println ("Port number is out of range. " + iae);
	    statusLabel.setForeground(Connectable.ERROR_COLOR);
	    statusLabel.setText("Port " + portField.getText() + " is out of range (0001-9999)");
	  }
	  catch  (IOException ex) {
	    System.out.println ("Could not get connection. " + ex);
	    ex.printStackTrace();
	    statusLabel.setForeground(Connectable.ERROR_COLOR);
	    statusLabel.setText("Could not get connection");
	  }

	}
      });
      JLabel hostLabel = new JLabel ("Host");
      JLabel portLabel = new JLabel ("Port");
      portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      hostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      portField = new JTextField();
      hostField = new JTextField("localhost");
      portField.addFocusListener(new FocusAdapter() {
	public void focusLost (FocusEvent e) {
	}
      });
      JPanel leftPanel = new JPanel();
      leftPanel.setLayout (new GridLayout (3,1));
      leftPanel.add(hostLabel);
      leftPanel.add(portLabel);
      leftPanel.add(connectButton);
      JPanel rightPanel = new JPanel();
      rightPanel.setLayout (new GridLayout (3,1));
      rightPanel.add(hostField);
      rightPanel.add(portField);
      add(statusLabel, BorderLayout.SOUTH);

      add(leftPanel, BorderLayout.WEST);
      add(rightPanel, BorderLayout.CENTER);
    }


  }

}