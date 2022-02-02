package pk.edu.uaar.group_sports_club.sports_club;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class tournamentListAdapter extends ArrayAdapter<tournament> {
    private ArrayList<tournament> memberList;
    private Context myContext;

    public tournamentListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<tournament> teamList) {
        super(context, resource, teamList);
        this.memberList=teamList;
        this.myContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.tournament_list,parent, false);
        }


        TextView nameM=convertView.findViewById(R.id.tournamentName);
        TextView cityM=convertView.findViewById(R.id.last_date);

        nameM.setText(memberList.get(position).getName());
        cityM.setText(memberList.get(position).getLast_date());
        return  convertView;
    }
}
