package org.example.calorie_tracker.repository;

import org.example.calorie_tracker.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    @Query("update User set goalId=?2 where email=?1")
    @Transactional
    @Modifying
    void changeUserGoalIdByUserIdAndGoalId(String email, long goalId);
}
