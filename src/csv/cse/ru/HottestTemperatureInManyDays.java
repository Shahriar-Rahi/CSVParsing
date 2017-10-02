package csv.cse.ru;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class HottestTemperatureInManyDays {
	
	public static void main(String[] args) {
		HottestTemperatureInManyDays hot = new HottestTemperatureInManyDays();
		hot.testHottestInManyDays();
	}
	
	public CSVRecord hottestHourInFile(CSVParser parser) {
		CSVRecord largestSoFar = null;
		for (CSVRecord currentRow : parser) {
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		return largestSoFar;
	}

	public void testHottestInDay () {
		FileResource fr = new FileResource();
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("TimeEST"));
	}

	public CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		return largestSoFar;
	}

	public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
		if (largestSoFar == null) {
			largestSoFar = currentRow;
		}
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
			if (currentTemp > largestTemp) {
				largestSoFar = currentRow;
			}
		}
		return largestSoFar;
	}

	public void testHottestInManyDays() {
		CSVRecord largest = hottestInManyDays();
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("DateUTC"));
	}
}
