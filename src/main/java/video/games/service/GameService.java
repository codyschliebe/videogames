package video.games.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class GameService {
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private ConsoleDao consoleDao;
	
	@Autowired
	private GenreDao genreDao;
	
	@Autowired
	private ReviewDao reviewDao;

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
				.orElseThrow(() -> new NoSuchElementException(
						"Game with ID=" + gameId + " was not found."));
	}
	
	private void copyGameFields(GameData gameData, Game game) {
		game.setGameId(gameData.getGameId());
		game.setGameTitle(gameData.getGameTitle());
		game.setGameReleaseYear(gameData.getGameReleaseYear());
		game.setGameDeveloper(gameData.getGameDeveloper());
		game.setGameSeries(gameData.getGameSeries());
	}
	
	//***************************Console***********************************
	
	@Transactional(readOnly = false)
	public GameConsole saveConsole(Long gameId, GameConsole gameConsole) {
		Game game = findGameById(gameId);
		Long consoleId = gameConsole.getConsoleId();
		Console console = findOrCreateConsole(gameId, consoleId);
		
		copyConsoleFields(console, gameConsole);
		
		Set<Game> gameSet = console.getGames();
		gameSet.add(game);
		game.setConsole(console);
		return new GameConsole(consoleDao.save(console));
				
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
				.orElseThrow(() -> new NoSuchElementException(
						"Console with ID=" + consoleId + " was not found."));
		if (((GameData) console.getGames()).getGameId() == gameId) {
			return console;
		} else {
			throw new IllegalArgumentException(
					"Console ID does not share a connection with Game ID");
		}
	}
	
	private void copyConsoleFields(Console console, GameConsole gameConsole) {
		console.setConsoleId(gameConsole.getConsoleId());
		console.setConsoleName(gameConsole.getConsoleName());
		console.setConsoleGeneration(gameConsole.getConsoleGeneration());
		console.setConsoleManufacturer(gameConsole.getConsoleManufacturer());
	}
	
	//*****************************Genre*****************************************
	
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
		Genre genre = genreDao.findById(genreId).orElseThrow(() -> new NoSuchElementException(
				"Genre with ID=" + genreId + " was not found."));
		boolean found = false;
		for (Game game : genre.getGames()) {
			if(game.getGameId() == gameId) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new IllegalArgumentException(
					"Customer ID does not share a connection with Game ID.");
		}
		return genre;
	}
	
	private void copyGenreFields(Genre genre, GameGenre gameGenre) {
		genre.setGenreId(gameGenre.getGenreId());
		genre.setGenreName(gameGenre.getGenreName());
		genre.setGenreType(gameGenre.getGenreType());
	}
	
	//**************************REVIEW******************************************
	
	@Transactional(readOnly = false)
	public GameReview saveReview(Long gameId, GameReview gameReview) {
		Game game = findGameById(gameId);
		Long reviewId = gameReview.getReviewId();
		Review review = findOrCreateReview(gameId, reviewId);
		
		copyReviewFields(review, gameReview);
		
		//Game game = review.getGame();
		//gameSet.add(game);
		review.getGame().setGameId(gameId);
		game.setReview(review);
		
		return new GameReview(reviewDao.save(review));
				
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
				.orElseThrow(() -> new NoSuchElementException(
						"Review with ID=" + reviewId + " was not found."));
		if (review.getGame().getGameId() == gameId) {
			return review;
		} else {
			throw new IllegalArgumentException(
					"Review ID does not share a connection with Game ID");
		}
	}
	
	private void copyReviewFields(Review review, GameReview gameReview) {
		review.setReviewId(gameReview.getReviewId());
		review.setReviewScore(gameReview.getReviewScore());
		review.setReviewText(gameReview.getReviewText());
	}
	
	//**************************RETRIEVE ALL GAMES****************************
	
	@Transactional
	public List<GameData> retrieveAllGames() {
		List<GameData> listGameData = new LinkedList<>();
		List<Game> listGame = gameDao.findAll();
		
		for (Game game : listGame) {
			GameData gameData = new GameData(game);
			
			//gameData.getConsole().clear();
			//gameData.getGenres().clear();
			//gameData.getReview().clear();
			
			listGameData.add(gameData);
		}
		return listGameData;
	}
	
	//***************************RETRIEVE ALL GENRES**************************
	
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
	
	//************************RETRIEVE ALL CONSOLES****************************
	
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
	
	//*****************************LIST BY ID***********************************
	public GameData retrieveGameById(Long gameId) {
		Game game = findGameById(gameId);
		GameData gameData = new GameData(game);
		
		return gameData;
	}
	
	//****************************LIST BY GENREID********************************
//	public List<GameData> retrieveAllGamesByGenreName(String genreName) {
//		Game game = findGameByGenre(genreName);
//	GameData gameData = new GameData(game);
//		
//	}
//	
//	private Game findGameByGenre(String genreName) {
//		List<Game> listGames = gameDao.findAll();
//		List<GameData> listGameData;
//		for(Game game : listGames) {
//			if (game)
//		}
//		if (listGames.getGenre)
//		return null;
//	}
	
	//***************************LIST BY CONSOLE********************************
	
	public List<GameData> retrieveAllGamesByConsole(Long consoleId) {
		Game game = findGamebyConsoleId(consoleId);
		GameData gameData = new GameData(game);
		
		private List<GameData> findGameByConsoleId(Long consoleId) {
			List<Game> listGames = gameDao.findAll();
			List<GameData> listGameData;
			for (Game game : listGames) {
				if (game.getConsoleId() == consoleId) {
					listGames.add(game);
				}
			}
			return listGameData;
		}
	}

	//*****************************DELETE BY ID**********************************
	public void deleteGameById(Long gameId) {
		Game game = findGameById(gameId);
		gameDao.delete(game);
	}
	
	
}
