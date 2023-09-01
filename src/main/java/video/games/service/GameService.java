package video.games.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import video.games.controller.model.GameData;
import video.games.controller.model.GameData.GameConsole;
import video.games.controller.model.GameData.GameGenre;
import video.games.controller.model.GameData.GameReview;
import video.games.dao.ConsoleDao;
import video.games.dao.GameDao;
import video.games.dao.GenreDao;
import video.games.dao.ReviewDao;
import video.games.entity.Console;
import video.games.entity.Game;
import video.games.entity.Genre;
import video.games.entity.Review;

@Service
@Slf4j
public class GameService {

	@Autowired
	private GameDao gameDao;

	@Autowired
	private ConsoleDao consoleDao;

	@Autowired
	private GenreDao genreDao;

	@Autowired
	private ReviewDao reviewDao;

	// ***************************Save Game**********************************

	@Transactional(readOnly = false)
	public GameData saveGame(GameData gameData) {
		Long gameId = gameData.getGameId();
		Game game = findOrCreateGame(gameId);

		copyGameFields(gameData, game);
		return new GameData(gameDao.save(game));
	}

	private Game findOrCreateGame(Long gameId) {
		if (Objects.isNull(gameId)) {
			return new Game();
		} else {
			return findGameById(gameId);
		}
	}

	private Game findGameById(Long gameId) {
		return gameDao.findById(gameId)
				.orElseThrow(() -> new NoSuchElementException("Game with ID=" + gameId + " was not found."));
	}

	private void copyGameFields(GameData gameData, Game game) {
		game.setGameId(gameData.getGameId());
		game.setGameTitle(gameData.getGameTitle());
		game.setGameReleaseYear(gameData.getGameReleaseYear());
		game.setGameDeveloper(gameData.getGameDeveloper());
		game.setGameSeries(gameData.getGameSeries());
	}

	// ***************************Console***********************************

	@Transactional(readOnly = false)
	public GameConsole saveConsole(Long gameId, GameConsole gameConsole) {
		Game game = findGameById(gameId);	//pull in game
		Long consoleId = gameConsole.getConsoleId();	//get consoleId from request data
		Console console = findOrCreateConsole(gameId, consoleId);	//find/create from consoleId

		copyConsoleFields(console, gameConsole);	//populate console with request data
		
		console.getGames().add(game);	//add game to games set

		game.setConsole(console);	//set console in the game object

		Console dbConsole = consoleDao.save(console);	//save console object

		return new GameConsole(dbConsole);	//return console data object
	}

	private Console findOrCreateConsole(Long gameId, Long consoleId) {
		if (Objects.isNull(consoleId)) {
			return new Console();
		} else {
			return findConsoleById(gameId, consoleId);
		}
	}

	private Console findConsoleById(Long gameId, Long consoleId) {
		Console console = consoleDao.findById(consoleId)
				.orElseThrow(() -> new NoSuchElementException("Console with ID=" + consoleId + " was not found."));
		boolean found = false;
		for (Game game : console.getGames()) {
			if (game.getGameId().equals(gameId)) {
				found = true;
			}
			if (!found) {
				throw new IllegalArgumentException("Console ID does not share a connection with Game ID.");
			}
		}
		return console;
	}

	private void copyConsoleFields(Console console, GameConsole gameConsole) {
		console.setConsoleId(gameConsole.getConsoleId());
		console.setConsoleName(gameConsole.getConsoleName());
		console.setConsoleGeneration(gameConsole.getConsoleGeneration());
		console.setConsoleManufacturer(gameConsole.getConsoleManufacturer());

	}

	// *****************************Genre*****************************************

	@Transactional(readOnly = false)
	public GameGenre saveGenre(Long gameId, GameGenre gameGenre) {
		Game game = findGameById(gameId);
		Long genreId = gameGenre.getGenreId();
		Genre genre = findOrCreateGenre(gameId, genreId);

		copyGenreFields(genre, gameGenre);

		genre.getGames().add(game);
		game.getGenres().add(genre);
		Genre dbGenre = genreDao.save(genre);
		return new GameGenre(dbGenre);
	}

	private Genre findOrCreateGenre(Long gameId, Long genreId) {
		if (Objects.isNull(genreId)) {
			return new Genre();
		} else {
			return findGenreById(gameId, genreId);
		}
	}

	private Genre findGenreById(Long gameId, Long genreId) {
		Genre genre = genreDao.findById(genreId)
				.orElseThrow(() -> new NoSuchElementException("Genre with ID=" + genreId + " was not found."));
		boolean found = false;
		for (Game game : genre.getGames()) {
			if (game.getGameId() == gameId) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new IllegalArgumentException("Genre ID does not share a connection with Game ID.");
		}
		return genre;
	}

	private void copyGenreFields(Genre genre, GameGenre gameGenre) {
		genre.setGenreId(gameGenre.getGenreId());
		genre.setGenreName(gameGenre.getGenreName());
		genre.setGenreType(gameGenre.getGenreType());
	}

	// **************************REVIEW******************************************

