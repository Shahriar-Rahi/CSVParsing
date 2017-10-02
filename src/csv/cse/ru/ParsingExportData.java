package csv.cse.ru;


import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
	
	
	public static void main(String[] args) {
		ParsingExportData parExDa = new ParsingExportData();
        parExDa.testCountryInfo();
        FileResource fr = new FileResource();
        parExDa.listExportsTwoPorducts(fr.getCSVParser(), "gold", "diamonds");
        int numOfCountries = parExDa.numberOfExports(fr.getCSVParser(), "gold");
	    System.out.println(numOfCountries + " Countries Export " + "gold\n");
	    numOfCountries = parExDa.numberOfExports(fr.getCSVParser(), "diamond");
	    System.out.println(numOfCountries + " Countries Export " + "diamond\n");
	    parExDa.bigExporters(fr.getCSVParser(), "$400,000,000");
	}
	
	public void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord record : parser){
			System.out.println(record.get("Country") + "\t" + record.get("Exports") + "\t" + record.get("Value (dollars)"));
		}
	}
	
	public String countryInfo(String country, CSVParser parser) {
	    for(CSVRecord record : parser) {
	    	if(record.get("Country").equals("Germany")) 
	    		return record.get("Country") + ":"+record.get("Exports") + " : " + record.get("Value (dollars)");
	    }
	    return "NOT FOUND";
	}
	
    public void testCountryInfo() {
    	FileResource fr = new FileResource();
    	CSVParser parser = fr.getCSVParser();
        String result = countryInfo("Germany",parser);
        System.out.println(result);
        result = countryInfo("Germany",parser);
        System.out.println(result);
    }
    
    public void listExportsTwoPorducts(CSVParser parser,String exportItem1, String exportItem2){
    	for(CSVRecord record : parser) {
    		if( record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem1))
    	        System.out.println(record.get("Country"));
    	}
    }
    
    public int numberOfExports(CSVParser parser,String item){
    	int count = 0;
    	for(CSVRecord record : parser){
    		if(record.get("Exports").contains(item))
    			count++;
    	}
        return count;
    }
    public Long convertInToNumber(String str) {
    	String amount = "";
    	for(int i=0; i< str.length();i++) {
    		char digi = str.charAt(i);
    	    switch(digi) {
    	        case '0':
    	        case '1':
    	        case '2':
    	        case '3':
    	        case '4':
    	        case '5':
    	        case '6':
    	        case '7':
    	        case '8':
    	        case '9':
    	            amount += digi;
    	        	break;
    	        default:
    	            break;
    	    }
    	}
        return Long.parseLong(amount);
    }
    public void bigExporters(CSVParser parser, String amount) {
    	Long tmpAmount = convertInToNumber(amount);
    	for(CSVRecord record : parser){
    		Long tmpAmo = convertInToNumber(record.get("Value (dollars)")); 
    	    if(tmpAmo >= tmpAmount) {
    	    	System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
    	    }
    	}
    }
}
