package com.healthcare.healthapp.Service;

import com.healthcare.healthapp.Repository.MealDao;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MealService {

    private final MealDao mealDao;

    public MealService(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    public String searchMeals(String cuisine, String diet, String intolerances, int number) {
        return mealDao.fetchMealsFromApi(cuisine, diet, intolerances, number);
    }

    public Map<String, Object> getMealDetails(String id) {
        return mealDao.fetchAndCustomizeMealDetails(id);
    }

}

