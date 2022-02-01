package pk.edu.uaar.group_sports_club.sports_club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class homeActivity extends AppCompatActivity {
    FragmentManager manager=getSupportFragmentManager();
    BottomNavigationView navigation;

    TournamentFragment tournament=new TournamentFragment();
    TeamFragment team = new TeamFragment();
    ProfileFragment profile= new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigation=findViewById(R.id.bottomNavigation);

        //click listener
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case(R.id.matches):
                        FragmentTransaction ft= manager.beginTransaction();
                        ft.replace(R.id.fragment, tournament).commit();
                        break;
                    case(R.id.myTeam):
                        FragmentTransaction ft1= manager.beginTransaction();
                        ft1.replace(R.id.fragment, team).commit();
                        break;
                    case(R.id.profile):
                        FragmentTransaction ft3= manager.beginTransaction();
                        ft3.replace(R.id.fragment, profile).commit();
                        break;
                }
                return true;
            }
        });

        //setting home as first fragment to display
        navigation.setSelectedItemId(R.id.matches);
    }
   //option menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.option_menu,menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        //return super.onOptionsItemSelected(item);
//        switch (item.getItemId()){
//            case R.id.myProfile:
//                Intent intent=new Intent(homeActivity.this, userProfile.class);
//                startActivity(intent);
//                break;
//            case R.id.myTeam:
//                Intent intent2=new Intent(homeActivity.this, teams.class);
//                startActivity(intent2);
//                break;
//            case R.id.logOut:
//                Intent intent3=new Intent(homeActivity.this, loginActivity.class);
//                startActivity(intent3);
//                finish();
//                break;
//        }
//        return true;
//    }
}