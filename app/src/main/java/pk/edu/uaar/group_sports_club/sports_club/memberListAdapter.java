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
import java.util.List;

import pk.edu.uiit.arid_2471.checkingemulator.R;

public class memberListAdapter extends ArrayAdapter<member> {
    private ArrayList<member> memberList;

    public memberListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<member> memberList) {
        super(context, resource, memberList);
        this.memberList=memberList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.team_members_list,parent, false);
        }



        TextView nameM=convertView.findViewById(R.id.memberName);
        TextView cityM=convertView.findViewById(R.id.memberCity);
        TextView statusM= convertView.findViewById(R.id.statusOfMember);

        nameM.setText(memberList.get(position).getName());
        cityM.setText(memberList.get(position).getCity());
        statusM.setText(memberList.get(position).getStatus());

        return convertView;
    }
}
