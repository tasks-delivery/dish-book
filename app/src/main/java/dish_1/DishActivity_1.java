package dish_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.book.core.R;
import dish_2.DishActivity_2;
import dish_3.DishActivity_3;
import entity.Message;
import ingredient_1.IngredientActivity_1;
import ingredient_3.IngredientActivity_3;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.MessagesApi;
import services.RestApi;

public class DishActivity_1 extends AppCompatActivity implements DishView_1 {

    private DishPresenter_1 dishPresenter_1;

    private RestApi restApi;

    @BindView(R.id.dish_1_btn_add_dish)
    Button dish_1_btn_add_dish;

    @BindView(R.id.dish_1_btn_all_dishes)
    Button dish_1_btn_all_dishes;

    @BindView(R.id.dish_1_btn_add_ingredient)
    Button dish_1_btn_add_ingredient;

    @BindView(R.id.dish_1_btn_all_ingredients)
    Button dish_1_btn_all_ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());
        setContentView(R.layout.activity_dish_1);
        ButterKnife.bind(this);
        dishPresenter_1 = new DishPresenter_1(this);
    }

    @OnClick(R.id.dish_1_btn_add_dish)
    public void goToAddDish() {
        dishPresenter_1.openDish_2();
    }

    @OnClick(R.id.dish_1_btn_all_dishes)
    public void goToDishList() {
        dishPresenter_1.openDish_3();
    }

    @OnClick(R.id.dish_1_btn_add_ingredient)
    public void goToAddIngredient() {
        dishPresenter_1.openIngredient_1();
    }

    @OnClick(R.id.dish_1_btn_all_ingredients)
    public void goToIngredientList() {
        dishPresenter_1.openIngredient_3();
    }

    @Override
    public void openDish_3() {
        startActivity(new Intent(this, DishActivity_3.class));
    }

    @Override
    public void openDish_2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessagesApi messagesApi = retrofit.create(MessagesApi.class);



        Call<List<Message>> messages = messagesApi.messages();

        messages.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
               response.body();
               System.out.println(response.body().get(1).getText());
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });



        /*
        given().
                when().
                get("http://localhost:7070/about").
                then().
                statusCode(200).
                extract().response().print();
*/
        Intent intent = new Intent(DishActivity_1.this, DishActivity_2.class);
        intent.putExtra("navigation", "DishActivity1");
        startActivity(intent);
    }

    @Override
    public void openIngredient_1() {
        Intent intent = new Intent(DishActivity_1.this, IngredientActivity_1.class);
        intent.putExtra("navigation", "DishActivity1");
        startActivity(intent);
    }

    @Override
    public void openIngredient_3() {
        Intent intent = new Intent(DishActivity_1.this, IngredientActivity_3.class);
        intent.putExtra("navigation", "dishActivity1");
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }

}
