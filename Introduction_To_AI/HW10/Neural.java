import java.util.*;
public class Neural{
  public static void main(String[] args){
    int flag = Integer.valueOf(args[0]);
    if (flag ==100){
      double w1 = Double.parseDouble(args[1]);
      double w2 = Double.parseDouble(args[2]);
      double w3 = Double.parseDouble(args[3]);
      double w4 = Double.parseDouble(args[4]);
      double w5 = Double.parseDouble(args[5]);
      double w6 = Double.parseDouble(args[6]);
      double w7 = Double.parseDouble(args[7]);
      double w8 = Double.parseDouble(args[8]);
      double w9 = Double.parseDouble(args[9]);
      double x1 = Double.parseDouble(args[10]);
      double x2 = Double.parseDouble(args[11]);
      double ua = 1*w1 + x1*w2 + x2*w3;
      double va = Math.max(ua,0);
      double ub = 1*w4 + x1*w5 + x2*w6;
      double vb = Math.max(ub,0);
      double uc = 1*w7 + va*w8 + vb*w9;
      double vc = 1/(1+Math.pow(Math.E,-uc));
      System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f\n",ua,va,ub,vb,uc,vc);
    }
  }
}
