import java.util.*;
public class Ice{

public static double calculateMSEB0(int[] y, int[] x, double beta0, double beta1){
  double mseB0 =0;
  for (int i = 0;i<y.length;i++){
    mseB0 += beta0 + (beta1*x[i]) - y[i];
  }
  mseB0 = 2*mseB0 / (double)y.length;
  return mseB0;
}

public static double calculateMSEB1(int[] y, int[] x, double beta0, double beta1){
  double mseB1 = 0;
  for (int i = 0;i<y.length;i++){
    mseB1 += (beta0 + (beta1*x[i]) - y[i])*x[i];
  }
  mseB1 = 2*mseB1 / (double)y.length;
  return mseB1;
}

public static double calculateMSE(int[] y, int[] x, double beta0, double beta1){
  double mse = 0;
  for(int i = 0; i <y.length; i++){
    mse += Math.pow((beta0 + beta1*x[i] - y[i]),2);
  }
  mse = mse / (double) y.length;
  return mse;
}

public static double calculateXMean(int[] x){
  double xMean = 0;
  // double yMean = 0;
  for(int i =0; i< x.length; i++){
    xMean += x[i];
    // yMean += y[i];
  }
  xMean = xMean / (double)x.length;
  // yMean = yMean / (double)y.length;
  return xMean;
}

public static double calculateYMean(int[] y){
  // double xMean = 0;
  double yMean = 0;
  for(int i =0; i< y.length; i++){
    // xMean += x[i];
    yMean += y[i];
  }
  // xMean = xMean / (double)x.length;
  yMean = yMean / (double)y.length;
  return yMean;
}

public static double calculateBeta1Hat(int[] y, int[] x, double xMean, double yMean){
  double beta1Hat = 0;
  double sumXcenterSquared = 0;
  double sumXcenterYcenter = 0;
  // double sumYcenter = 0;
  for(int i = 0; i<x.length; i++){
    sumXcenterYcenter += (x[i] - xMean)*(y[i] - yMean);
    sumXcenterSquared += Math.pow((x[i]-xMean),2);
  }
  beta1Hat = sumXcenterYcenter / sumXcenterSquared;
  return beta1Hat;
}

public static double calculateBeta0Hat(double yMean, double beta1Hat, double xMean){
  double beta0Hat = yMean - beta1Hat*xMean;
  return beta0Hat;
}

public static void main(String[] args) {
  int flag = Integer.valueOf(args[0]);
  String s = "118,151,121,96,110,117,132,104,125,118,125,123,110,127,131,99,126,144,136,126,91,130,62,112,99,161,78,124,119,124,128,131,113,88,75,111,97,112,101,101,91,110,100,130,111,107,105,89,126,108,97,94,83,106,98,101,108,99,88,115,102,116,115,82,110,81,96,125,104,105,124,103,106,96,107,98,65,115,91,94,101,121,105,97,105,96,82,116,114,92,98,101,104,96,109,122,114,81,85,92,114,111,95,126,105,108,117,112,113,120,65,98,91,108,113,110,105,97,105,107,88,115,123,118,99,93,96,54,111,85,107,89,87,97,93,88,99,108,94,74,119,102,47,82,53,115,21,89,80,101,95,66,106,97,87,109,57,87,117,91,62,65,94";
  String[] buffer = s.split(",");
  int[] y;
  y = new int[buffer.length];
  for(int i =0; i<y.length;i++){
    y[i] = Integer.parseInt(buffer[i]);
  }

  int[] x;
  x = new int[buffer.length];
  int base = 1855;
  for(int i = 0; i<x.length;i++){
    x[i] = i+base;
  }

  if (flag == 100){
    for(int i  =0; i<y.length;i++){
      System.out.println(x[i]+" "+y[i]);
    }
  }

  else if (flag == 200){
    System.out.println(y.length);
    int total = 0;
    for(int i = 0; i<y.length;i++){
      total+= y[i];
    }
    double mean = (double)total / (double) y.length;
    System.out.printf("%.2f\n",mean);
    double sigma = 0;
    for(int i =0; i<y.length;i++){
      sigma+= Math.pow((y[i] - mean),2);
    }
    sigma = sigma / (double) total-1;
    System.out.printf("%.2f\n",sigma);
  }

  else if (flag == 300){
    double beta0 = Float.parseFloat(args[1]);
    double beta1 = Float.parseFloat(args[2]);
    double mse = calculateMSE(y,x,beta0,beta1);
    // for(int i = 0; i <y.length; i++){
      // mse += Math.pow((beta0 + beta1*x[i] - y[i]),2);
    // }
    // mse = mse / (double) y.length;
    System.out.printf("%.2f\n",mse);
  }
else if (flag == 400){
    double beta0 = Float.parseFloat(args[1]);
    double beta1 = Float.parseFloat(args[2]);
    double mseB0 = calculateMSEB0(y,x,beta0,beta1);
    double mseB1 = calculateMSEB1(y,x,beta0,beta1);;
    // for (int i = 0;i<y.length;i++){
      // mseB0 += beta0 + (beta1*x[i]) - y[i];
      // mseB1 += (beta0 + (beta1*x[i]) - y[i])*x[i];
    // }
    System.out.printf("%.2f\n",mseB0);
    System.out.printf("%.2f\n",mseB1);
}

else if (flag == 500){
  double aeta = Float.parseFloat(args[1]);
  int t = Integer.valueOf(args[2]);
  double beta0 = 0;
  double newbeta0 = 0;
  double beta1 = 0;
  double newbeta1 = 0;
  for (int i =0; i<t; i++){
    newbeta0 = beta0 - aeta*(calculateMSEB0(y,x,beta0,beta1));
    newbeta1 = beta1 - aeta*(calculateMSEB1(y,x,beta0,beta1));
    beta0 = newbeta0;
    beta1 = newbeta1;
    System.out.printf("%d %.2f %.2f %.2f \n",i+1,beta0,beta1,calculateMSE(y,x,beta0,beta1));
  }
}

else if (flag == 600){
  double xMean = calculateXMean(x);
  double yMean = calculateYMean(y);
  // for(int i =0; i< x.length; i++){
    // xMean += x[i];
    // yMean += y[i];
  // }
  // xMean = xMean / (double)x.length;
  // yMean = yMean / (double)y.length;
  double beta1Hat = calculateBeta1Hat(y,x,xMean, yMean);
  // double sumXcenterSquared = 0;
  // double sumXcenterYcenter = 0;
  // double sumYcenter = 0;
  // for(int i = 0; i<x.length; i++){
    // sumXcenterYcenter += (x[i] - xMean)*(y[i] - yMean);
    // sumXcenterSquared += Math.pow((x[i]-xMean),2);
  // }
  // beta1Hat = sumXcenterYcenter / sumXcenterSquared;
  double beta0Hat = calculateBeta0Hat(yMean,beta1Hat,xMean);
  // beta0Hat = yMean - beta1Hat*xMean;
  System.out.printf("%.2f %.2f %.2f\n",beta0Hat, beta1Hat, calculateMSE(y,x, beta0Hat,beta1Hat));
}

else if (flag == 700){
  int inputYear = Integer.valueOf(args[1]);
  double xMean = calculateXMean(x);
  double yMean = calculateYMean(y);
  double beta1Hat = calculateBeta1Hat(y,x,xMean, yMean);
  double beta0Hat = calculateBeta0Hat(yMean,beta1Hat,xMean);

  double prediction = beta0Hat + beta1Hat*inputYear;
  System.out.printf("%.2f\n",prediction);
}

}
}
