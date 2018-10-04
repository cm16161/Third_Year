/** 
 * This skeleton is just an exmple.
 * Feel free to change this skeleton or using better ideas to implement.  
 */

import java.util.*;

// implement the class of block if necessary
class Block {
    
}

class GameState {      
    public int[][] board = new int[5][4];
    public GameState parent = null;
    public int cost = 0;
    public int steps = 0;
    public int totalCost = 0;
    public String boardState = "";

    public GameState(int [][] inputBoard, int steps) {
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 4; j++)
                this.board[i][j] = inputBoard[i][j];
        this.steps = steps; 
        String state = "";
			for(int i =0; i<5; i++){
				for(int j =0;j<4;j++){
					state=state.concat(Integer.toString(this.board[i][j]));
				}
			}
		this.boardState = state;
    }

    public GameState(int [][] inputBoard) {
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 4; j++)
                this.board[i][j] = inputBoard[i][j];
    }
            
    // get all successors and return them in sorted order
    public List<GameState> getNextStates(int flag) {
        List<GameState> successors = new ArrayList<>();
        List<Coordinate> emptySpace = new ArrayList<>();
        List<Coordinate> oneXone = new ArrayList<>();
        List<oneByTwo> oneXtwo = new ArrayList<>();
        twoByOne twoXone = new twoByOne(new Coordinate(-1000,-1000),new Coordinate(-1000,-1000));
        twoByTwo twoXtwo = new twoByTwo(new Coordinate(-1000,-1000),new Coordinate(-1000,-1000),new Coordinate(-1000,-1000),new Coordinate(-1000,-1000));

        	for(int i = 0; i<5; i++){
        		for(int j = 0; j<4; j++){
        			if(this.board[i][j]==0){
						emptySpace.add(new Coordinate(j,i));
        			}
        			if(this.board[i][j]==1){
        				if(twoXtwo.getTopLeft().getX()==-1000){
        					twoXtwo.setTopLeft(new Coordinate(j,i));
        					twoXtwo.setTopRight(new Coordinate(j+1,i));
        					twoXtwo.setBottomLeft(new Coordinate(j,i+1));
        					twoXtwo.setBottomRight(new Coordinate((j+1),(i+1)));
        				}
        			}
        			if(this.board[i][j]==2){
        				if(i!=4){
	        				if(this.board[i+1][j]==2){
        						if(oneXtwo.size()!=0){
        							for(int m =0; m<oneXtwo.size();m++){
        								if(oneXtwo.get(m).getBottom().getY() != i && oneXtwo.get(m).getBottom().getX() != j){
        									oneXtwo.add(new oneByTwo(new Coordinate(j,i),new Coordinate(j,i+1)));	
        								}
        							}
        						}
								else oneXtwo.add(new oneByTwo(new Coordinate(j,i), new Coordinate(j,i+1)));	        					
        					}
        				}
        			}
        			if(this.board[i][j]==3){
        				if (twoXone.getLeft().getX()== -1000){
        					twoXone.setLeft(new Coordinate(j,i));
        					twoXone.setRight(new Coordinate(j+1,i));
        				
        				}
        			}
        			if(this.board[i][j]==4){
        				oneXone.add(new Coordinate(j,i));
        			}
       			}
        	}
        	if(flag == 400 || flag == 500){
	        	int manhattanX = 1- twoXtwo.getTopLeft().getX(); 
 	  	     	if(manhattanX < 0){manhattanX *= -1;}
   	   		  	int manhattanY = 3 - twoXtwo.getTopLeft().getY();
   		     	if(manhattanY < 0){manhattanY *= -1;}
        		this.cost = manhattanX + manhattanY;
        	}
        	
          
			////////////////////////////////////////////////
	////////////////////// MOVE ALL THE 1x1 BLOCKS //////////////////
			////////////////////////////////////////////////
        
        for(int j = 0;j<2;j++){
        	for (int i = 0;i<oneXone.size();i++){
        		successors.add(new GameState(this.board));
        		successors.get(successors.size()-1).board[oneXone.get(i).getY()][oneXone.get(i).getX()] = 0;
        		successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 4;

        	}
        }
       
		     ////////////////////////////////////////////////
	   //////////////////// MOVE THE 1x2 BLOCKS /////////////////
			//////////////////////////////////////////////// 

		if(oneXtwo.size() > 4){
			oneXtwo.remove(oneXtwo.size()-1); //For some reason my algorithm duplicates the last block so remove it here 
		}
		
		for(int i = 0; i<oneXtwo.size();i++){
			for(int j = 0; j<2; j++){
				//Find the Vertical Downward moves
				if((oneXtwo.get(i).getBottom().getY()+1) == emptySpace.get(j).getY()){
					if(oneXtwo.get(i).getBottom().getX() == emptySpace.get(j).getX()){
						successors.add(new GameState(this.board));
						successors.get(successors.size()-1).board[oneXtwo.get(i).getTop().getY()][oneXtwo.get(i).getTop().getX()] = 0;
						successors.get(successors.size()-1).board[oneXtwo.get(i).getBottom().getY()][oneXtwo.get(i).getBottom().getX()] = 0;
						successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 2;
    					successors.get(successors.size()-1).board[emptySpace.get(j).getY()-1][emptySpace.get(j).getX()] = 2;
					}
				}
				//Find the Vertical Upwards move
				if((oneXtwo.get(i).getTop().getY()-1) == emptySpace.get(j).getY()){
					if(oneXtwo.get(i).getBottom().getX() == emptySpace.get(j).getX()){
						successors.add(new GameState(this.board));
						successors.get(successors.size()-1).board[oneXtwo.get(i).getTop().getY()][oneXtwo.get(i).getTop().getX()] = 0;
						successors.get(successors.size()-1).board[oneXtwo.get(i).getBottom().getY()][oneXtwo.get(i).getBottom().getX()] = 0;
						successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 2;
    					successors.get(successors.size()-1).board[emptySpace.get(j).getY()+1][emptySpace.get(j).getX()] = 2;
					}	
				}
				//Find the Horizontal Right move
				if((oneXtwo.get(i).getTop().getX()+1) == emptySpace.get(j).getX()){
					if(oneXtwo.get(i).getTop().getY() == emptySpace.get(j).getY()){
						if((oneXtwo.get(i).getBottom().getX()+1) == emptySpace.get((j+1)%2).getX()){
							if(oneXtwo.get(i).getBottom().getY() == emptySpace.get((j+1)%2).getY()){
								successors.add(new GameState(this.board));
								successors.get(successors.size()-1).board[oneXtwo.get(i).getTop().getY()][oneXtwo.get(i).getTop().getX()] = 0;
								successors.get(successors.size()-1).board[oneXtwo.get(i).getBottom().getY()][oneXtwo.get(i).getBottom().getX()] = 0;
								successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 2;
    							successors.get(successors.size()-1).board[emptySpace.get((j+1)%2).getY()][emptySpace.get((j+1)%2).getX()] = 2;
							}					
						}
					}
				}
				//Find the Horizontal  Left move
				if((oneXtwo.get(i).getTop().getX()-1) == emptySpace.get(j).getX()){
					if(oneXtwo.get(i).getTop().getY() == emptySpace.get(j).getY()){
						if((oneXtwo.get(i).getBottom().getX()-1) == emptySpace.get((j+1)%2).getX()){
							if(oneXtwo.get(i).getBottom().getY() == emptySpace.get((j+1)%2).getY()){
								successors.add(new GameState(this.board));
								successors.get(successors.size()-1).board[oneXtwo.get(i).getTop().getY()][oneXtwo.get(i).getTop().getX()] = 0;
								successors.get(successors.size()-1).board[oneXtwo.get(i).getBottom().getY()][oneXtwo.get(i).getBottom().getX()] = 0;
								successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 2;
    							successors.get(successors.size()-1).board[emptySpace.get((j+1)%2).getY()][emptySpace.get((j+1)%2).getX()] = 2;
							}					
						}
					}
				}
			}
		}

 
    	
		     ////////////////////////////////////////////////
	   //////////////////// MOVE THE 2x1 BLOCK //////////////////
			//////////////////////////////////////////////// 
		
		for (int j = 0; j<2 ;j++){
			if(twoXone.getRight().getX()+1 == emptySpace.get(j).getX()){
				if(twoXone.getRight().getY()==emptySpace.get(j).getY()){
					successors.add(new GameState(this.board));
					successors.get(successors.size()-1).board[twoXone.getLeft().getY()][twoXone.getLeft().getX()] = 0;
					successors.get(successors.size()-1).board[twoXone.getRight().getY()][twoXone.getRight().getX()] = 3;
					successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 3;
				}
			}
			if(twoXone.getLeft().getX()-1 == emptySpace.get(j).getX()){
				if(twoXone.getLeft().getY()==emptySpace.get(j).getY()){
					successors.add(new GameState(this.board));
					successors.get(successors.size()-1).board[twoXone.getLeft().getY()][twoXone.getLeft().getX()] = 3;
					successors.get(successors.size()-1).board[twoXone.getRight().getY()][twoXone.getRight().getX()] = 0;
					successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 3;
				}
			}
			if(twoXone.getLeft().getY()+1 == emptySpace.get(j).getY()){
				if(twoXone.getLeft().getX() == emptySpace.get(j).getX()){
					if(twoXone.getRight().getY()+1 == emptySpace.get((j+1)%2).getY()){
						if(twoXone.getRight().getX()== emptySpace.get((j+1)%2).getX()){
							successors.add(new GameState(this.board));
							successors.get(successors.size()-1).board[twoXone.getLeft().getY()][twoXone.getLeft().getX()] = 0;
							successors.get(successors.size()-1).board[twoXone.getRight().getY()][twoXone.getRight().getX()] = 0;
							successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 3;
							successors.get(successors.size()-1).board[emptySpace.get((j+1)%2).getY()][emptySpace.get((j+1)%2).getX()] = 3;
						}
					}
				}
			}
			if(twoXone.getLeft().getY()-1 == emptySpace.get(j).getY()){
				if(twoXone.getLeft().getX() == emptySpace.get(j).getX()){
					if(twoXone.getRight().getY()-1 == emptySpace.get((j+1)%2).getY()){
						if(twoXone.getRight().getX()== emptySpace.get((j+1)%2).getX()){
							successors.add(new GameState(this.board));
							successors.get(successors.size()-1).board[twoXone.getLeft().getY()][twoXone.getLeft().getX()] = 0;
							successors.get(successors.size()-1).board[twoXone.getRight().getY()][twoXone.getRight().getX()] = 0;
							successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 3;
							successors.get(successors.size()-1).board[emptySpace.get((j+1)%2).getY()][emptySpace.get((j+1)%2).getX()] = 3;
						}
					}
				}
			}
		}
		
		
		     ////////////////////////////////////////////////
	   //////////////////// MOVE THE 2x2 BLOCK //////////////////
			////////////////////////////////////////////////
		
		for(int j = 0; j<2; j++){
			//Move Horizontally Right
			if(twoXtwo.getTopRight().getX() +1 == emptySpace.get(j).getX()){
				if(twoXtwo.getTopRight().getY() == emptySpace.get(j).getY()){
					if(twoXtwo.getBottomRight().getX() +1 == emptySpace.get((j+1)%2).getX()){
						if(twoXtwo.getBottomRight().getY() == emptySpace.get((j+1)%2).getY()){
							successors.add(new GameState(this.board));
							successors.get(successors.size()-1).board[twoXtwo.getTopLeft().getY()][twoXtwo.getTopLeft().getX()]=0;
							successors.get(successors.size()-1).board[twoXtwo.getBottomLeft().getY()][twoXtwo.getBottomLeft().getX()]=0;
							successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 1;
							successors.get(successors.size()-1).board[emptySpace.get((j+1)%2).getY()][emptySpace.get((j+1)%2).getX()] = 1;
							twoXtwo.setTopLeft(emptySpace.get(j));
							if(flag == 400 || flag == 500){
								int manhattanX = 1- twoXtwo.getTopLeft().getX(); 
					        	if(manhattanX < 0){manhattanX *= -1;}
					        	int manhattanY = 3 - twoXtwo.getTopLeft().getY();
					        	if(manhattanY < 0){manhattanY *= -1;}
				    	    	successors.get(successors.size()-1).cost = manhattanX + manhattanY;
				    	    	successors.get(successors.size()-1).cost = this.cost-1;
							}
				        	
						}
					}
				}
			}
			//Move Horizontally Left
			if(twoXtwo.getTopLeft().getX() -1 == emptySpace.get(j).getX()){
				if(twoXtwo.getTopRight().getY() == emptySpace.get(j).getY()){
					if(twoXtwo.getBottomLeft().getX() -1 == emptySpace.get((j+1)%2).getX()){
						if(twoXtwo.getBottomRight().getY() == emptySpace.get((j+1)%2).getY()){
							successors.add(new GameState(this.board));
							successors.get(successors.size()-1).board[twoXtwo.getTopRight().getY()][twoXtwo.getTopRight().getX()]=0;
							successors.get(successors.size()-1).board[twoXtwo.getBottomRight().getY()][twoXtwo.getBottomRight().getX()]=0;
							successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 1;
							successors.get(successors.size()-1).board[emptySpace.get((j+1)%2).getY()][emptySpace.get((j+1)%2).getX()] = 1;
							twoXtwo.setTopLeft(emptySpace.get(j));
							
							if(flag == 400 || flag == 500){
								int manhattanX = 1- twoXtwo.getTopLeft().getX(); 
					        	if(manhattanX < 0){manhattanX *= -1;}
					        	int manhattanY = 3 - twoXtwo.getTopLeft().getY();
					        	if(manhattanY < 0){manhattanY *= -1;}
				    	    	successors.get(successors.size()-1).cost = manhattanX + manhattanY;
				    	    	successors.get(successors.size()-1).cost = this.cost-1;
							}
						}
					}
				}
			}
			//Move Vertically Up
			if(twoXtwo.getTopLeft().getY()-1 == emptySpace.get(j).getY()){
				if(twoXtwo.getTopLeft().getX() == emptySpace.get(j).getX()){
					if(twoXtwo.getTopRight().getY()-1 == emptySpace.get((j+1)%2).getY()){
						if(twoXtwo.getTopRight().getX() == emptySpace.get((j+1)%2).getX()){
							successors.add(new GameState(this.board));
							successors.get(successors.size()-1).board[twoXtwo.getBottomLeft().getY()][twoXtwo.getBottomLeft().getX()] = 0;
							successors.get(successors.size()-1).board[twoXtwo.getBottomRight().getY()][twoXtwo.getBottomRight().getX()] = 0;
							successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 1;
							successors.get(successors.size()-1).board[emptySpace.get((j+1)%2).getY()][emptySpace.get((j+1)%2).getX()] = 1;
							twoXtwo.setTopLeft(emptySpace.get(j));
							if(flag == 400 || flag == 500){
								int manhattanX = 1- twoXtwo.getTopLeft().getX(); 
					        	if(manhattanX < 0){manhattanX *= -1;}
					        	int manhattanY = 3 - twoXtwo.getTopLeft().getY();
					        	if(manhattanY < 0){manhattanY *= -1;}
				    	    	successors.get(successors.size()-1).cost = manhattanX + manhattanY;
							}
						}
					}
				}
			}
			//Move Vertically Down
			if(twoXtwo.getBottomLeft().getY()+1 == emptySpace.get(j).getY()){
				if(twoXtwo.getBottomLeft().getX() == emptySpace.get(j).getX()){
					if(twoXtwo.getBottomRight().getY()+1 == emptySpace.get((j+1)%2).getY()){
						if(twoXtwo.getBottomRight().getX() == emptySpace.get((j+1)%2).getX()){
							successors.add(new GameState(this.board));
							successors.get(successors.size()-1).board[twoXtwo.getTopLeft().getY()][twoXtwo.getTopLeft().getX()] = 0;
							successors.get(successors.size()-1).board[twoXtwo.getTopRight().getY()][twoXtwo.getTopRight().getX()] = 0;
							successors.get(successors.size()-1).board[emptySpace.get(j).getY()][emptySpace.get(j).getX()] = 1;
							successors.get(successors.size()-1).board[emptySpace.get((j+1)%2).getY()][emptySpace.get((j+1)%2).getX()] = 1;
							twoXtwo.setTopLeft(emptySpace.get(j));
							if(flag == 400 || flag == 500){
								int manhattanX = 1- twoXtwo.getTopLeft().getX(); 
					        	if(manhattanX < 0){manhattanX *= -1;}
					        	int manhattanY = 3 - twoXtwo.getTopLeft().getY();
					        	if(manhattanY < 0){manhattanY *= -1;}
				    	    	successors.get(successors.size()-1).cost = manhattanX + manhattanY;
				    	    	successors.get(successors.size()-1).cost = this.cost-1;
							}
						}
					}
				}
			}
		}
		
 
		List<String> gamestates = new ArrayList<>();
		String state = "";
		for(int c=0;c<successors.size();c++){
		state = "";
			for(int i =0; i<5; i++){
				for(int j =0;j<4;j++){
					state=state.concat(Integer.toString(successors.get(c).board[i][j]));
				}
			}
			gamestates.add(c,state);
		}

		Collections.sort(gamestates);

		for(int c=0; c<successors.size();c++){
			for(int i = 0; i<5; i++){
				for(int j = 0; j<4; j++){
					successors.get(c).board[i][j] = Character.getNumericValue(gamestates.get(c).charAt((4*i)+j));
					successors.get(c).parent = this;
					successors.get(c).steps = this.steps+1;
					if(flag == 400 || flag == 500){
						Coordinate topLeft = findTopLeft(successors.get(c));
						int manhattanX = 1- topLeft.getX(); //twoXtwo.getTopLeft().getX(); 
					    if(manhattanX < 0){manhattanX *= -1;}
						int manhattanY = 3 - topLeft.getY();//twoXtwo.getTopLeft().getY();
					    if(manhattanY < 0){manhattanY *= -1;}
				    	successors.get(c).cost = manhattanX + manhattanY;}
					successors.get(c).totalCost = successors.get(c).steps + successors.get(c).cost;
					successors.get(c).boardState = gamestates.get(c);
				}
			}
		}	
        return successors;
    }

    // return the 20-digit number as ID
    public String getStateID() {  
        String s = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++)
                s += this.board[i][j];
        }
        return s;
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(this.board[i][j]);
            System.out.println();
        }
    }

    // check whether the current state is the goal
    public boolean goalCheck() {        

        return false;
    }

    // add new methods for the GameState if necessary        

    public Coordinate findTopLeft(GameState state){
    	for(int i = 0; i<5; i++){
    		for(int j = 0;j<4;j++){
    			if(state.board[i][j]==1){
    				return new Coordinate(j,i);
    			}
    		}
    	}
    	return null;
    }

}

