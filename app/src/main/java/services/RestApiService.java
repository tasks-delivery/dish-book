package services;

import java.util.List;

import entity.Dish;
import entity.Ingredient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface RestApiService {

    @GET("getDishes")
    List<Dish> getDishes();

    @GET("getDish")
    Dish getDish(String dishName);

    @GET("getIngredients")
    List<Ingredient> getIngredients();

    @GET("getIngredient")
    List<Ingredient> getIngredient(String ingName);

}
