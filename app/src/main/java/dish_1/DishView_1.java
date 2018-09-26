package dish_1;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import services.RestApiService;

public interface DishView_1 {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:7070/")
            .build();

    DishView_1 service = retrofit.create(DishView_1.class);

    void openDish_3();

    @GET("about")
    void openDish_2();

    void openIngredient_1();

    void openIngredient_3();





}
