package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class storeOwnerActions {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String actionName;
	private int numberOfProduct;
	private int numberOfSoldProduct;
	private int numberOfVisitedProduct;
	
	@ManyToOne
	@JoinColumn(name = "productID")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;
	

	public storeOwnerActions() {
		super();
		this.actionName = "";
		this.product = new Product();
		this.store = new Store();
		this.numberOfProduct = 0;
		this.numberOfSoldProduct = 0;
		this.numberOfVisitedProduct = 0;
	}

	public storeOwnerActions(String actionName, int numberOfProduct, int numberOfSoldProduct,
			int numberOfVisitedProduct, Product product, Store store) {
		super();
		this.actionName = actionName;
		this.numberOfProduct = numberOfProduct;
		this.numberOfSoldProduct = numberOfSoldProduct;
		this.numberOfVisitedProduct = numberOfVisitedProduct;
		this.product = product;
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

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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

	
	
}
