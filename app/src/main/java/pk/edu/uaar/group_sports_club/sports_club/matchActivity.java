package pk.edu.uaar.group_sports_club.sports_club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class matchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
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
                Intent intent=new Intent(matchActivity.this, userProfile.class);
                startActivity(intent);
                break;
            case R.id.myTeam:
                Intent intent2=new Intent(matchActivity.this, teams.class);
                startActivity(intent2);
                break;
            case R.id.logOut:
                Intent intent3=new Intent(matchActivity.this, loginActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }
}