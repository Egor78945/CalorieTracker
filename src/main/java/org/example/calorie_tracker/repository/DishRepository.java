package org.example.calorie_tracker.repository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.dish.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Tag(name = "Dish repository", description = "Репозиторий таблицы блюд")
public interface DishRepository extends JpaRepository<Dish, Long> {
    @Operation(description = "Получить список всех блюд пользователя по email")
    List<Dish> findAllByUserEmail(String email);

    @Query(value = """
            from Dish
            where date = ?2 and userEmail=?1
            order by date
            """)
    @Operation(description = "Получить список всех блюд пользователя, съеденных за сегодня")
    Optional<List<Dish>> findAllByUserEmailPerLastDay(String email, LocalDate now);
}
