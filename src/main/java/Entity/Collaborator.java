package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Collaborator {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "storeOwnerID")
	private User storeOwner;
	
	@ManyToOne
	@JoinColumn(name = "storeID")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name = "collaboratorID")
	private User collaborator;

	
	public Collaborator() {
		super();
		this.storeOwner = new User();
		this.store = new Store();
		this.collaborator = new User();
	}
	public Collaborator(User storeOwner, Store store, User collaborator) {
		super();
		this.storeOwner = storeOwner;
		this.store = store;
		this.collaborator = collaborator;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getStoreOwner() {
		return storeOwner;
	}

	public void setStoreOwner(User storeOwner) {
		this.storeOwner = storeOwner;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public User getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(User collaborator) {
		this.collaborator = collaborator;
	}

	
	
	
}
