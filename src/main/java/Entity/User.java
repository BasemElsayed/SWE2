package Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String Password;
	@Id
	private String Email = "";
	private String Type;
	
	public User()
	{
		super();
		userName = "";
		Password = "";
		Type = "";
		Email = "";
	}
	public User(String userName, String Password, String Email, String Type)
	{
		super();
		this.userName = userName;
		this.Password = Password;
		this.Type = Type;
		this.Email = Email;
	}
	
	public void setName(String userName)
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
