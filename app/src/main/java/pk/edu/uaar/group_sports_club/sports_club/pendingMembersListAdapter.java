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

public class pendingMembersListAdapter extends ArrayAdapter<member> {
    private ArrayList<member> memberList;
    private Context myContext;

    public pendingMembersListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<member> teamList) {
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
        cityM.setText(memberList.get(position).getCity());
        return  convertView;
    }
}
