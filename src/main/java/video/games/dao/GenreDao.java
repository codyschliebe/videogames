package video.games.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import video.games.entity.Genre;

public interface GenreDao extends JpaRepository<Genre, Long>{

}