class twoByTwo{
	Coordinate topLeft;
	Coordinate topRight;
	Coordinate bottomLeft;
	Coordinate bottomRight;
	
	public twoByTwo(Coordinate top_left, Coordinate top_right, Coordinate bottom_left, Coordinate bottom_right){
		topLeft = top_left;
		topRight = top_right;
		bottomLeft = bottom_left;
		bottomRight = bottom_right;
	}
	
	public Coordinate getTopLeft(){
		return this.topLeft;
	}
	
	public Coordinate getTopRight(){
		return this.topRight;
	}

	public Coordinate getBottomLeft(){
		return this.bottomLeft;
	}
	
	public Coordinate getBottomRight(){
		return this.bottomRight;
	}
	
	public void setTopLeft(Coordinate location){
		topLeft = location;
	}	

	public void setTopRight(Coordinate location){
		topRight = location;
	}	
	
	public void setBottomLeft(Coordinate location){
		bottomLeft = location;
	}
	
	public void setBottomRight(Coordinate location){
		bottomRight = location;
	}
	
}

class oneByTwo{
	Coordinate topPosition;
	Coordinate bottomPosition;
	
	public oneByTwo(Coordinate top, Coordinate bottom){
		topPosition = top;
		bottomPosition = bottom;
	}
	
