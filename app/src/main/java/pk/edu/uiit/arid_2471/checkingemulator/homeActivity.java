package pk.edu.uiit.arid_2471.checkingemulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.zip.Inflater;

public class homeActivity extends AppCompatActivity {

    GridLayout firstMatch, secondMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firstMatch=findViewById(R.id.firstMatch);
        secondMatch=findViewById(R.id.secondMatch);

        firstMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homeActivity.this, matchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        secondMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homeActivity.this, matchActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.myProfile:
                Toast.makeText(homeActivity.this, "Your Profile", Toast.LENGTH_LONG).show();
                break;
            case R.id.myTeam:
                Toast.makeText(homeActivity.this, "Your Team", Toast.LENGTH_LONG).show();
                break;
            case R.id.logOut:
                Intent intent=new Intent(homeActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}