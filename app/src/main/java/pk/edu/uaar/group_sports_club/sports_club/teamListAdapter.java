package pk.edu.uaar.group_sports_club.sports_club;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class teamListAdapter extends ArrayAdapter<team> {
    private ArrayList<team> memberList;
    private Context myContext;

    public teamListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<team> teamList) {
        super(context, resource, teamList);
        this.memberList=teamList;
        this.myContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.teams_list,parent, false);
        }


        TextView nameM=convertView.findViewById(R.id.teamName);
        TextView cityM=convertView.findViewById(R.id.teamLocation);

        nameM.setText(memberList.get(position).getName());
        cityM.setText(memberList.get(position).getLocation());
        return  convertView;
    }
}
