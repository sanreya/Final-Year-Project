package com.healthcare.healthapp.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MealDao {

    private static final String API_KEY = "1682f92a122c48b195a8d4ea84643c6d";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/complexSearch";

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchMealsFromApi(String cuisine, String diet, String intolerances, int number) {
        String url = String.format("%s?cuisine=%s&diet=%s&intolerances=%s&number=%d&apiKey=%s",
                BASE_URL, cuisine, diet, intolerances, number, API_KEY);

        return restTemplate.getForObject(url, String.class);
    }

    public Map<String, Object> fetchAndCustomizeMealDetails(String id) {
        String url = String.format(
                "https://api.spoonacular.com/recipes/%s/information?apiKey=%s", id, API_KEY);

        Map<String, Object> fullResponse = restTemplate.getForObject(url, Map.class);


        List<Map<String, Object>> ingredients = (List<Map<String, Object>>) fullResponse.get("extendedIngredients");

        // Modify response
        Map<String, Object> customResponse = new HashMap<>();
        customResponse.put("title", fullResponse.get("title"));
        customResponse.put("image", fullResponse.get("image"));
        customResponse.put("readyInMinutes", fullResponse.get("readyInMinutes"));
        customResponse.put("servings", fullResponse.get("servings"));
        customResponse.put("summary", fullResponse.get("summary"));
        customResponse.put("instructions", fullResponse.get("instructions"));
        customResponse.put("ingredients", ingredients);


        return customResponse;
    }

}

