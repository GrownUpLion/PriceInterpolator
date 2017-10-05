package concreteClasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import interfaces.IAmountReader;

public class PriceReader implements IAmountReader{

	@Override
	public List<Integer> readAmounts() throws Exception {
		
		List<Integer> amounts= new ArrayList<Integer>();
		String fileName = "./input.txt";
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.filter(s-> (Integer.valueOf(s) >= 0)).forEach(s-> amounts.add((Integer.valueOf(s))));

		} catch (IOException e) {
			System.out.println("Error while loading the input file");
			throw e;
		}
		catch(NumberFormatException e)
		{
			System.out.println("There is a non valid amount in the input file");
			throw e;
		}
		return amounts;
	}

}
