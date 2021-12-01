package pk.edu.uaar.group_sports_club.sports_club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class signUpActivity extends AppCompatActivity {

    Button next;
    TextView logInPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        next=findViewById(R.id.nextBtn);
        logInPage=findViewById(R.id.signBtn);

        logInPage.setOnClickListener((View v)->{
            Intent intent=new Intent(signUpActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        });

        next.setOnClickListener((View v)->{
            Intent intent=new Intent(signUpActivity.this, signUp2.class);
            startActivity(intent);
            finish();
        });


    }
}