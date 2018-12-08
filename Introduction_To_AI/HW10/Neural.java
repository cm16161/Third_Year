import java.util.*;
public class Neural{
  public static double calculateUA(double w1, double w2, double w3, double x1, double x2){
    double ua = 1*w1 + x1*w2 + x2*w3;
    return ua;
  }

  public static double calculateUB(double w4, double w5, double w6, double x1, double x2){
    double ub = 1*w4 + x1*w5 + x2*w6;
    return ub;
  }

  public static double calculateUC(double w7, double w8, double w9, double va, double vb){
    double uc = 1*w7 + va*w8 + vb*w9;
    return uc;
  }

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
      // double ua = 1*w1 + x1*w2 + x2*w3;
      double ua = calculateUA(w1, w2, w3, x1, x2);
      double va = Math.max(ua,0);
      // double ub = 1*w4 + x1*w5 + x2*w6;
      double ub = calculateUB(w4,w5,w6,x1,x2);
      double vb = Math.max(ub,0);
      // double uc = 1*w7 + va*w8 + vb*w9;
      double uc = calculateUC(w7,w8,w9,va,vb);
      double vc = 1/(1+Math.pow(Math.E,-uc));
      System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f\n",ua,va,ub,vb,uc,vc);
    }

    if (flag ==200){
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
      double y = Double.parseDouble(args[12]);
      // double ua = 1*w1 + x1*w2 + x2*w3;
      double ua = calculateUA(w1, w2, w3, x1, x2);
      double va = Math.max(ua,0);
      // double ub = 1*w4 + x1*w5 + x2*w6;
      double ub = calculateUB(w4,w5,w6,x1,x2);
      double vb = Math.max(ub,0);
      // double uc = 1*w7 + va*w8 + vb*w9;
      double uc = calculateUC(w7,w8,w9,va,vb);
      double vc = 1/(1+Math.pow(Math.E,-uc));
      double E = 0.5*Math.pow((vc - y),2);
      double dEdVc = vc - y;
      double dEdUc = dEdVc*vc*(1-vc);
      System.out.printf("%.5f %.5f %.5f\n",E,dEdVc, dEdUc);
    }

    if (flag == 300){
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
      double y = Double.parseDouble(args[12]);
      // double ua = 1*w1 + x1*w2 + x2*w3;
      double ua = calculateUA(w1, w2, w3, x1, x2);
      double va = Math.max(ua,0);
      // double ub = 1*w4 + x1*w5 + x2*w6;
      double ub = calculateUB(w4,w5,w6,x1,x2);
      double vb = Math.max(ub,0);
      // double uc = 1*w7 + va*w8 + vb*w9;
      double uc = calculateUC(w7,w8,w9,va,vb);
      double vc = 1/(1+Math.pow(Math.E,-uc));
      double E = 0.5*Math.pow((vc - y),2);
      double dEdVc = vc - y;
      double dEdUc = dEdVc*vc*(1-vc);

      double dEdVa = w8*dEdUc;
      double dVadUa;
      if(ua >= 0)dVadUa = 1;
      else dVadUa = 0;
      double dEdUa = dEdVa*dVadUa;

      double dEdVb = w9*dEdUc;
      double dVbdUb;
      if(ub >= 0)dVbdUb = 1;
      else dVbdUb = 0;
      double dEdUb = dEdVb*dVbdUb;

      System.out.printf("%.5f %.5f %.5f %.5f \n",dEdVa,dEdUa,dEdVb,dEdUb);
    }

    if (flag ==400){
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
      double y = Double.parseDouble(args[12]);
      // double ua = 1*w1 + x1*w2 + x2*w3;
      double ua = calculateUA(w1, w2, w3, x1, x2);
      double va = Math.max(ua,0);
      // double ub = 1*w4 + x1*w5 + x2*w6;
      double ub = calculateUB(w4,w5,w6,x1,x2);
      double vb = Math.max(ub,0);
      // double uc = 1*w7 + va*w8 + vb*w9;
      double uc = calculateUC(w7,w8,w9,va,vb);
      double vc = 1/(1+Math.pow(Math.E,-uc));
      double E = 0.5*Math.pow((vc - y),2);
      double dEdVc = vc - y;
      double dEdUc = dEdVc*vc*(1-vc);

      double dEdVa = w8*dEdUc;
      double dVadUa;
      if(ua >= 0)dVadUa = 1;
      else dVadUa = 0;
      double dEdUa = dEdVa*dVadUa;

      double dEdVb = w9*dEdUc;
      double dVbdUb;
      if(ub >= 0)dVbdUb = 1;
      else dVbdUb = 0;
      double dEdUb = dEdVb*dVbdUb;

      double dEdw1 = 1*dEdUa;
      double dEdw2 = x1*dEdUa;
      double dEdw3 = x2*dEdUa;
      double dEdw4 = 1*dEdUb;
      double dEdw5 = x1*dEdUb;
      double dEdw6 = x2*dEdUb;
      double dEdw7 = 1*dEdUc;
      double dEdw8 = va*dEdUc;
      double dEdw9 = vb*dEdUc;

      System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n",dEdw1,dEdw2,dEdw3,dEdw4,dEdw5,dEdw6,dEdw7,dEdw8,dEdw9);

    }

    if(flag == 500){
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
      double y = Double.parseDouble(args[12]);
      double aeta = Double.parseDouble(args[13]);

      // double ua = 1*w1 + x1*w2 + x2*w3;
      double ua = calculateUA(w1, w2, w3, x1, x2);
      double va = Math.max(ua,0);
      // double ub = 1*w4 + x1*w5 + x2*w6;
      double ub = calculateUB(w4,w5,w6,x1,x2);
      double vb = Math.max(ub,0);
      // double uc = 1*w7 + va*w8 + vb*w9;
      double uc = calculateUC(w7,w8,w9,va,vb);
      double vc = 1/(1+Math.pow(Math.E,-uc));
      double E = 0.5*Math.pow((vc - y),2);
      double dEdVc = vc - y;
      double dEdUc = dEdVc*vc*(1-vc);

      double dEdVa = w8*dEdUc;
      double dVadUa;
      if(ua >= 0)dVadUa = 1;
      else dVadUa = 0;
      double dEdUa = dEdVa*dVadUa;

      double dEdVb = w9*dEdUc;
      double dVbdUb;
      if(ub >= 0)dVbdUb = 1;
      else dVbdUb = 0;
      double dEdUb = dEdVb*dVbdUb;

      double dEdw1 = 1*dEdUa;
      double dEdw2 = x1*dEdUa;
      double dEdw3 = x2*dEdUa;
      double dEdw4 = 1*dEdUb;
      double dEdw5 = x1*dEdUb;
      double dEdw6 = x2*dEdUb;
      double dEdw7 = 1*dEdUc;
      double dEdw8 = va*dEdUc;
      double dEdw9 = vb*dEdUc;

      double new_w1 = w1 - aeta*dEdw1;
      double new_w2 = w2 - aeta*dEdw2;
      double new_w3 = w3 - aeta*dEdw3;
      double new_w4 = w4 - aeta*dEdw4;
      double new_w5 = w5 - aeta*dEdw5;
      double new_w6 = w6 - aeta*dEdw6;
      double new_w7 = w7 - aeta*dEdw7;
      double new_w8 = w8 - aeta*dEdw8;
      double new_w9 = w9 - aeta*dEdw9;

      double new_ua = calculateUA(new_w1, new_w2, new_w3, x1, x2);
      double new_va = Math.max(new_ua,0);
      // double ub = 1*w4 + x1*w5 + x2*w6;
      double new_ub = calculateUB(new_w4,new_w5,new_w6,x1,x2);
      double new_vb = Math.max(new_ub,0);
      // double uc = 1*w7 + va*w8 + vb*w9;
      double new_uc = calculateUC(new_w7,new_w8,new_w9,new_va,new_vb);
      double new_vc = 1/(1+Math.pow(Math.E,-new_uc));
      double new_E = 0.5*Math.pow((new_vc - y),2);
      System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n",w1,w2,w3,w4,w5,w6,w7,w8,w9);
      System.out.printf("%.5f\n", E);
      System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f %.5f\n",new_w1,new_w2,new_w3,new_w4,new_w5,new_w6,new_w7,new_w8,new_w9);
      System.out.printf("%.5f\n", new_E);


    }

  }
}
