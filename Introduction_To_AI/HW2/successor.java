import java.util.*;

public class successor {
    public static class JugState {
        int[] Capacity = new int[]{0,0};
        int[] Content = new int[]{0,0};
        public JugState(JugState copyFrom)
        {
            this.Capacity[0] = copyFrom.Capacity[0];
            this.Capacity[1] = copyFrom.Capacity[1];
            this.Content[0] = copyFrom.Content[0];
            this.Content[1] = copyFrom.Content[1];
        }
        public JugState()
        {
        }
        public JugState(int A,int B)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
        }
        public JugState(int A,int B, int a, int b)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Content[0] = a;
            this.Content[1] = b;
        }
 
        public void printContent()
        {
            System.out.println(this.Content[0] + " " + this.Content[1]);
        }
 
        public ArrayList<JugState> getNextStates(){
            ArrayList<JugState> successors = new ArrayList<>();

            // TODO add all successors to the list		
			//Case 1: Empty Container 1
			JugState emptyContainer1 = new JugState(this.Capacity[0],this.Capacity[1],0,this.Content[1]);
			successors.add(emptyContainer1);
			
			//Case 2: Empty Container 2
			JugState emptyContainer2 = new JugState(this.Capacity[0],this.Capacity[1],this.Content[0],0);
			successors.add(emptyContainer2);
			
			//Case 3: Fill Container 1
			if (this.Content[0] < this.Capacity[0]) {
				JugState fillContainer1 = new JugState(this.Capacity[0],this.Capacity[1],this.Capacity[0],this.Content[1]);
				successors.add(fillContainer1);
			}
			
			//Case 4: Fill Container 2
			if (this.Content[1] < this.Capacity[1]) {
				JugState fillContainer2 = new JugState(this.Capacity[0],this.Capacity[1],this.Content[0],this.Capacity[1]);
				successors.add(fillContainer2);
			}			
			//Case 5: Pour Container 1 into Container 2
			if(this.Content[1] < this.Capacity[1]){
				int buffer = this.Capacity[1] - this.Content[1];
				int newContent2 = this.Content[0] + this.Content[1];
				int newContent1 = this.Content[0] - buffer;
				if (newContent1 < 0){
					newContent1 = 0;
				}
				if (newContent2 > this.Capacity[1]){
					newContent2 = this.Capacity[1];	
				}
			JugState pourContainer1IntoContainer2 = new JugState(this.Capacity[0], this.Capacity[1], newContent1, newContent2);
			successors.add(pourContainer1IntoContainer2);
			}
			
			//Case 6: Pour Container 2 into Container 1
			if(this.Content[0] < this.Capacity[0]){
				int buffer = this.Capacity[0] - this.Content[0];
				int newContent1 = this.Content[0] + this.Content[1];
				int newContent2 = this.Content[1] - buffer;
				if (newContent2 < 0){
					newContent2 = 0;
				}
				if (newContent1 > this.Capacity[0]){
					newContent1 = this.Capacity[0];	
				}
			JugState pourContainer2IntoContainer1 = new JugState(this.Capacity[0], this.Capacity[1], newContent1, newContent2);
			successors.add(pourContainer2IntoContainer1);
			}			
			
						
            // Use this snippet to sort the successors
            Collections.sort(successors, (s1, s2) -> {
                if (s1.Content[0] < s2.Content[0]) {
                    return -1;
                } else if (s1.Content[0] > s2.Content[0]) {
                    return 1;
                } else return Integer.compare(s1.Content[1], s2.Content[1]);
            });

            return successors;
        }

    }

    public static void main(String[] args) {
        if( args.length != 4 )
        {
            System.out.println("Usage: java successor [A] [B] [a] [b]");
            return;
        }

        // parse command line arguments
        JugState a = new JugState();
        a.Capacity[0] = Integer.parseInt(args[0]);
        a.Capacity[1] = Integer.parseInt(args[1]);
        a.Content[0] = Integer.parseInt(args[2]);
        a.Content[1] = Integer.parseInt(args[3]);

        // Implement this function
        ArrayList<JugState> asist = a.getNextStates();

        // Print out generated successors
        for(int i=0;i< asist.size(); i++)
        {
            asist.get(i).printContent();
        }

        return;
    }
}
