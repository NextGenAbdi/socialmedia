package com.restics.socialmedia.repository;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LikeRepository {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LikeRepository.class);
    private final JdbcTemplate jdbc;

    public LikeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public boolean likedByUserId(int userId, int postId) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM PostLike WHERE user_id = ? AND post_id = ?",
                Integer.class,
                userId, postId
        );
        return count != null && count > 0;
    }

    public int likePost(int userId, int postId) {
        return jdbc.update(
                "INSERT INTO PostLike (user_id, post_id) VALUES (?, ?) ON CONFLICT DO NOTHING",
                userId, postId
        );
    }

    public int unlikePost(int userId, int postId) {
        return jdbc.update(
                "DELETE FROM PostLike WHERE user_id = ? AND post_id = ?",
                userId, postId
        );
    }

    public int getPostLikes(int postId) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM PostLike WHERE post_id = ?",
                Integer.class,
                postId
        );
        return count != null ? count : 0;
    }


}
