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
        List<String> ingredientsList = new ArrayList<>();
        List<String> knownAsList = new ArrayList<>();
        String mainName = "";

        try {
            JSONObject jsonObject = new JSONObject(json);

            if (jsonObject.has(PRODUCT_BY_NAME)) {
                JSONObject name = jsonObject.getJSONObject(PRODUCT_BY_NAME);
                mainName = name.optString(MAIN_NAME);
                if (name.has(ALSO_KNOWN_AS)) {
                    JSONArray knownAdArray = name.getJSONArray(ALSO_KNOWN_AS);
                    knownAsList = getListFromJsonArray(knownAdArray);
                }
            }

            String placeOfOrigin = jsonObject.has(PLACE_OF_ORIGIN) ? jsonObject.optString(PLACE_OF_ORIGIN) : "";
            String description = jsonObject.has(DESCRIPTION) ? jsonObject.optString(DESCRIPTION) : "";
            String image = jsonObject.has(IMAGE) ? jsonObject.optString(IMAGE) : "";

            if (jsonObject.has(INGREDIENTS)) {
                JSONArray ingredientsArray = jsonObject.getJSONArray(INGREDIENTS);
                ingredientsList = getListFromJsonArray(ingredientsArray);
            }
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
