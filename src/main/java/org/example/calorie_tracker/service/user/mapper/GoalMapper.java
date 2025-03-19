package org.example.calorie_tracker.service.user.mapper;

import org.example.calorie_tracker.enumeration.goal.CalorieGoal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GoalMapper {
    private static final Map<String, CalorieGoal> goalByName;
    private static final Map<Long, CalorieGoal> goalById;

    static {
        goalByName = new HashMap<>();
        goalById = new HashMap<>();
        Arrays.stream(CalorieGoal.values()).forEach(g -> {
            goalByName.put(g.name(), g);
            goalById.put(g.getId(), g);
        });
    }

    public static CalorieGoal mapTo(String goalName) {
        return goalByName.get(goalName.toUpperCase());
    }

    public static CalorieGoal mapTo(long goalId){
        return goalById.get(goalId);
    }
}
