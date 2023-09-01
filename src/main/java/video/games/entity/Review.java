package video.games.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Review {

	//declare review variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="review_id")
	private Long reviewId;
	private Integer reviewScore;
	private String reviewText;

	//define relationship with game table
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Game game;

}
