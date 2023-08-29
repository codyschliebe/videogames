package video.games.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import video.games.entity.Review;

public interface ReviewDao extends JpaRepository<Review, Long> {

}
