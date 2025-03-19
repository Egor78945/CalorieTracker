package org.example.calorie_tracker.enumeration.goal;

public enum CalorieGoal {
    LOSS(1), INCREASE(2), HOLD(3);
    private long id;

    CalorieGoal(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
