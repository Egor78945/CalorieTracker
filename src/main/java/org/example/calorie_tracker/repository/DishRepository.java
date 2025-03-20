package org.example.calorie_tracker.repository;

import org.example.calorie_tracker.model.dish.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAllByUserEmail(String email);
    @Query(value = """
            from Dish a
            where a.date = (select max(b.date)
            from Dish b
            where b.userEmail = ?1) and a.userEmail=?1
            order by a.date
            """)
    List<Dish> findAllByUserEmailPerLastDay(String email);
    int findCalorieSumPerLastDayByUserEmail(String email);
}
