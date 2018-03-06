package Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private String Brand;
	@Id
	private String Code = "";
	
	private String Description;
	private String Type;
	private String Price;

	
	public Product()
	{
		super();
		productName = "";
		Brand = "";
		Type = "";
		Price = "";
		Description = "";
		Code = "";
	}
	public Product(String productName, String Brand , String Code, String Description , String Type, String Price )
	{
		super();
		this.productName = productName;
		this.Brand = Brand;
		this.Type = Type;
		this.Price = Price;
		this.Code = Code;
		this.Description = Description;
	}
	public void setName(String productName)
	{
		this.productName = productName;
	}
	public void setBrand(String Brand)
	{
		this.Brand = Brand;
	}
	public void setType(String Type)
	{
		this.Type = Type;
	}
	public void setCode(String  Code)
	{
		this.Code = Code;
	}
	public void setDescription(String Description)
	{
		this.Description =Description;
	}
	public void setPrice(String  Price)
	{
		this.Price = Price;
	}
	
	public String getName(String ProductName)
	{
		return this.productName;
	}

	public String getCode()
	{
		return this.Code;
	}
	public String getPrice()
	{
		return this.Price;
	}
	public String getDescription()
	{
		return this.Description;
	}
	public String getBrand()
	{
		return this.Brand;
	}
	public String getType()
	{
		return this.Type;
	}
}
