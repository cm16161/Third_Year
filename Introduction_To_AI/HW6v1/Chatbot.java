
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
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);

        if(flag == 100){
			int w = Integer.valueOf(args[1]);
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
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            //TODO generate
            double random = (double) n1/n2;
            // System.out.println(random);
            double l = 0;
            double r = 0;
            double cumulative;
            double total =0;
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
            for(int i = 0; i<probabilitySegment.length-1; i++){
              if((probabilitySegment[i] <= random) && (random <= probabilitySegment[i+1])){
                System.out.println(i);
                System.out.println(String.format("%.7f",probabilitySegment[i]));
                System.out.println(String.format("%.7f",probabilitySegment[i+1]));
              }
            }
            // for(double i = 0; i<random; i++){
              // l += probabilitySegment[i];
            // }
            // System.out.println(probabilitySegment[4699]);
            // System.out.println(probabilitySegment[4699-1]);
        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            //TODO

            //output
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            //TODO

        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            //TODO

            //output
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            //TODO
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
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // TODO Generate second word using r
                r = rng.nextDouble();
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();
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
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}
