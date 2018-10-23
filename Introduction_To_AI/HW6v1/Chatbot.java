
import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "./WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    static public void flag100(int w, ArrayList<Integer> corpus){
      // int w = Integer.valueOf(args[1]);
      int count = 0;
      for(int i = 0; i<corpus.size(); i++){
        if (corpus.get(i).equals(w)) {
          count++;
        }
      }
      //TODO count occurence of w
      System.out.println(count);
      System.out.println(String.format("%.7f",count/(double)corpus.size()));
    }

    static public double[] flag200(double random, ArrayList<Integer> corpus){
      // double random = (double) n1/n2;
      int wordCounts[] = new int[Collections.max(corpus)];
      for(int i = 0; i< wordCounts.length;i++){
        for (int j = 0; j<corpus.size();j++){
          if (corpus.get(j).equals(i)) {
            wordCounts[i]++;
          }
        }
      }
      double probabilitySegment[] = new double[Collections.max(corpus)+2];
      for(int i =0; i<probabilitySegment.length-1;i++){
        if(i != 0){
          probabilitySegment[i] = probabilitySegment[i-1];
          probabilitySegment[i] += (double) ((double) wordCounts[i-1] / (double) corpus.size());
        }
        if(i == 0){
          probabilitySegment[0] = 0;
        }
      }
      probabilitySegment[Collections.max(corpus)+1] = 1;
      return probabilitySegment;
    }

    static public void flag300(int h, int w, ArrayList<Integer> corpus){
      int count = 0;
      ArrayList<Integer> words_after_h = new ArrayList<Integer>();
      //TODO
      for(int i =0; i<corpus.size()-1;i++){
        if(corpus.get(i) == h){
          if(corpus.get(i+1) == w){
            count++;
          }
          words_after_h.add(corpus.get(i+1));
        }
      }

      //output
      System.out.println(count);
      System.out.println(words_after_h.size());
      System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
    }

    static public double[] flag400(double random, int h, ArrayList<Integer> corpus){
      // double random = (double) n1/n2;
      double l = 0;
      double r = 0;

      int bigramCounts[] = new int[Collections.max(corpus)];
      for(int i=0;i<bigramCounts.length;i++){
        for(int j =0; j<corpus.size();j++){
          if(corpus.get(j).equals(h)){
            if(corpus.get(j+1).equals(i)){
              bigramCounts[i]++;
            }
          }
        }

      }
      int totalBigrams =0;
      for(int i = 0;i<bigramCounts.length;i++){
        if(bigramCounts[i] != 0){
          totalBigrams+= bigramCounts[i];
        }
      }

      double probabilitySegment[] = new double[bigramCounts.length+2];
      for(int i =0; i<probabilitySegment.length-1;i++){
        if(i != 0){
          probabilitySegment[i] = probabilitySegment[i-1];
          probabilitySegment[i] += (double) ((double) bigramCounts[i-1] / (double) totalBigrams);
        }
        if(i == 0){
          probabilitySegment[0] = 0;
        }
      }
      probabilitySegment[bigramCounts.length+1] = 1;
      return probabilitySegment;
    }

    static public void flag500(int h1, int h2, int w, ArrayList<Integer> corpus){
      int count = 0;
      ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
      for(int i =0; i<corpus.size()-1;i++){
        if(corpus.get(i) == h1){
          if(corpus.get(i+1) == h2){
            if(corpus.get(i+2) == w){
              count++;
            }
            words_after_h1h2.add(corpus.get(i+2));
          }
        }
      }

      //output
      System.out.println(count);
      System.out.println(words_after_h1h2.size());
      if(words_after_h1h2.size() == 0)
          System.out.println("undefined");
      else
          System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
    }

    static public double[] flag600(double random, int h1, int h2, ArrayList<Integer> corpus){
      int trigramCounts[] = new int[Collections.max(corpus)];
      for(int i=0;i<trigramCounts.length;i++){
        for(int j =0; j<corpus.size();j++){
          if(corpus.get(j).equals(h1)){
            if(corpus.get(j+1).equals(h2)){
              if(corpus.get(j+2).equals(i)){
                trigramCounts[i]++;
              }
            }
          }
        }

      }
      int totalTrigrams =0;
      for(int i = 0;i<trigramCounts.length;i++){
        if(trigramCounts[i] != 0){
          totalTrigrams+= trigramCounts[i];
        }
      }

      double probabilitySegment[] = new double[trigramCounts.length+2];
      for(int i =0; i<probabilitySegment.length-1;i++){
        if(i != 0){
          probabilitySegment[i] = probabilitySegment[i-1];
          probabilitySegment[i] += (double) ((double) trigramCounts[i-1] / (double) totalTrigrams);
        }
        if(i == 0){
          probabilitySegment[0] = 0;// ((double)trigramCounts[0]/ (double) totalTrigrams);
        }
      }
      probabilitySegment[trigramCounts.length+1] = 1;
      return probabilitySegment;
    }

    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);

        if(flag == 100){
          int w = Integer.valueOf(args[1]);
          flag100(w, corpus);
        }

        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            //TODO generate
            double random = (double) (n1/n2);
            double probabilitySegment[] =flag200(random,corpus);
            for(int i = 0; i<probabilitySegment.length-1; i++){
              if((probabilitySegment[i] <= random) && (random <= probabilitySegment[i+1]) && (probabilitySegment[i+1] != 0)){
                System.out.println(i);
                System.out.println(String.format("%.7f",probabilitySegment[i]));
                System.out.println(String.format("%.7f",probabilitySegment[i+1]));
              }
            }
        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            flag300(h,w,corpus);

        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            double random = (double) n1/n2;

            //TODO
            double probabilitySegment[] = flag400(random,h,corpus);
            for(int i = 0; i<probabilitySegment.length-1; i++){
              if((probabilitySegment[i] <= random) && (random <= probabilitySegment[i+1])&& (probabilitySegment[i+1] !=0)){
                System.out.println(i);
                System.out.println(String.format("%.7f",probabilitySegment[i]));
                System.out.println(String.format("%.7f",probabilitySegment[i+1]));
              }
            }
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);

            //TODO
            flag500(h1,h2,w,corpus);

        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            double random = (double) n1/n2;
            //TODO
            double probabilitySegment[] =flag600(random,h1,h2,corpus);
            boolean cond = false;
            for(int i = 0; i<probabilitySegment.length-1; i++){
              if((probabilitySegment[i] <= (double)random) && (random <= probabilitySegment[i+1]) && (probabilitySegment[i+1] !=0)){
                System.out.println(i);
                System.out.println(String.format("%.7f",probabilitySegment[i]));
                System.out.println(String.format("%.7f",probabilitySegment[i+1]));
                cond = true;
                break;
              }
            }
            if(!cond){System.out.println("undefined");}
        }
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double r = rng.nextDouble();
                double probabilitySegment[] = flag200(r,corpus);
                for(int i = 0; i<probabilitySegment.length-1; i++){
                  if((probabilitySegment[i] <= r) && (r <= probabilitySegment[i+1]) && (probabilitySegment[i+1] != 0)){
                    h1 = i;
                    // System.out.println(i);
                    // System.out.println(String.format("%.7f",probabilitySegment[i]));
                    // System.out.println(String.format("%.7f",probabilitySegment[i+1]));
                  }
                }
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }


                // TODO Generate second word using r
                r = rng.nextDouble();
                probabilitySegment = flag400(r,h1,corpus);
                for(int i = 0; i<probabilitySegment.length-1; i++){
                  if((probabilitySegment[i] <= r) && (r <= probabilitySegment[i+1]) && (probabilitySegment[i+1] != 0)){
                    h2 = i;
                    break;
                  }
                }
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();
                double probabilitySegment[] = flag400(r,h1,corpus);
                for(int i = 0; i<probabilitySegment.length-1; i++){
                  if((probabilitySegment[i] <= r) && (r <= probabilitySegment[i+1])&& (probabilitySegment[i+1] !=0)){
                    h2 = i;
                    break;
                  }
                }
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                // TODO Generate new word using h1,h2
                double probabilitySegment[] =flag600(r,h1,h2,corpus);
                boolean cond = false;
                for(int i = 0; i<probabilitySegment.length-1; i++){
                  if((probabilitySegment[i] <= (double)r) && (r <= probabilitySegment[i+1]) && (probabilitySegment[i+1] !=0)){
                    w = i;
                    cond = true;
                    break;
                  }
                }
                if(!cond){System.out.println("undefined");}
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}
