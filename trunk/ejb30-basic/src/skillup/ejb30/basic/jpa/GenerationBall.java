package skillup.ejb30.basic.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="GenerationBall")
public class GenerationBall implements Serializable {

	private static final long serialVersionUID = 4500286997735546338L;
	
	@TableGenerator(
			name="GeneratedIDG001", 
			table="GeneratedID",
			pkColumnName="pk_column",
			valueColumnName="pk_value",
			pkColumnValue="ballId")
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="GeneratedIDG001")
	private int ballId;
	
	private String comments;


	public int getBallId() {
		return ballId;
	}

	public void setBallId(int ballId) {
		this.ballId = ballId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenerationBall [ballId=").append(ballId).append(
				", comments=").append(comments).append("]");
		return builder.toString();
	}
	
	
}
