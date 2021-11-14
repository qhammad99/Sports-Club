package pk.edu.uiit.arid_2471.checkingemulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class loginActivity extends AppCompatActivity {
    Button submit, signupPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        signupPage=findViewById(R.id.signBtn);
        submit=findViewById(R.id.sbtBtn);

        signupPage.setOnClickListener((View v)->{
            Intent intent=new Intent(loginActivity.this, signUpActivity.class);
            startActivity(intent);
            finish();
        });

        submit.setOnClickListener((View v)->{
            Intent intent=new Intent(loginActivity.this, homeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}