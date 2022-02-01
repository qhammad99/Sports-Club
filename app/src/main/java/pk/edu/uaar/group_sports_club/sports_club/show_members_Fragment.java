package pk.edu.uaar.group_sports_club.sports_club;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Use the {@link show_members_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class show_members_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public show_members_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment show_members_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static show_members_Fragment newInstance(String param1, String param2) {
        show_members_Fragment fragment = new show_members_Fragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_members_, container, false);
        ListView members=view.findViewById(R.id.membersList);
        ArrayList<member> memberArrayList = new ArrayList<>();
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login_preference", MODE_PRIVATE);

        String url="http://192.168.0.101/app_project/membersDetail.php";
        RequestQueue queue= Volley.newRequestQueue(getActivity());
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

                memberListAdapter adapter= new memberListAdapter(getActivity(), R.layout.fragment_show_members_, memberArrayList);
                members.setAdapter(adapter);
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
                params.put("id", sharedPreferences.getString("teamid",""));
                return params;
            }
        };
        queue.add(stringRequest);
        return view;
    }
}