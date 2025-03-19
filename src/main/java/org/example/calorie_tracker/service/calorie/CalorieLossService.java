package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.repository.UserRepository;

public class CalorieLossService extends CalorieService {
    public CalorieLossService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public int getCaloriePerDayForUserByEmail(String email) {
        double baseBMR = getBaseBMR(email);
        return (int)(baseBMR - (baseBMR * 1.55 * 0.17));
    }
}
