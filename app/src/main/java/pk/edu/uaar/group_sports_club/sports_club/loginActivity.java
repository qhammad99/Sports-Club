package pk.edu.uaar.group_sports_club.sports_club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class loginActivity extends AppCompatActivity {
    Button submit;
    TextView signupPage;
    EditText mail, pass;
    databaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        signupPage=findViewById(R.id.signBtn);
        submit=findViewById(R.id.sbtBtn);
        mail=findViewById(R.id.etMail);
        pass=findViewById(R.id.etPwd);
        helper=new databaseHelper(this);

        signupPage.setOnClickListener((View v)->{
            Intent intent=new Intent(loginActivity.this, signUpActivity.class);
            startActivity(intent);
            finish();
        });

        submit.setOnClickListener((View v)->{
            String email=mail.getText().toString();
            String Pwd= pass.getText().toString();

            String url="http://192.168.0.106/app_project/users.php";

            RequestQueue queue= Volley.newRequestQueue(loginActivity.this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        if(!jsonObject.getBoolean("error")) {
                            Intent intent = new Intent(loginActivity.this, homeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(loginActivity.this, "Verified successfully", Toast.LENGTH_SHORT).show();
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
                    params.put("mail", email);
                    params.put("password", Pwd);
                    return params;
                }
            };

            queue.add(stringRequest);

            //sqlite code
//            Cursor check=helper.login(email, Pwd);
//            if(check.getCount()==0)
//                Toast.makeText(loginActivity.this,"Record not found", Toast.LENGTH_LONG).show();
//            else {
//                Intent intent = new Intent(loginActivity.this, homeActivity.class);
//                while (check.moveToNext()) {
//                    intent.putExtra("name", check.getString(0));
//                }
//                startActivity(intent);
//            }
        });
    }
}