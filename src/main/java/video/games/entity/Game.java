package video.games.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Game {
	// Declare variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "game_id")
	private Long gameId;
	private String gameTitle;
	private String gameReleaseYear;
	private String gameDeveloper;
	private String gameSeries;
	//private Long consoleId;

	// defining relationship with genre table
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "game_genre", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Genre> genres = new HashSet<>();

	// defining relationship with console table
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "console_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Console console;

	// defining relationship with review table
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Review review;

//	public Long getConsoleId(Console console) {
//		Long consoleId = console.getConsoleId();
//		return consoleId;
//	}
}
