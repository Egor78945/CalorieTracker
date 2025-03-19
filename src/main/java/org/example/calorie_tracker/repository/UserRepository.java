package org.example.calorie_tracker.repository;

import org.example.calorie_tracker.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User set goalId=?2 where email=?1")
    void changeUserGoalIdByUserIdAndGoalId(String email, long goalId);

    Optional<User> findUserByEmail(String email);

    @Query("select goalId from User where email=?1")
    Optional<Long> findUserGoalIdByUserEmail(String email);
}
