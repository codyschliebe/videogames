package video.games.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import video.games.entity.Console;

public interface ConsoleDao extends JpaRepository<Console, Long> {

}
