package pk.edu.uaar.group_sports_club.sports_club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class MainActivity extends AppCompatActivity {

    private final int splashActivityTime=2700;
    Animation topAnimation;
    ImageView img;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
               for full screen
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        topAnimation= AnimationUtils.loadAnimation(this, R.anim.top_animation);

        img=findViewById(R.id.logo);
        img.setAnimation(topAnimation);
        sharedPreferences=getSharedPreferences("login_preference", MODE_PRIVATE);
        /*
            Setting timer
         */
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreferences.contains("id")){
                    String id= sharedPreferences.getString("id","").toString();
                    String adminId="1";
                    if(id.equals(adminId.toString())){
                        Intent intent = new Intent(MainActivity.this, MainActivityAdmin.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, homeActivity.class);
                        startActivity(intent);
                    }
                }
                else{
                    Intent intent=new Intent(MainActivity.this,loginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, splashActivityTime);
    }
}