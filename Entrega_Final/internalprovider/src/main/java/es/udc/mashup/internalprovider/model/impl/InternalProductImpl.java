package es.udc.mashup.internalprovider.model.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.mashup.internalprovider.model.ProductITO;
import es.udc.mashup.internalprovider.model.InternalProductService;

public class InternalProductImpl implements InternalProductService {
	
	private static List<ProductITO> internalProducts;
	
	static {
		
		internalProducts = new ArrayList<ProductITO>();

        ProductITO product00 = new ProductITO();
        product00.setName("TR-40975 Power");
        product00.setCategory("Laptops");
        product00.setDescription("Attractive design; 14.5-inch size offers same resolution as 15.6-inch laptops while saving a bit of size and weight; awesome keyboard; above-average audio.");
        product00.setPrice(1600);
        Calendar c0 = setTime(2013,7,1,14,10,30);
        product00.setDate(c0);

        ProductITO product01 = new ProductITO();
        product01.setName("XZ-2098 Portable Slim");
        product01.setCategory("Laptops");
        product01.setDescription("Despite retaining the same price and look as last year's model, XZ2098 Pro has significant CPU updates and fantastic battery life make it one of the top laptops we've reviewed, provided you can live with passable integrated graphics.");
        product01.setPrice(900);
        Calendar c1 = setTime(2013,7,1,14,10,30);
        product01.setDate(c1);

        ProductITO product02 = new ProductITO();
        product02.setName("LP-76388 Notebook");
        product02.setCategory("Laptops");
        product02.setDescription("Though it's still in the upper ranges of current laptop prices, LP-76388 offers great hardware at a decent price.");
        product02.setPrice(1250);
        Calendar c2 = setTime(2013,7,1,14,10,30);
        product02.setDate(c2);
        
        ProductITO product03 = new ProductITO();
        product03.setName("Macbook Air");
        product03.setCategory("Laptops");
        product03.setDescription("Product 3 Facebook MBA");
        product03.setPrice(1130);
        Calendar c3 = setTime(2013,8,9,14,10,30);
        product03.setDate(c3);
        
        ProductITO product04 = new ProductITO();
        product04.setName("Sony Vaio");
        product04.setCategory("Laptops");
        product04.setDescription("Product 4 Facebook");
        product04.setPrice(1000);
        Calendar c4 = setTime(2013,8,6,18,1,30);
        product04.setDate(c4);
        
        ProductITO product05 = new ProductITO();
        product05.setName("Hewlett Packard Laptop");
        product05.setCategory("Laptops");
        product05.setDescription("Product 5 Facebook");
        product05.setPrice(800);
        Calendar c5 = setTime(2013,8,6,12,10,30);
        product05.setDate(c5);

        internalProducts.add(product00);
        internalProducts.add(product00);
        internalProducts.add(product01);
        internalProducts.add(product01);
        internalProducts.add(product02);
        internalProducts.add(product00);
        internalProducts.add(product01);
        internalProducts.add(product00);
        internalProducts.add(product02);
        internalProducts.add(product01);
        internalProducts.add(product00);
        internalProducts.add(product02);
        internalProducts.add(product02);
        internalProducts.add(product01);
        internalProducts.add(product00);
        internalProducts.add(product01);
        internalProducts.add(product00);
        internalProducts.add(product01);
        internalProducts.add(product00);
        internalProducts.add(product01);
        internalProducts.add(product00);
        internalProducts.add(product01);
        internalProducts.add(product00);
        internalProducts.add(product00);
        internalProducts.add(product01);
        internalProducts.add(product03);
        internalProducts.add(product04);
        internalProducts.add(product05);
	
	}
	
	private static Calendar setTime(int year, int month, int day, int hour, 
			int minute, int second) {
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);                 
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		return c;
	}
	
	public InternalProductImpl() {	}

	public List<ProductITO> findProducts(String productName, String category,
			double minPrice, double maxPrice)
	{
		
		List<ProductITO> products = new ArrayList<ProductITO>();

		for (ProductITO pInt : internalProducts)
		{
			if (pInt.getName().toLowerCase().contains(productName.toLowerCase()) && 
				pInt.getPrice() >= minPrice && pInt.getPrice() <= maxPrice && 
				(category == null || pInt.getCategory().equals(category)) )
				
				products.add(pInt);
		}
        
        return products;
		
	}

	@Override			
	public List<ProductITO> findProductsByDate(Calendar firstDate, 
			Calendar secondDate)
	{
				
		List<ProductITO> products = new ArrayList<ProductITO>();
		
		for (ProductITO pIt : internalProducts)
		{
			if (pIt.getDate() != null) {
				if (pIt.getDate().after(firstDate) && 
					pIt.getDate().before(secondDate))
						products.add(pIt);
			}
		}
		
		return products;
	}
	
}
