package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StoreProduct {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String prodStoreNameID;
	

	@ManyToOne
	@JoinColumn(name = "productID")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;
	
	private int numberOfProduct;
	private int numberOfSoldProduct;
	private int numberOfVisitedProduct;
	private int discount;
	
	public StoreProduct() 
	{
		super();
		this.product = new Product();
		this.store = new Store();
		this.numberOfProduct = 0;
		this.numberOfSoldProduct = 0;
		this.numberOfVisitedProduct = 0;
		this.discount = 0;
	}
	
	public StoreProduct(Product product, Store store, int numberOfProduct) 
	{
		super();
		this.product = product;
		this.store = store;
		this.prodStoreNameID = product.getCode();
		this.numberOfProduct = numberOfProduct;
		this.numberOfSoldProduct = 0;
		this.numberOfVisitedProduct = 0;
		this.discount = 0;
	}

	
	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getProdStoreNameID() {
		return prodStoreNameID;
	}

	public void setProdStoreNameID(String prodStoreNameID) {
		this.prodStoreNameID = prodStoreNameID;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public int getNumberOfProduct() {
		return numberOfProduct;
	}
	public void setNumberOfProduct(int numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}
	public int getNumberOfSoldProduct() {
		return numberOfSoldProduct;
	}
	public void setNumberOfSoldProduct(int numberOfSoldProduct) {
		this.numberOfSoldProduct = numberOfSoldProduct;
	}
	public int getNumberOfVisitedProduct() {
		return numberOfVisitedProduct;
	}
	public void setNumberOfVisitedProduct(int numberOfVisitedProduct) {
		this.numberOfVisitedProduct = numberOfVisitedProduct;
	}
	
}
