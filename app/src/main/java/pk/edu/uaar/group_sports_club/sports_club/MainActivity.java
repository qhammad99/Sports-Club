package pk.edu.uaar.group_sports_club.sports_club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class MainActivity extends AppCompatActivity {

    private final int splashActivityTime=2700;
    Animation topAnimation;
    ImageView img;

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
        /*
            Setting timer
         */
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        }, splashActivityTime);
    }
}