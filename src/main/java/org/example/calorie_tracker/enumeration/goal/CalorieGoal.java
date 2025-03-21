package org.example.calorie_tracker.enumeration.goal;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Calorie goal", description = "Цели пользователя")
public enum CalorieGoal {
    @Parameter(name = "Похудение") LOSS(1),
    @Parameter(name = "Набор массы") INCREASE(2),
    @Parameter(name = "Удержание массы на текущем уровне") HOLD(3);

    @Parameter(name = "Id цели", description = "Id ели в базе данных")
    private long id;

    CalorieGoal(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
