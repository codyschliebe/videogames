package video.games.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import video.games.controller.model.GameData;
import video.games.controller.model.GameData.GameConsole;
import video.games.controller.model.GameData.GameGenre;
import video.games.controller.model.GameData.GameReview;
import video.games.entity.Genre;
import video.games.service.GameService;



@RestController
@RequestMapping("/video_games")
@Slf4j
public class GameController {
	@Autowired
	private GameService gameService;
	
//	@Autowired
//	private GenreDao genreDao;
	
	//********************* Create New Game ***************************
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public GameData postGame(@RequestBody GameData gameData) {
		log.info("Creating new game {}", gameData);
		gameData = gameService.saveGame(gameData);
		return gameService.saveGame(gameData);
	}
	
	//******************* Modify Existing Game ************************
	@PutMapping("/{gameId}")
	public GameData updateGame(@PathVariable Long gameId, 
			@RequestBody GameData gameData) {
		gameData.setGameId(gameId);
		log.info("Updating game entry {}", gameData);
		return gameService.saveGame(gameData);
	}
	
	//*********************Create New Console****************************
	@PostMapping("/{gameId}/console")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GameConsole saveGameConsole(@PathVariable Long gameId, 
			@RequestBody GameConsole gameConsole) {
		log.info("Creating console...");
		return gameService.saveConsole(gameId,  gameConsole);
	}
	
	//*********************Create New Genre******************************
	@PostMapping("/{gameId}/genre")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GameGenre saveGameGenre(@PathVariable Long gameId, 
			@RequestBody GameGenre gameGenre) {
		log.info("Creating genre...");
		return gameService.saveGenre(gameId, gameGenre);
	}
	
	//*********************Create New Review*****************************
	@PostMapping("/{gameId}/review")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GameReview saveGameRevew(@PathVariable Long gameId, 
			@RequestBody GameReview gameReview) {
		log.info("Creating review...");
		return gameService.saveReview(gameId, gameReview);
	}
	
	//*********************List All Games********************************
	@GetMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public List<GameData> gameListAll() {
		log.info("Retrieving all games...");
		return gameService.retrieveAllGames();
	}
	
	//********************List All Genres********************************
	@GetMapping("/genres")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public List<GameGenre> genreListAll() {
		log.info("Retrieving all genres...");
		return gameService.retrieveAllGenres();
	}
	
	//********************List All Consoles******************************
		@GetMapping("/consoles")
		@ResponseStatus(code = HttpStatus.ACCEPTED)
		public List<GameConsole> consoleListAll() {
			log.info("Retrieving all consoles...");
			return gameService.retrieveAllConsoles();
		}
	
	//******************List All Games by Genre***************************
	@GetMapping("/genre/{genreName}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public List<GameData> gameListByGenre(@PathVariable String genreName) {
		log.info("Retrieving all games of genre " + genreName + "...");
		//List<Genre> genreList = genreDao.findAll();
		Genre retrievedGenre = gameService.retrieveGenreByName(genreName);
		return gameService.retrieveAllGamesByGenre(retrievedGenre);
	}
	
	//*************************Retrieve Game By ID***********************
	@GetMapping("/{gameId}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public GameData retrieveGameById(@PathVariable Long gameId) {
		log.info("Retrieving Game with ID=" + gameId + "...");
		return gameService.retrieveGameById(gameId);
	}
	
	//*************************Delete Game by ID*************************
	@DeleteMapping("/{gameId}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Map<String, String> deleteGameById(@PathVariable Long gameId) {
		log.info("Deleting game with ID=" + gameId + "...");
		gameService.deleteGameById(gameId);
		return Map.of("Message", "Game with ID=" + gameId + " has been deleted.");
	}
	
	

}
