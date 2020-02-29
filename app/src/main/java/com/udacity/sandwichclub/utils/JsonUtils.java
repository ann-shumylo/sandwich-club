package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String PRODUCT_BY_NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject name = jsonObject.getJSONObject(PRODUCT_BY_NAME);
            String mainName = name.optString(MAIN_NAME);

            JSONArray knownAdArray = name.getJSONArray(ALSO_KNOWN_AS);
            List<String> knownAsList = getListFromJsonArray(knownAdArray);

            String placeOfOrigin = jsonObject.optString(PLACE_OF_ORIGIN);
            String description = jsonObject.optString(DESCRIPTION);
            String image = jsonObject.optString(IMAGE);

            JSONArray ingredientsArray = jsonObject.getJSONArray(INGREDIENTS);
            List<String> ingredientsList = getListFromJsonArray(ingredientsArray);

            return new Sandwich(mainName, knownAsList, placeOfOrigin, description, image, ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> getListFromJsonArray(JSONArray jsonArray) {
        List<String> ingredientsList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            ingredientsList.add(jsonArray.optString(i));
        }
        return ingredientsList;
    }
}
