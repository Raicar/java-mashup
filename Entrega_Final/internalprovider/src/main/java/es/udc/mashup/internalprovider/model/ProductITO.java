package es.udc.mashup.internalprovider.model;

import java.util.Calendar;

public class ProductITO {

	protected String name = null;
	protected String description = null;
	protected String category = null;
	protected double price = 0;
	protected double discount = 0.85;
	protected String imageURL = null;
	protected Calendar date = null;
	
	public ProductITO() {}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String value)
	{
		this.name = value;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String value)
	{
		this.description = value;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String value)
	{
		this.category = value;
	}
	
	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double value) 
	{
		this.price = value;
	}
	
	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String value) {
        this.imageURL = value;
    }
    
    public Calendar getDate() {
    	return date;
    }

    public void setDate(Calendar value) {
    	this.date = value;
    }
    
}
