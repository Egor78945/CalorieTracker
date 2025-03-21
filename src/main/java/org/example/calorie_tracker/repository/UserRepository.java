package org.example.calorie_tracker.repository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Tag(name = "User repository", description = "Репозиторий таблицы пользователей")
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User set goalId=?2 where email=?1")
    @Operation(description = "Изменить цель пользователя")
    void changeUserGoalIdByUserIdAndGoalId(String email, long goalId);

    @Operation(description = "найти пользователя по email")
    Optional<User> findUserByEmail(String email);

    @Query("select goalId from User where email=?1")
    @Operation(description = "Получить цель пользователя по email")
    Optional<Long> findUserGoalIdByUserEmail(String email);
}