	@Transactional(readOnly = false)
	public GameReview saveReview(Long gameId, GameReview gameReview) {
		Game game = findGameById(gameId);
		Long reviewId = gameReview.getReviewId();
		Review review = findOrCreateReview(gameId, reviewId);

		copyReviewFields(review, gameReview);

		review.setGame(game);
		game.setReview(review);
		Review dbReview = reviewDao.save(review);
		return new GameReview(dbReview);
	}

	private Review findOrCreateReview(Long gameId, Long reviewId) {
		if (Objects.isNull(reviewId)) {
			return new Review();
		} else {
			return findReviewById(gameId, reviewId);
		}
	}

	private Review findReviewById(Long gameId, Long reviewId) {
		Review review = reviewDao.findById(reviewId)
				.orElseThrow(() -> new NoSuchElementException("Review with ID=" + reviewId + " was not found."));
		boolean found = false;
		if (review.getGame().getGameId().equals(gameId)) {
			found = true;
		}
		if (!found) {
			throw new IllegalArgumentException("Review ID does not share a connection with Game ID.");
		}
		return review;
	}

	private void copyReviewFields(Review review, GameReview gameReview) {
		review.setReviewId(gameReview.getReviewId());
		review.setReviewText(gameReview.getReviewText());
		review.setReviewScore(gameReview.getReviewScore());
	}

	// **************************RETRIEVE ALL GAMES****************************

	@Transactional
	public List<GameData> retrieveAllGames() {
		List<GameData> listGameData = new LinkedList<>();
		List<Game> listGame = gameDao.findAll();

		for (Game game : listGame) {
			GameData gameData = new GameData(game);

			// gameData.getConsole();
			// gameData.getGenres().clear();
			// gameData.getReview();

			listGameData.add(gameData);
		}
		return listGameData;
	}

	// ***************************RETRIEVE ALL GENRES**************************

	@Transactional
	public List<GameGenre> retrieveAllGenres() {
		List<GameGenre> listGameGenre = new LinkedList<>();
		List<Genre> listGenre = genreDao.findAll();

		for (Genre genre : listGenre) {
			GameGenre gameGenre = new GameGenre(genre);

			listGameGenre.add(gameGenre);
		}
		return listGameGenre;
	}

	// ************************RETRIEVE ALL CONSOLES****************************

	@Transactional
	public List<GameConsole> retrieveAllConsoles() {
		List<GameConsole> listGameConsole = new LinkedList<>();
		List<Console> listConsole = consoleDao.findAll();

		for (Console console : listConsole) {
			GameConsole gameConsole = new GameConsole(console);
			listGameConsole.add(gameConsole);
		}
		return listGameConsole;
	}

	// *****************************LIST BY ID***********************************
	public GameData retrieveGameById(Long gameId) {
		Game game = findGameById(gameId);
		GameData gameData = new GameData(game);

		return gameData;
	}

	// ****************************LIST BY GENRENAME********************************

	public Genre retrieveGenreByName(String genreName) {
		log.info("Retrieving Genre with name=" + genreName + "...");
		List<Genre> listGenres = genreDao.findAll();
		Genre returnGenre = new Genre();
		for (Genre genre : listGenres) {
			if (genre.getGenreName().equals(genreName)) {
				returnGenre = genre;
			}
		}
		return returnGenre;
	}

	public List<GameData> retrieveAllGamesByGenre(Genre genre) {
		log.info("Retrieving all games of genre " + genre.getGenreName() + "...");
		List<Game> listGames = gameDao.findAll(); // pull all games into list
		List<GameData> listGameData = new ArrayList<GameData>(); // create new array to store list of game DTOs
		for (Game game : listGames) { // iterate through list of games
			if (game.getGenres().contains(genre)) { // find if genre associated with game matches genre from
				// build DTO from found game
				GameData newGameData = new GameData(game);
				// add DTO to list of game DTOs
				listGameData.add(newGameData);
			}
		}
		return listGameData;
	}

	// ***************************LIST BY CONSOLE NAME****************************

	// find console object from PathVariable consoleName
	public Console retrieveConsoleByName(String consoleName) {
		log.info("Retrieving Console with name=" + consoleName + "...");
		List<Console> listConsoles = consoleDao.findAll();
		//log.info("Found consoles " + listConsoles); FOR TESTING PURPOSES
		Console returnConsole = new Console();
		for (Console console : listConsoles) {
			if (console.getConsoleName().equals(consoleName)) {
				returnConsole = console;
			}
		}
		return returnConsole;
	}

	// retrieve games matching console
	public List<GameData> retrieveAllGamesByConsole(Console console) {
		log.info("Retrieving all games on console " + console.getConsoleName() + "...");
		List<Game> listGames = gameDao.findAll();
		List<GameData> listGameData = new ArrayList<GameData>();
		for (Game game : listGames) {
			if (game.getConsole().equals(console)) {
				GameData newGameData = new GameData(game);
				listGameData.add(newGameData);
			}
		}
		return listGameData;
	}
//	
//	private List<Game> findGameByConsoleId(Long consoleId) {
//		List<Game> listGames = gameDao.findAll();
//		for (Game game : listGames) {
//			if (game.getConsoleId() == consoleId) {
//				listGames.add(game);
//			}
//		}
//		return listGames;
//	}

	// *****************************DELETE BY ID**********************************
	public void deleteGameById(Long gameId) {
		Game game = findGameById(gameId);
		gameDao.delete(game);
	}

}
