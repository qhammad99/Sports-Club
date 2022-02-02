package pk.edu.uaar.group_sports_club.sports_club;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class CreateTournament extends AppCompatActivity {

    EditText name, num;
    TextView date;
    Button save;
    String reg_date;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tournament);

        name=findViewById(R.id.tournamentName);
        num=findViewById(R.id.maxTeams);
        date=findViewById(R.id.lastRegDate);
        save=findViewById(R.id.saveBtn);

        Calendar calendar=Calendar.getInstance();

        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        CreateTournament.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                reg_date= year+"-"+month+"-"+dayOfMonth;
                date.setText(reg_date);
            }
        };


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tournament_name=name.getText().toString();
                String numbers=num.getText().toString();

                if(tournament_name.equals("")||numbers.equals("")||date.equals("")){
                    Toast.makeText(CreateTournament.this, "Must fill all fields", Toast.LENGTH_LONG).show();
                }
                else{
                    String url="http://192.168.0.101/app_project/tournament_insert.php";

                    RequestQueue queue= Volley.newRequestQueue(CreateTournament.this);
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                if(!jsonObject.getBoolean("error")) {
                                    Intent intent = new Intent(CreateTournament.this, MainActivityAdmin.class);
                                    startActivity(intent);
                                    finish();
                                }
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
                            params.put("tournament_name", tournament_name);
                            params.put("number", numbers);
                            params.put("reg_date", reg_date);
                            return params;
                        }
                    };

                    queue.add(stringRequest);
                }
            }
        });
    }
}