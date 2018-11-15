        /**
 * A kNN classification algorithm implementation.
 *
 */

import java.util.*;


public class KNN {

	/**
	 * In this method, you should implement the kNN algorithm. You can add
	 * other methods in this class, or create a new class to facilitate your
	 * work. If you create other classes, DO NOT FORGET to include those java
   * files when preparing your code for hand in.
   *
	 * Also, Please DO NOT MODIFY the parameters or return values of this method,
   * or any other provided code.  Again, create your own methods or classes as
   * you need them.
	 *
	 * @param trainingData
	 * 		An Item array of training data
	 * @param testData
	 * 		An Item array of test data
	 * @param k
	 * 		The number of neighbors to use for classification
	 * @return
	 * 		The object KNNResult contains classification accuracy,
	 * 		category assignment, etc.
	 */
	public KNNResult classify(Item[] trainingData, Item[] testData, int k) {

		/* ... YOUR CODE GOES HERE ... */
    // for each test item in testData
		KNNResult knn = new KNNResult();
		knn.categoryAssignment = new String[testData.length];
		knn.nearestNeighbors = new String[testData.length][k];
		int count =0;
		for(Item i : testData){
			List<Double> distances = new ArrayList<Double>();
			for(Item j : trainingData){
				double distanceX = (j.features[0] - i.features[0])*(j.features[0] - i.features[0]);
				double distanceY = (j.features[1] - i.features[1])*(j.features[1] - i.features[1]);
				double distanceZ = (j.features[2] - i.features[2])*(j.features[2] - i.features[2]);
				double totalDistance = distanceX+distanceY+distanceZ;
				distances.add(totalDistance);
			}
			List<Integer> indices = new ArrayList<Integer>();
			for(int n = 0; n < k; n++){
				double smallest = 10000;
				int index = 0;
				for(int m = 0; m< distances.size();m++){
					if(distances.get(m) < smallest){
						smallest = distances.get(m);
						index = m;
					}
				}
				indices.add(index);
				distances.set(index,(double)100000);
			}
			int fruitCount = 0;
			int machineCount = 0;
			int nationCount =0;
			int innerCount = 0;
			// System.out.println(i.name);
			// System.out.println();
			for(int t : indices){
				knn.nearestNeighbors[count][innerCount] = trainingData[t].name;
				// System.out.println(knn.nearestNeighbors[count][innerCount]);
				if(trainingData[t].category.equals("fruit")){
					fruitCount++;
				}
				if(trainingData[t].category.equals("machine")){
					machineCount++;
				}
				if(trainingData[t].category.equals("nation")){
					nationCount++;
				}
				innerCount++;
			}
			// System.out.println();
			if((nationCount >= machineCount) && (nationCount >= fruitCount)){
				knn.categoryAssignment[count] = "nation";
			}
			else if((machineCount >= fruitCount) && (machineCount >= nationCount)){
				knn.categoryAssignment[count] = "machine";
			}
			else if((fruitCount > machineCount) && (fruitCount > nationCount)){
				knn.categoryAssignment[count] = "fruit";
			}

			count++;
		}

      // find kNN in trainingData

      // get predicted category, save in KNNResult.categoryAssignment

      // save kNN in KNNResult.nearestNeighbors

    // calculate accuracy
		int numCorrect = 0;
		for(int f = 0; f<testData.length; f++){
			if(testData[f].category.equals( knn.categoryAssignment[f])){
				numCorrect++;
			}
		}
		double accuracy = (double)numCorrect / (double)testData.length;
		knn.accuracy = accuracy;
		return knn;
	}
}
