package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Statistics {

	@Id
	private String statName;
	
	private int statCheck;

	public String getStatName() {
		return statName;
	}

	public Statistics() {
		super();
		this.statName = "";
		this.statCheck = 0;
	}
	public Statistics(String statName, int statCheck) {
		super();
		this.statName = statName;
		this.statCheck = statCheck;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public int getStatCheck() {
		return statCheck;
	}

	public void setStatCheck(int statCheck) {
		this.statCheck = statCheck;
	}
	
}
