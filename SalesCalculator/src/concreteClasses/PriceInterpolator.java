package concreteClasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import interfaces.IPriceCalculator;

public class PriceInterpolator implements IPriceCalculator{

	private LinkedHashMap<Integer, Double> priceTable = new LinkedHashMap<Integer, Double>();
	
	
	public PriceInterpolator() throws Exception {
		
		/*priceTable.put(1, 29d);
		priceTable.put(10, 20d);
		priceTable.put(100, 10d);
		priceTable.put(500, 8d);
		priceTable.put(1000, 7d);
		priceTable.put(5000, 5d);
		priceTable.put(10000,2d);*/
		priceTable.put(0,0d);
		loadTable();
	}

	@Override
	public double getPrice(int amount) {
		if (priceTable.containsKey(amount)) return priceTable.get(amount);
		else return priceInterpolation(amount);
	}

	private double priceInterpolation(int amount) {
		double beforeValue = 0,afterValue = 0;
		int keybefore = 0, keyAfter = 0;
		Map.Entry<Integer,Double> me = null;
        Iterator<Map.Entry<Integer,Double>> i =  priceTable.entrySet().iterator();
        Boolean found = false;
        while (i.hasNext() && !found) {
        	me = (Map.Entry<Integer,Double>) i.next();
            if((int)me.getKey() > amount) {
                found = true;
            }
            beforeValue = afterValue;
            keybefore = keyAfter;
            afterValue = (double) me.getValue();
            keyAfter =  (int) me.getKey();          
        }  
        if(!found) return (double) me.getValue();
        else return ((amount-keybefore)*(afterValue-beforeValue))/((keyAfter -keybefore)) + beforeValue;
	}

	
	private void loadTable() throws Exception
	{
		String fileName = "./table.txt";
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(s-> (priceTable.put(Integer.valueOf(s.split(" ")[0]), Double.parseDouble(s.split(" ")[1]))));

		}catch (IOException e) {
			System.out.println("Error while loading the table file");
			throw e;
		}
		catch(NumberFormatException e)
		{
			System.out.println("There is a non valid value in the table file");
			throw e;
		}
	}
}
