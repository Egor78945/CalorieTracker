package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.repository.UserRepository;

public class CalorieHoldService extends CalorieService {
    public CalorieHoldService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public int getCaloriePerDayForUserByEmail(String email) {
        return (int) getBaseBMR(email);
    }
}
