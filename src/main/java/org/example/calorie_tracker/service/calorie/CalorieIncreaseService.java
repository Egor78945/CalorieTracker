package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.repository.UserRepository;

public class CalorieIncreaseService extends CalorieService {
    public CalorieIncreaseService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public int getCaloriePerDayForUserByEmail(String email) {
        double baseBMR = getBaseBMR(email);
        return (int)(baseBMR + (1.55 * baseBMR * 0.15));
    }
}
