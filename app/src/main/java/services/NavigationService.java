package services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class NavigationService extends AppCompatActivity {

    public String navigationResponse(String key) {
        Intent intent = getIntent();
        return intent.getStringExtra(key);
    }

    public void navigationRequest(Intent intent, String key, String value){
        intent.putExtra(key,value);
        startActivity(intent);
    }

}
