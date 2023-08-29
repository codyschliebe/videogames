package video.games.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import video.games.entity.Console;
import video.games.entity.Game;
import video.games.entity.Genre;
import video.games.entity.Review;

@Data
@NoArgsConstructor
public class GameData {
	// create variables in data object
	private Long gameId;
	private String gameTitle;
	private String gameReleaseYear;
	private String gameDeveloper;
	private String gameSeries;
	//private Long consoleId;
	private Set<GameGenre> genres = new HashSet<>();
	private GameConsole console;
	private GameReview review;

	public GameData(Game game) {
		gameId = game.getGameId();
		gameTitle = game.getGameTitle();
		gameReleaseYear = game.getGameReleaseYear();
		gameDeveloper = game.getGameDeveloper();
		gameSeries = game.getGameSeries();
		//consoleId = console.getConsoleId();

		for (Genre genre : game.getGenres()) {
			genres.add(new GameGenre(genre));
		}

		genres = getGenres();
		console = getConsole();
		review = getReview();
	}

	@Data
	@NoArgsConstructor
	public static class GameConsole {
		private Long consoleId;
		private String consoleName;
		private String consoleGeneration;
		private String consoleManufacturer;

		public GameConsole(Console console) {
			//consoleId = console.getConsoleId();
			consoleName = console.getConsoleName();
			consoleGeneration = console.getConsoleGeneration();
			consoleManufacturer = console.getConsoleManufacturer();
		}
	}

	@Data
	@NoArgsConstructor
	public static class GameReview {
		private Long reviewId;
		private Integer reviewScore;
		private String reviewText;
		private Long gameId;

		public GameReview(Review review) {
			reviewId = review.getReviewId();
			reviewScore = review.getReviewScore();
			reviewText = review.getReviewText();
			gameId = getGameId();
		}

	}

	@Data
	@NoArgsConstructor
	public static class GameGenre {
		private Long genreId;
		private String genreName;
		private String genreType;

		public GameGenre(Genre genre) {
			genreId = genre.getGenreId();
			genreName = genre.getGenreName();
			genreType = genre.getGenreType();
		}
	}
}