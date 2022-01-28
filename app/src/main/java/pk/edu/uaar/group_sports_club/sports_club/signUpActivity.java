package pk.edu.uaar.group_sports_club.sports_club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class signUpActivity extends AppCompatActivity {

    EditText name, mail, phone,city, pass, cPass;
    String gender;
    boolean genderDone=false;
    Button next;
    TextView logInPage;
    databaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        name=findViewById(R.id.etName);
        mail=findViewById(R.id.etMail);
        phone=findViewById(R.id.etPhone);
        city=findViewById(R.id.etCity);
        pass=findViewById(R.id.etPwd);
        cPass=findViewById(R.id.cPwd);

        db=new databaseHelper(this);

        next=findViewById(R.id.nextBtn);
        logInPage=findViewById(R.id.signBtn);

        logInPage.setOnClickListener((View v)->{
            Intent intent=new Intent(signUpActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        });


        next.setOnClickListener((View v)->{

            String u_name=name.getText().toString();
            String u_mail=mail.getText().toString();
            String u_phone=phone.getText().toString();
            String u_city=city.getText().toString();
            String u_pass=pass.getText().toString();
            String u_c_pass=cPass.getText().toString();

            if(u_name.equals("")||u_mail.equals("")||u_phone.equals("")||u_city.equals("")||u_pass.equals("")||u_c_pass.equals("") || !genderDone){
                Toast.makeText(signUpActivity.this, "Must fill all fields", Toast.LENGTH_LONG).show();
            }
            else if(!pass.getText().toString().equals(cPass.getText().toString())) {
                Toast.makeText(signUpActivity.this, "password and confirm password must be same", Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent(signUpActivity.this, signUp2.class);
                intent.putExtra("name",u_name);
                intent.putExtra("mail", u_mail);
                intent.putExtra("phone", u_phone);
                intent.putExtra("city", u_city);
                intent.putExtra("gender",gender);
                intent.putExtra("pass", u_pass);
                startActivity(intent);
            }
        });


    }

    public void genderClicked(View view){
            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();

            genderDone=true;
            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.genderMale:
                    if (checked)
                        gender="Male";
                        break;
                case R.id.genderFemale:
                    if (checked)
                        gender="Female";
                        break;
            }
    }
}