	public Coordinate getTop(){
		return this.topPosition;	
	}
	
	public Coordinate getBottom(){
		return this.bottomPosition;	
	}
}

class twoByOne{
	Coordinate leftPosition;
	Coordinate rightPosition;
	
	public twoByOne(Coordinate left, Coordinate right){
		leftPosition = left;
		rightPosition = right;
	}
	
	public void setLeft(Coordinate left){
		leftPosition = left;
	}
	
	public void setRight(Coordinate right){
		rightPosition = right;
	}	
	
	public Coordinate getLeft(){
		return this.leftPosition;
	}
	
	public Coordinate getRight(){
		return this.rightPosition;
	}
}

class Coordinate{
	Integer xCoordinate;
	Integer yCoordinate;
	
	public Coordinate(int x, int y){
		xCoordinate = x;
		yCoordinate = y;
	}
	
	public void setX(int x){
		this.xCoordinate = x;
	}
	
	public void sety(int y){
		this.yCoordinate = y;
	}	
	
	public int getX(){
		return this.xCoordinate;
	}
	
	public int getY(){
		return this.yCoordinate;
	}
	
	public void printCoord(){
		System.out.printf("%d,%d\n",xCoordinate,yCoordinate);
	}
	
}

class AStarSearch{
    Queue<GameState> openSet;
    Set<GameState> closedSet;
    int goal;
    int goalCheck;
    int maxOPEN;
    int maxCLOSED;
    int steps;

