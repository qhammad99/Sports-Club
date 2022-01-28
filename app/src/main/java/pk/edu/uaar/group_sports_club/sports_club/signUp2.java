package pk.edu.uaar.group_sports_club.sports_club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class signUp2 extends AppCompatActivity {

    Button signup;
    boolean keeperDone=false;
    boolean bowlingHandDone=false;
    boolean bowlingTypeDone = false;
    boolean battingDone=false;

    String isKeeper="";
    String battingSkill="";
    String bowlingType="";
    String bowlingHand="";

    databaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up2);

        signup = findViewById(R.id.sbtBtn);
        db= new databaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                String name=intent.getStringExtra("name");
                String mail=intent.getStringExtra("mail");
                String phone=intent.getStringExtra("phone");
                String city=intent.getStringExtra("city");
                String gender=intent.getStringExtra("gender");
                String pass=intent.getStringExtra("pass");

                if(!(keeperDone||battingDone||bowlingHandDone||bowlingTypeDone)) {
                    Toast.makeText(signUp2.this, "All fields must be filled", Toast.LENGTH_LONG).show();
                }
                else {

                    String url="http://192.168.0.106/app_project/user_insert.php";

                    RequestQueue queue= Volley.newRequestQueue(signUp2.this);
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signUp2.this, homeActivity.class);
                                startActivity(intent);
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams(){
                            Map params= new HashMap<String, String>();
                            params.put("name", name);
                            params.put("mail", mail);
                            params.put("phone", phone);
                            params.put("city", city);
                            params.put("gender",gender);
                            params.put("password", pass);
                            params.put("isKeeper", isKeeper);
                            params.put("battingSkill", battingSkill);
                            params.put("bowlingType", bowlingType);
                            params.put("bowlingHand", bowlingHand);
                            return params;
                        }
                    };

                    queue.add(stringRequest);
                    //sqlite code
//                    long check = db.signup(name, mail, phone, city, gender, userSkill, battingSkill, bowlingSkill, pass);
//                    if (check != -1) {
//                        Toast.makeText(signUp2.this, "Inserted ", Toast.LENGTH_LONG).show();
//
//                        Intent intent2=new Intent(signUp2.this, homeActivity.class);
//                        startActivity(intent2);
//
//                    }
//                    else {
//                        Toast.makeText(signUp2.this, "Error in insertion", Toast.LENGTH_LONG).show();
//                    }
                }
            }
        });
    }

    public void keeperChecked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        keeperDone=true;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.yesKeeper:
                if (checked)
                    isKeeper= "true";
                RadioButton noBowler = findViewById(R.id.noneBowler);

                RadioButton spin=findViewById(R.id.SpinBowler);
                RadioButton fast=findViewById(R.id.FastBowler);
                RadioButton left=findViewById(R.id.leftHandBowler);
                RadioButton right=findViewById(R.id.rightHandBowler);

                noBowler.setChecked(true);

                spin.setEnabled(false);
                fast.setEnabled(false);
                left.setEnabled(false);
                right.setEnabled(false);
                bowlingHandDone=true;
                bowlingTypeDone=true;
                bowlingType="none";
                bowlingHand="none";
                break;
            case R.id.noKeeper:
                if (checked)
                    isKeeper= "false";
                break;
        }
    }

    public void batsmanChecked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        battingDone=true;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rightBat:
                if (checked)
                    battingSkill="Right Hand Batsman";
                break;
            case R.id.leftBat:
                if (checked)
                    battingSkill="Left Hand Batsman";
                break;
        }

    }

    public void bowlerChecked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        bowlingTypeDone=true;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.FastBowler:
                if (checked)
                    bowlingType="Fast Bowler";
                break;
            case R.id.SpinBowler:
                if (checked)
                    bowlingType="Spin Bowler";
                    break;
            case R.id.noneBowler:
                if (checked)
                    bowlingType="false";
                    break;
        }
    }

    public void bowlerHand(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        bowlingHandDone=true;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.leftHandBowler:
                if (checked)
                    bowlingHand="Left Hand Bowler";
                break;
            case R.id.rightHandBowler:
                if (checked)
                    bowlingHand="Right Hand Bowler";
                break;
        }
    }
}