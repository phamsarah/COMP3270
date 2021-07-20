import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Random;

/**
 * 
 * @author Sarah Pham created on June 18, 2021
 * This program computes the maximum sum contiguous subvector problem (mscs). 
 *
 */

public class mscs_problem {
	
	public static int[] randomArray_1 =  new int[10];
	public static int[] randomArray_2 =  new int[15];
	public static int[] randomArray_3 =  new int[20];
	public static int[] randomArray_4 =  new int[25];
	public static int[] randomArray_5 =  new int[30];
	public static int[] randomArray_6 =  new int[35];
	public static int[] randomArray_7 =  new int[40];
	public static int[] randomArray_8 =  new int[45];
	public static int[] randomArray_9 =  new int[50];
	public static int[] randomArray_10 =  new int[55];
	public static int[] randomArray_11 =  new int[60];
	public static int[] randomArray_12 =  new int[65];
	public static int[] randomArray_13 =  new int[70];
	public static int[] randomArray_14 =  new int[75];
	public static int[] randomArray_15 =  new int[80];
	public static int[] randomArray_16 =  new int[85];
	public static int[] randomArray_17 =  new int[90];
	public static int[] randomArray_18 =  new int[95];
	public static int[] randomArray_19 =  new int[100];
	
	public static int[][] matrix = new int[19][8];
	
	public static void main(String[] args){
		
		int[] X = readFileToArray();
		
		int max = 0;
		max = algorithm1(X);
		System.out.println("algorithm-1: " + max + "\n");
		max = algorithm2(X);
		System.out.println("algorithm-2: " + max + "\n");
		max = maxSum(X, 0, 9);
		System.out.println("algorithm-3: " + max + "\n");
		max = algorithm4(X);
		System.out.println("algorithm-4: " + max + "\n");
		
		
		randArrayHandler();
		outputToFile();
	}
	
	
	/**
	 *  readFileToArray function reads the file and inputs the values into an array
	 *  
	 *  Source: http://www.java2s.com/Code/Java/File-Input-Output/Readeachlineinacommaseparatedfileintoanarray.htm
	 *  		https://stackoverflow.com/questions/18838781/converting-string-array-to-an-integer-array
	 */
	public static int[] readFileToArray(){
		String[] stringFileInput = new String[10];
		int[] intFileInput = new int[10];
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("phw_input.txt"));
			String inputLine = null;
			
			while((inputLine = br.readLine()) != null)
				stringFileInput = inputLine.split(",");
		
			
			// Converting the string array into integer array 
			for(int i = 0; i < stringFileInput.length; i++)
				intFileInput[i] = Integer.parseInt(stringFileInput[i]);
			
