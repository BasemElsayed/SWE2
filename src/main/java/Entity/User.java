package Entity;

import java.io.Serializable;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String Password;
	private double balance;


	@Id
	private String Email = "";
	private String Type;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Store> stores;
	
	
	
	public Set<Store> getStores() {
		return stores;
	}
	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}
	public User()
	{
		super();
		userName = "";
		Password = "";
		Type = "";
		Email = "";
		Random rand = new Random();
		balance = rand.nextInt(10000) + 5000;
	}
	public User(String userName, String Password, String Email, String Type)
	{
		super();
		this.userName = userName;
		this.Password = Password;
		this.Type = Type;
		this.Email = Email;
		Random rand = new Random();
		balance = rand.nextInt(10000) + 5000;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}
	public void setType(String Type)
	{
		this.Type = Type;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	public String getPassword()
	{
		return this.Password;
	}
	public String getEmail()
	{
		return this.Email;
	}
	
	public String getType()
	{
		return this.Type;
	}
	
	
	
}
