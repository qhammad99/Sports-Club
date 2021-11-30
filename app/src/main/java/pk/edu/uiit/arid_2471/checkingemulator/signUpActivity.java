package pk.edu.uiit.arid_2471.checkingemulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class signUpActivity extends AppCompatActivity {

    Button submit;
    TextView logInPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        submit=findViewById(R.id.sbtBtn);
        logInPage=findViewById(R.id.signBtn);

        logInPage.setOnClickListener((View v)->{
            Intent intent=new Intent(signUpActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        });

        submit.setOnClickListener((View v)->{
            Intent intent=new Intent(signUpActivity.this, homeActivity.class);
            startActivity(intent);
            finish();
        });


    }
}