	public AStarSearch(){
	this.openSet = new PriorityQueue<>(stateComparator);
    this.closedSet = new HashSet<>();
    this.goal = 0; 
    this.goalCheck= 0;
    this.maxOPEN = -1;
    this.maxCLOSED = -1;
    this.steps = 0;
	}
    //Comparator for the GameState
    public Comparator<GameState> stateComparator = new Comparator<GameState>() {
        @Override
        public int compare(GameState o1, GameState o2) {
            if (o1.totalCost - o2.totalCost != 0)
                return o1.totalCost - o2.totalCost;
            else
                //return o1.getStateID().compareTo(o2.getStateID());
                return o1.boardState.compareTo(o2.boardState);
        }
    };   
 
    public void printList(int flag, GameState state){
            String stateAsString = "";
			for(int i =0; i<5; i++){
				for(int j =0;j<4;j++){
					stateAsString = stateAsString.concat(Integer.toString(state.board[i][j]));
				}
			}
		System.out.println(state.boardState);
        state.printBoard();
        System.out.println(state.totalCost + " " + state.steps + " " + state.cost);
    	stateAsString = "";
    	if(state.parent != null){
			for(int i =0; i<5; i++){
				for(int j =0;j<4;j++){
					stateAsString = stateAsString.concat(Integer.toString(state.parent.board[i][j]));
				}
			}
		}
		else {stateAsString = "null";}
        System.out.println(stateAsString); 
    }
    
