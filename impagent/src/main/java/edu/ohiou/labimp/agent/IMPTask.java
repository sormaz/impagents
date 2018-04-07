package edu.ohiou.labimp.agent;

public abstract class IMPTask implements java.io.Serializable{

  public static final int BA = 10;
  public static final int PA = 100;
  public static final int UG = 200;
  public static final int FM = 300;
  public static final int PS = 400;
  public static final int MC = 500;
  public static final int TX = 600;
  public static final int XL = 700;
  public static final int FA = 800;
  public static final int IE = 900;
  public static final int UNKNOWN = -1;
  public static final int GA = 1000;
  public static final int DR = 1100;
  public static final int DC = 1200;
  public static final int UN = 1300;
  public static final int IN = 1400;
  public static final int DI = 1500;
  protected int id;
  protected Object contents;

  public IMPTask(){
    this(null,-1);
  }

  public IMPTask(Object c, int id){
    contents = c;
    this.id = id;
  }

  public Object execute(){return contents;}

  public int id() {
    return id;
  }

  public void setContents(Object contents){
    this.contents = contents;
  }
  public Object getContents(){
    return contents;
  }


  public String toString(){
    return toString(id);
  }

  public static String toString(int id) {
    switch (id) {
      case BA:
        return "BA";
      case PA:
        return "PA";
      case UG:
        return "UG";
      case FM:
        return "FM";
      case PS:
        return "PS";
      case MC:
        return "MC";
      case TX:
        return "TX";
      case XL:
        return "XL";
      case FA:
        return "FA";
      case IE:
        return "IE";
      case DR:
          return "DR";
      case DC:
          return "DC";
      case GA:
          return "GA";
      case UN:
          return "UN";
      case IN:
          return "IN";    
      case DI:
          return "DI";       
      default:
        return "-NONE-";
    }
  }
}