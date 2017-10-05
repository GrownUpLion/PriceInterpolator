/**
 * 
 */
package Main;

import java.util.List;

import interfaces.*;

/**
 * @author juntas
 *
 */
public class SaleCalculator {

	public IAmountReader amountReader;
	public IPriceCalculator priceCalculator; 
	
	public List<Integer> prices;
	
	/**
	 * 
	 */
	public SaleCalculator(IAmountReader amountReader,IPriceCalculator priceCalculator) {
		
		this.amountReader = amountReader;
		this.priceCalculator = priceCalculator;
	}
	
	public void readAmounts() throws Exception
	{
		this.prices = amountReader.readAmounts();
	}
	
	public void CalculatePrice() throws Exception
	{
		for(Integer amount : prices){
			System.out.println(priceCalculator.getPrice(amount));
		}
	}

}
