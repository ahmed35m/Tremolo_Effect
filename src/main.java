/*
 * Program:	main.java
 * Author: 	Muhammad Ahmed
 * Course:	CS 427
 * Date	:	18-Nov-2018
 * Assignment # 3
 * Description:	This program uses Reader.java and Writer.java classes to read
 * 				 sound data from a dat file, stored in the program's src folder
 * 				 and applies Tremolo effect on the data. The data is written back 
 * 				 to a data file.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class main {

	// Declaring constants
	static double pi = 3.147 ;
	static double sampleRate =44100;

	// Arrays to hold real and imaginary values of data
	static double[] x ;
	static double[] y;

	public static void main(String[] args) {
		Reader r = new Reader();	
		try {
			r.importData();			// Step 1 - Import sound data from dat file
		} catch (IOException e) { 	// Fall back procedure/handling
			e.printStackTrace();
			System.out.println("Unable to import audio data");
		}

		applyEffect(r,5);			//Step 2 - Apply Tremolo effect with 5Hz freq
		writeTremolo(x,y);	//Step 3 - Output results to dat file

	}

	/*
	 * Name: 		writeTremolo()
	 * Purpose:		Generates a .dat file or overrides an existing one with given array 
	 * 				members to produce a sound data file 
	 * Parameters:	Array<Double> X-axis data , Array<Double> Y-axis data
	 * Returns:		Null 
	 */

	public static void writeTremolo(double[] x, double[]y) {

		// Location to store the file
		String FileLoc = System.getProperty("user.home") + "/Desktop/output.dat";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FileLoc))) {
			//Write header information of sound file
			bw.write("; Sample Rate 44100");
			bw.newLine();
			bw.write("; Channels 1");
			bw.newLine();

			String content ;

			// Loop through the arrays to write on the specified file
			for(int i=0; i<x.length;i++) {
				content = "   "+Double.toString(x[i]) +"   "+Double.toString(y[i]);
				bw.write(content);
				bw.newLine();
			}
			//Print success message
			System.out.println("A file has been generated and store in :"+FileLoc);

		} catch (IOException e) { //Error handling

			e.printStackTrace();

		}	
	}

	/*
	 * Name: 		applyEffect
	 * Purpose:		Apply Tremolo effect on the given data with the given frequencey 
	 * Parameters:	Reader (r) , Double (Frequency)
	 * Returns:		Null 
	 */
	public static void applyEffect(Reader t, double Fc) {
		// Load data into arrays from Reader object
		y = t.getYData();	
		x =t.getXData();

		//Depth of Tremolo effect set
		double a =0.5; 		

		double tremEffect; 	

		// Loop through the sound y-data point and multiply them with the calcualted Tremolo factor/co-effecient
		for (int i=0; i<x.length; i++) {
			tremEffect = 1+ (a* Math.sin(2*pi*i*Fc/sampleRate ));
			y[i] = y[i] *tremEffect;
		}	
	}



}

