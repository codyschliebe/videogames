package video.games.entity;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Genre {
	//declare genre variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="genre_id")
	private Long genreId;
	private String genreName;
	private String genreType;
	
	//define relationship with game table
	@ManyToMany(mappedBy="genres", cascade=CascadeType.PERSIST)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Game> games = new HashSet<>();

}
