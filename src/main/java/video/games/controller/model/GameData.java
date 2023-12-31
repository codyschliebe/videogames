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
	private Set<GameGenre> genres = new HashSet<>();
	private GameConsole console;
	private GameReview review;

	public GameData(Game game) {
		gameId = game.getGameId();
		gameTitle = game.getGameTitle();
		gameReleaseYear = game.getGameReleaseYear();
		gameDeveloper = game.getGameDeveloper();
		gameSeries = game.getGameSeries();

		for (Genre genre : game.getGenres()) {
			genres.add(new GameGenre(genre));
		}

		genres = getGenres();
		
		//allow GameData creation with null console and review
		if (game.getConsole() != null) {
			console = new GameConsole(game.getConsole());
		}
		if (game.getReview() != null) {
			review = new GameReview(game.getReview());
		}
	}

	@Data
	@NoArgsConstructor
	public static class GameConsole {
		private Long consoleId;
		private String consoleName;
		private String consoleGeneration;
		private String consoleManufacturer;

		public GameConsole(Console console) {
			consoleId = console.getConsoleId();
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
		// private Game game;

		public GameReview(Review review) {
			reviewId = review.getReviewId();
			reviewScore = review.getReviewScore();
			reviewText = review.getReviewText();
			gameId = review.getGame().getGameId();
			// game = review.getGame();
		}

	}

	@Data
	@NoArgsConstructor
	public static class GameGenre {
		private Long genreId;
		private String genreName;
		private String genreType;
		// private Set<GameData> games = new HashSet<>();

		public GameGenre(Genre genre) {
			genreId = genre.getGenreId();
			genreName = genre.getGenreName();
			genreType = genre.getGenreType();

			// for (Game game : genre.getGames()) {
			// games.add(new GameData(game));
			// }
		}
	}
}