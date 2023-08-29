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
import jakarta.persistence.PrimaryKeyJoinColumn;
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

	// defining relationship with genre table
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "game_genre", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Genre> genres = new HashSet<>();

	// defining relationship with console table
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "console_id", nullable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Console console;

	// defining relationship with review table
	@OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Review review;

	public Long getConsoleId(Console console) {
		Long consoleId = console.getConsoleId();
		return consoleId;
	}
}
