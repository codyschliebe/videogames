package video.games.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import video.games.entity.Game;

public interface GameDao extends JpaRepository<Game, Long> {

}
