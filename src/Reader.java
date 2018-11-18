/*
 * Program:	Reader.java
 * Author: 	Muhammad Ahmed
 * Course:	CS 427
 * Date	:	18-Nov-2018
 * Assignment # 3
 * Description:	This class handles reading the data from .dat file into the
 * 				program for manipulation.
 * 				Currently it only supports Single Channel audio file.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	//Member variables
	List<Double> data = new ArrayList<Double>();
	List<Double> timeIndex = new ArrayList<Double>();
	
	/*
	 * Name: 		Reader()
	 * Purpose:		Constructor for the class 
	 * Parameters:	Null
	 * Returns:		Null 
	 */
	Reader()
	{
		
	}
	
	/*
	 * Name: 		importData()
	 * Purpose:		Reads the string lines from the specified file
	 * 				and casts the string to doubles for x and y data sets. 
	 * Parameters:	Null
	 * Returns:		Null 
	 */
	public void importData()
			throws IOException {
		// Data file location declared
		String file ="src/mT.dat"; 
		
		BufferedReader readerO = new BufferedReader(new FileReader(file));
		String t="";
		
		//Loop through the file until end of file to read each line and split x and y data from
		//the string into 2 separate doubles 
		while((t=readerO.readLine())!=null){
			t= t.trim();
			String[] td = t.split(" ", 2)	; 	

			data.add(Double.parseDouble(td[1]));
			timeIndex.add(Double.parseDouble(td[0]));
		}

		//Close reader
		readerO.close();

	}

	/*
	 * Name: 		getData()
	 * Purpose:		Get y data for the given sample index
	 * Parameters:	Integer (index)
	 * Returns:		Double (value)
	 */
	public double getData(int i)
	{
		return data.get(i);
	}
	
	/*
	 * Name: 		getData()
	 * Purpose:		Get x data for the given sample index
	 * Parameters:	Integer (index)
	 * Returns:		Double (value)
	 */
	public double getTimeData(int i)
	{
		return timeIndex.get(i);
	}

	
	/*
	 * Name: 		getYData()
	 * Purpose:		Get the entire y data set from the imported sound file
	 * Parameters:	null
	 * Returns:		Array<Double>
	 */
	public double[] getYData()
	{
		double[] tempA =new double[data.size()];
		for (int i=0; i<data.size();i++)
		{
			tempA[i]=data.get(i);
		}
		return tempA;
	}
	
	
	/*
	 * Name: 		getXData()
	 * Purpose:		Get the entire x data set from the imported sound file
	 * Parameters:	null
	 * Returns:		Array<Double>
	 */
	public double[] getXData()
	{
		double[] tempA =new double[timeIndex.size()];
		for (int i=0; i<timeIndex.size();i++)
		{
			tempA[i]=timeIndex.get(i);
		}
		return tempA;
	}

}
