package pk.edu.uiit.arid_2471.checkingemulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class matchActivity extends AppCompatActivity {

    ListView playersInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        String[] infor={
                "player1", "player2", "player3", "player4", "player5", "player6",
                "player7", "player8", "player9", "player10", "player11"};
        playersInfo=findViewById(R.id.playersInfor);
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
                Toast.makeText(matchActivity.this, "Your Profile", Toast.LENGTH_LONG).show();
                break;
            case R.id.myTeam:
                Toast.makeText(matchActivity.this, "Your Team", Toast.LENGTH_LONG).show();
                break;
            case R.id.logOut:
                Intent intent=new Intent(matchActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}