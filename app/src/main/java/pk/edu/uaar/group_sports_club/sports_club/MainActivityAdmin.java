package pk.edu.uaar.group_sports_club.sports_club;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivityAdmin extends AppCompatActivity {

    Button btn1, creates, shows, remov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        btn1=findViewById(R.id.logoutBtn);
        creates=findViewById(R.id.addTournament);
        shows=findViewById(R.id.viewTournament);
        remov=findViewById(R.id.deleteTournament);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= getSharedPreferences("login_preference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MainActivityAdmin.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        shows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTournaments();
            }
        });

        creates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shiftCreate();
            }
        });

        remov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTournament();
            }
        });
    }
    public void removeTournament(){
        SharedPreferences sharedPreferences=getSharedPreferences("login_preference", MODE_PRIVATE);

        String url="http://192.168.0.101/app_project/tournament_delete.php";
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), "not deleted",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "deleting tournament",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map params = new HashMap<String, String>();
                params.put("id", sharedPreferences.getString("id",""));
                return params;
            }
        };

        queue.add(stringRequest);
        Volley.newRequestQueue(this).getCache().clear();
    }
    public void shiftCreate(){
        SharedPreferences sharedPreferences=getSharedPreferences("login_preference", MODE_PRIVATE);

        String url="http://192.168.0.101/app_project/getTournamentId.php";
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("error")) {
                        Intent intent = new Intent(MainActivityAdmin.this, CreateTournament.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "You already created a tournament",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map params = new HashMap<String, String>();
                params.put("id", sharedPreferences.getString("id",""));
                return params;
            }
        };

        queue.add(stringRequest);
        Volley.newRequestQueue(this).getCache().clear();
    }


    public void checkTournaments(){
        SharedPreferences sharedPreferences=getSharedPreferences("login_preference", MODE_PRIVATE);

        String url="http://192.168.0.101/app_project/getTournamentId.php";
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.getBoolean("error")) {
                        Intent intent = new Intent(MainActivityAdmin.this, TournamentDetail.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No tournaments to display",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map params = new HashMap<String, String>();
                params.put("id", sharedPreferences.getString("id",""));
                return params;
            }
        };

        queue.add(stringRequest);
        Volley.newRequestQueue(this).getCache().clear();
    }

}