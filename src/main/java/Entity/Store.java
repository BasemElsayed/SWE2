package Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Store implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String storeName;
	private String storeAddress;
	private String storeCategory;
	private String storeStatus;
	private String storeType;
	
	@ManyToOne
	@JoinColumn(name = "user_email")
	private User user;

	
	
	public Store()
	{
		super();
		storeName = "";
		storeAddress = "";
		storeCategory = "";
		storeStatus = "";
		storeType = "";
		user = new User();
	}
	
	public Store(String storeName, String storeAddress, String storeCategory, String storeType, User user)
	{
		super();
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.storeCategory = storeCategory;
		this.storeType = storeType;
		this.storeStatus = "0";
		this.user = user;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
	public String getStoreCategory() {
		return storeCategory;
	}
	public void setStoreCategory(String storeCategory) {
		this.storeCategory = storeCategory;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
