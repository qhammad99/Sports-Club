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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pk.edu.uiit.arid_2471.checkingemulator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PendingMembersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PendingMembersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PendingMembersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PendingMembersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PendingMembersFragment newInstance(String param1, String param2) {
        PendingMembersFragment fragment = new PendingMembersFragment();
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
    ArrayList<member> memberArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_members, container, false);
        ListView members=view.findViewById(R.id.pendingMember);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login_preference", MODE_PRIVATE);

        String url="http://192.168.0.101/app_project/pendingMembers.php";
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.getCache().clear();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray =jsonObject.getJSONArray("data");
                    for(int i=0; i<jsonArray.length();i++) {
                        JSONObject obj=jsonArray.getJSONObject(i);
                        member newMember=new member(obj.getString("name"), obj.getString("location"), obj.getString("status"), obj.getString("id") );
                        memberArrayList.add(newMember);
                        //Toast.makeText(getActivity(), "teamName: "+jsonObject.getString("teamname"),Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                pendingMembersListAdapter adapter= new pendingMembersListAdapter(getActivity(), R.layout.fragment_pending_members, memberArrayList);
                members.setAdapter(adapter);
                members.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        addMembers(position);
                    }
                });
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
                params.put("team_id", sharedPreferences.getString("teamid",""));
                return params;
            }
        };
        queue.add(stringRequest);
        queue.getCache().clear();

        return view;
    }

    public void addMembers(int index){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login_preference", MODE_PRIVATE);

        String url="http://192.168.0.101/app_project/updateStatus.php";
        Volley.newRequestQueue(getActivity()).getCache().clear();
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    FragmentManager manager=getParentFragmentManager();
                    FragmentTransaction ft=manager.beginTransaction();
                    PendingMembersFragment pending=new PendingMembersFragment();
                    ft.replace(R.id.secondFrame, pending).commit();
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
                params.put("id", memberArrayList.get(index).getId());
                params.put("team_id", sharedPreferences.getString("reqid",""));
                return params;
            }
        };
        queue.add(stringRequest);
        queue.getCache().clear();
    }
}