    public Coordinate findTopLeft(GameState state){
    	for(int i = 0; i<5; i++){
    		for(int j = 0;j<4;j++){
    			if(state.board[i][j]==1){
    				return new Coordinate(j,i);
    			}
    		}
    	}
    	return null;
    }

    // implement the A* search
    public GameState aStarSearch(int flag, GameState state) {   
        // feel free to using other data structures if necessary
        List<GameState> neighbourStates = new ArrayList<>();
		String stateAsString = "";
		List<GameState> path = new ArrayList<>();

		int unique = 1;
		for(GameState c : this.closedSet){
			if(state.boardState.equals(c.boardState)){
				unique = 0;
			}
		}
		if(unique==1){
			this.closedSet.add(state);
		}
		if(this.maxCLOSED < this.closedSet.size()){
			this.maxCLOSED = this.closedSet.size();
		}
         
		if(flag ==200 || flag == 400){  
				for(int i =0; i<5; i++){
					for(int j =0;j<4;j++){
						stateAsString = stateAsString.concat(Integer.toString(state.board[i][j]));
					}
				}
			System.out.println(stateAsString);
			state.printBoard();
		}
      //  if(flag ==300 || flag == 500){System.out.println("");}
        if(flag == 400|| flag == 500){
        	Coordinate topLeft = findTopLeft(state);
        	int manhattanX = 1 - topLeft.getX();
        	int manhattanY = 3 - topLeft.getY();
        	if(manhattanX < 0){manhattanX *= -1;}
        	if(manhattanY < 0){manhattanY *= -1;}
        	state.cost = manhattanX + manhattanY;
        	state.totalCost = state.cost+state.steps;
        }

        if(flag ==200 || flag ==400){
        	if(flag == 200){state.cost = 0;}
        	System.out.println(state.totalCost + " " + state.steps + " " + state.cost);
        	stateAsString = "";
        	if(state.parent != null){
				for(int i =0; i<5; i++){
					for(int j =0;j<4;j++){
						stateAsString = stateAsString.concat(Integer.toString(state.parent.board[i][j]));
					}
				}
        	}
        	else{stateAsString = "null";}
        	System.out.println(stateAsString); 
        }
        
		this.goalCheck++;
		if((state.board[3][1] == 1) && (state.board[3][2] == 1) && (state.board[4][1] == 1) && (state.board[4][2] == 1)){
			this.goal = 1;
			GameState s = state;
			if(flag == 300 || flag == 500){
				path.add(s);
				while(s.parent != null){
					if(this.closedSet.contains(s.parent)){
						path.add(s.parent);
						s = s.parent;
					}
				}
				for(int k = path.size()-1; k>=0;k--){
					if(path.get(k) != null){
						path.get(k).printBoard();
						System.out.println("");

					}
				}
				
				System.out.println("goalCheckTimes " + this.goalCheck);
        		System.out.println("maxOPENSize " + this.maxOPEN);
        		System.out.println("maxCLOSEDSize " + this.maxCLOSED);
        		System.out.println("steps " + state.steps);
			}
			return state;
		}   
        neighbourStates = state.getNextStates(flag);
		for (GameState g : neighbourStates){
			unique = 1; 
			for(GameState o : this.openSet){
				if(g.boardState.equals(o.boardState)){
					unique = 0;
				}
			}
			for(GameState c : this.closedSet){
				if(g.boardState.equals(c.boardState)){
					unique = 0;
				}
			}
			if(unique==1){
				this.openSet.add(g);	
			}

		}
		
		if(this.maxOPEN < this.openSet.size()){
			this.maxOPEN = this.openSet.size();
		}
		
		if(flag == 200 || flag ==400){
			System.out.println("OPEN");
			for(GameState g: this.openSet){
				this.printList(flag,g);
			}
		}
		
		if(flag ==200 || flag ==400){
			System.out.println("CLOSED");
			for(GameState g : this.closedSet){	
				this.printList(flag,g);
			}
		}
		
        return this.openSet.poll();
    }    

    // add more methods for the A* search if necessary
}

public class Klotski {   
    public static void printNextStates(GameState s) {
        List<GameState> states = s.getNextStates(100);
        for (GameState state: states) {
            state.printBoard();
            System.out.println();
        }
    }
   
    public static void main(String[] args) {
        if (args == null || args.length < 21) {
            return;
        }
        int flag = Integer.parseInt(args[0]);
        int[][] board = new int[5][4];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = Integer.parseInt(args[i * 4 + j + 1]);
            }                
        }        
        GameState s = new GameState(board, 0);

        if (flag == 100) {
            printNextStates(s);
            return;
        }
		
        AStarSearch search = new AStarSearch();     
        int iteration = 1;
        if(flag == 200 || flag ==400){
        	System.out.println("iteration  "+iteration);
        }
        GameState nextState = search.aStarSearch(flag, s);

        while (search.goal != 1){
        	iteration++;
        	if(flag == 200 || flag ==400){
        		System.out.println("iteration "+iteration);
        	}
        	nextState = search.aStarSearch(flag,nextState);
        }           
    }

}