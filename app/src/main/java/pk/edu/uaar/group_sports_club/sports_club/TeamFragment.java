package pk.edu.uaar.group_sports_club.sports_club;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import pk.edu.uiit.arid_2471.checkingemulator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamFragment newInstance(String param1, String param2) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String status;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        TextView teamName = view.findViewById(R.id.name);
        TextView location = view.findViewById(R.id.location);
        TextView creatorName= view.findViewById(R.id.creatorName);
        TextView members = view.findViewById(R.id.membersCount);
        Button showMember=view.findViewById(R.id.members);
        Button reqMember=view.findViewById(R.id.requests);
        FragmentManager manager=getParentFragmentManager();

        connector(teamName, location, creatorName, members);

        showMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), teamName.getText().toString() ,Toast.LENGTH_SHORT).show();
//                //connector(teamName, location, creatorName, members);

                FragmentTransaction ft= manager.beginTransaction();
                show_members_Fragment members= new show_members_Fragment();
                ft.replace(R.id.secondFrame, members).commit();
            }
        });

        reqMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.equals("creator")){
                    FragmentTransaction ft= manager.beginTransaction();
                    PendingMembersFragment members= new PendingMembersFragment();
                    ft.replace(R.id.secondFrame, members).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Only Creator can see requests", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    public void connector(TextView teamName, TextView location, TextView creatorName, TextView members){
        FragmentManager manager=getParentFragmentManager();
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login_preference", MODE_PRIVATE);

        String url="http://192.168.0.101/app_project/getTeamId.php";
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    if (!jsonObject.getBoolean("error")) {
                        status = jsonObject.getString("status");
                        if (status.equals("pending")) {
                            FragmentTransaction ft= manager.beginTransaction();
                            PendingTeamFragment members= new PendingTeamFragment();
                            ft.replace(R.id.fragment, members).commit();
                        } else {
                            teamName.setText("Team Name: " + jsonObject.getString("teamname"));
                            location.setText("Location: " + jsonObject.getString("location"));
                            creatorName.setText("Creator: " + jsonObject.getString("creator"));
                            members.setText("Members: " + jsonObject.getString("members"));

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("teamid", jsonObject.getString("teamid"));
                            editor.commit();

                            Toast.makeText(getActivity(), "teamName: " + jsonObject.getString("teamname"), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        FragmentTransaction ft= manager.beginTransaction();
                        BlankFragment members= new BlankFragment();
                        ft.replace(R.id.fragment, members).commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
        Volley.newRequestQueue(getActivity()).getCache().clear();
    }
}