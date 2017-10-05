package Main;

import concreteClasses.PriceInterpolator;
import concreteClasses.PriceReader;


public class MainClass {

	public static void main(String[] args) {
		try{

		SaleCalculator calculator = new SaleCalculator(new PriceReader(), new PriceInterpolator());
			calculator.readAmounts();
			calculator.CalculatePrice();
		}catch (Exception e)
		{
			System.out.println("Terminating with errors");

		}
		
	}

}
