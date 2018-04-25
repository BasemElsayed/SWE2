package Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String brandName;
	private String brandCategory;
	private String brandDescription;
	
	public Brand()
	{
		super();
		brandName = "";
		brandCategory = "";
		brandDescription = "";
	}

	public Brand(String brandName, String brandCategory, String brandDescription)
	{
		super();
		this.brandName = brandName;
		this.brandCategory = brandCategory;
		this.brandDescription = brandDescription;
	}
	
	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

	public String getBrandCategory() {
		return brandCategory;
	}

	public void setBrandCategory(String brandCategory) {
		this.brandCategory = brandCategory;
	}
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	

}
