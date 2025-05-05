package com.healthcare.healthapp.Controller;

import com.healthcare.healthapp.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/api/diet")
public class DietController {

    @Autowired
    private MealService mealService;

    @GetMapping("/search")
    public ResponseEntity<String> searchMeals(
            @RequestParam String cuisine,
            @RequestParam String diet,
            @RequestParam String intolerances,
            @RequestParam(defaultValue = "5") int number) {
        String result = mealService.searchMeals(cuisine, diet, intolerances, number);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getMealDetails(@PathVariable String id) {
        Map<String, Object> customMealDetails = mealService.getMealDetails(id);
        return ResponseEntity.ok(customMealDetails);
    }

}
