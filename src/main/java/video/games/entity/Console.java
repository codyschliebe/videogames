package video.games.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Console {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consoleId;
	private String consoleName;
	private String consoleGeneration;
	private String consoleManufacturer;
	
	@OneToMany(mappedBy = "console", cascade = CascadeType.PERSIST,
			orphanRemoval = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Game> games = new HashSet<>();
}