			br.close();
		}
		catch(IOException e) { System.out.println(e); }
		
		return intFileInput;

	}
	
	/**
	 * 	Algorithm1 is the first algorithm that was provided in pseudocode
	 * 	The running time of this algorithm would be O(n^3) since there are
	 * 	3 nested for-loops
	 * @param X - The array from P to Q
	 * @return maxSoFar - The maximum integer in the array given through this algorithm
	 */
	public static int algorithm1(int X[]) {
		int maxSoFar = 0, P = 0;
		int Q = X.length;
		
		for(int L = P; L < Q; L++) {	
			for(int U = L; U < Q; U++) {	
				 int sum = 0;				
				for(int I = L; I <= U; I++) {
					sum = sum + X[I];	
				}
				maxSoFar = Math.max(maxSoFar, sum);
			}
		}
		
		return maxSoFar;
	}
	
	
	/**
	 * 	Algorithm2 is the second algorithm that was provided in pseudocode
	 * @param X - The array from P to Q
	 * @return maxSoFar - The maximum integer in the array given through this algorithm
	 */
	public static int algorithm2(int X[]) {
		int maxSoFar = 0, P = 0;
		int Q = X.length;
		
		for(int L = P; L < Q; L++) {
			int sum = 0;
			for(int U = L; U < Q; U++) {
				sum = sum + X[U];
				// Sum contains X[L..U]
				maxSoFar = Math.max(maxSoFar, sum);
			}
		}
		
		return maxSoFar;
	}
	
	/**
	 * 	maxSum is the third algorithm that was provided in pseudocode
	 * 		This method is used recursively.
	 * @param X - The array from L to U
	 * @return maxSoFar - The maximum integer in the array given through this algorithm
	 */
	public static int maxSum(int X[], int L, int U) {
		
		if(L > U) return 0;
		if(L == U) return Math.max(0, X[L]);
		
		int M = (L + U)/2;
		int sum = 0, maxToLeft = 0;
		
		for(int I = M; I >= L; I--) {
			sum = sum + X[I];
			maxToLeft = Math.max(maxToLeft, sum);
		}
		
		sum = 0;
		int maxToRight = 0;
		
		for(int I = M+1; I <= U; I++) {
			sum = sum + X[I];
			maxToRight = Math.max(maxToRight, sum);
		}
		
		int maxCrossing = maxToLeft + maxToRight;
		
		int maxInA = maxSum(X, L, M);
		int maxInB = maxSum(X, M+1, U);
		
		return Math.max(maxCrossing,Math.max(maxInA, maxInB));
	}
	
	/**
	 * 	Algorithm4 is the fourth algorithm that was provided in pseudocode
	 * @param X - The array from P to Q
	 * @return maxSoFar - The maximum integer in the array given through this algorithm
	 */
	public static int algorithm4(int X[]) {
		int maxSoFar = 0, maxEndingHere = 0, P = 0;
		int Q = X.length;
		
		for(int I = P; I < Q; I++) {
			maxEndingHere = Math.max(0, maxEndingHere + X[I]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		
		return maxSoFar;
	}

	/**
	 *  randomizeArrays will fill all of the global variable arrays with random integers
	 *  	that contain negative, zero, and positive integers and will also calculate
	 *  	the time complexity of each populated array
	 */
	public static void randArrayHandler() {
		randomArray_1 = populateArray(10);
		randomArray_2 = populateArray(15);
		randomArray_3 = populateArray(20);
		randomArray_4 = populateArray(25);
		randomArray_5 = populateArray(30);
		randomArray_6 = populateArray(35);
		randomArray_7 = populateArray(40);
		randomArray_8 = populateArray(45);
		randomArray_9 = populateArray(50);
		randomArray_10 = populateArray(55);
		randomArray_11 = populateArray(60);
		randomArray_12 = populateArray(65);
		randomArray_13 = populateArray(70);
		randomArray_14 = populateArray(75);
		randomArray_15 = populateArray(80);
		randomArray_16 = populateArray(85);
		randomArray_17 = populateArray(90);
		randomArray_18 = populateArray(95);
		randomArray_19 = populateArray(100);
		
		measureTime(randomArray_1, 0);
		measureTime(randomArray_2, 1);
		measureTime(randomArray_3, 2);
		measureTime(randomArray_4, 3);
		measureTime(randomArray_5, 4);
		measureTime(randomArray_6, 5);
		measureTime(randomArray_7, 6);
		measureTime(randomArray_8, 7);
		measureTime(randomArray_9, 8);
		measureTime(randomArray_10, 9);
		measureTime(randomArray_11, 10);
		measureTime(randomArray_12, 11);
		measureTime(randomArray_13, 12);
		measureTime(randomArray_14, 13);
		measureTime(randomArray_15, 14);
		measureTime(randomArray_16, 15);
		measureTime(randomArray_17, 16);
		measureTime(randomArray_18, 17);
		measureTime(randomArray_19, 18);
	}
	
	
	/**
	 * 	PopulateArray will add randomized integers from -100 to 100 (I chose these numbers 
	 * 	because anything higher is unnecessary in terms of this project and the requirements)
	 * @param arrayLength: The given array length that is incrementing by 5
	 * @return tempArray: The array that contains randomized integers
	 * 
	 * Source: https://www.tutorialspoint.com/generate-a-random-array-of-integers-in-java#:~:text=In%20order%20to%20generate%20random,this%20random%20number%20generator%20sequence.
	 */
	private static int[] populateArray(int arrayLength) {	
		Random random = new Random();
		int[] tempArray = new int[arrayLength];

		for(int i = 0; i < arrayLength; i++)
			tempArray[i] = random.nextInt(200) - 100;
		
		return tempArray;
	}
	
	
	/**
	 * measureTime will measure the average running time for each algorithm on each of the 19 arrays.
	 * 	This function will also handle measuring T(n) for each algorithm as well.
	 * @param tempArray: The given array that could have a length from 10-100
	 * 
	 * Source: // https://www.programiz.com/java-programming/examples/calculate-methods-execution-time
	 */
	public static void measureTime(int[] tempArray, int row) {
		
		long start = 0, end = 0, execution = 0;
		
		int avgTimeAlg1 = 0, avgTimeAlg2 = 0, avgTimeAlg3 = 0, avgTimeAlg4 = 0;
		double tn_Alg1 = 0, tn_Alg2 = 0, tn_Alg3 = 0, tn_Alg4 = 0;
		
		int arrayLength = tempArray.length;
		
		for(int i = 0; i < 500; i++) {
			
			// *** Algorithm 1 Time Measurements ***
			start = System.nanoTime();
			int max = algorithm1(tempArray);
			end = System.nanoTime();
		
			execution = end - start;
			avgTimeAlg1 += execution;
			System.out.println(avgTimeAlg1);
			
			// *** Algorithm 2 Time Measurements ***
			start = System.nanoTime();
			max = algorithm2(tempArray);
			end = System.nanoTime();
			
			execution = end - start;
			avgTimeAlg2 += execution;
			
			// *** Algorithm 3 Time Measurements ***
			start = System.nanoTime();
			max = maxSum(tempArray, 0, arrayLength-1);
			end = System.nanoTime();
		
			execution = end - start;
			avgTimeAlg3 += execution;
			
			// *** Algorithm 3 Time Measurements ***
			start = System.nanoTime();
			max = algorithm4(tempArray);
			end = System.nanoTime();
		
			execution = end - start;
			avgTimeAlg4 += execution;
		}
		
		// Getting the average 
		avgTimeAlg1 = avgTimeAlg1 / 500;
		avgTimeAlg2 = avgTimeAlg2 / 500;
		avgTimeAlg3 = avgTimeAlg3 / 500;
		avgTimeAlg4 = avgTimeAlg4 / 500;

		// Calculating each algorithm T(n)
		tn_Alg1 = calculateTN(arrayLength, 1);
		tn_Alg2 = calculateTN(arrayLength, 2);
		tn_Alg3 = calculateTN(arrayLength, 3);
		tn_Alg4 = calculateTN(arrayLength, 4);
		
		
		int[] arrayOfTime = { avgTimeAlg1, avgTimeAlg2, avgTimeAlg3, avgTimeAlg4, (int) tn_Alg1, (int) tn_Alg2, (int) tn_Alg3, (int) tn_Alg4 };
		
		// Filling the 19x8 matrix with all the necessary values
		for(int j = 0; j < 8; j++) {
			matrix[row][j] = arrayOfTime[j];
		}
			

	}
	
	
	/**
	 * calculateTN will calculate the time complexity, the calculations are provided
	 * 	in the TimeComplexity document.
	 * @param n: the input size number
	 * @param algorithmNum: the algorithm we are testing
	 * @return tn: the calculate time complexity of the algorithm
	 * 
	 * Source: https://www.geeksforgeeks.org/math-pow-method-in-java-with-example/
	 */
	private static double calculateTN(int n, int algorithmNum) {
		
		double tn = 0;
		switch(algorithmNum) {
			case 1:
				tn = ((7/2) * Math.pow(n, 3)) + (3 * Math.pow(n, 2)) + ((33/2) * n) + 3;
				break;
				
			case 2:
				tn = Math.pow(n, 2) + ((17/2) * n) + 4;
				break;
				
			case 3:
				tn = (12 * n)* (Math.log(2)/Math.log(n)) + (12 * n);
				break;
				
			case 4: 
				tn = (13 * n) + 5;
				break;
		}
		
		return tn;
	}
	
	/**
	 * outputToFile will take the global variable: "matrix"
	 * 	and print the contents out to a file
	 * 
	 * 	Source: https://www.w3schools.com/java/java_files_create.asp
	 */
	public static void outputToFile() {
		
		try {
			File file = new File("SarahPham_phw_output.txt");
			FileWriter fileWriter = new FileWriter("SarahPham_phw_output.txt");

			fileWriter.write("algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n),T4(n)\n");

			for(int i = 0; i < 19; i++) {
				for(int j = 0; j < 8; j++) {
					String value = Integer.toString(matrix[i][j]);
					fileWriter.write(value);
					
					if(j < 7) fileWriter.write(",");
						
				}
				fileWriter.write("\n");
			}
			
			fileWriter.close();
		
		}
		catch(IOException e) { System.out.println(e); }
		
	}
	
	
}
