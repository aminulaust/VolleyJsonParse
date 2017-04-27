package volleyjsonparse.aminulaust.com.volleyjsonparse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.namelist);
        fetchingData();
    }

    void fetchingData(){
        String myURL = "https://raw.githubusercontent.com/aminulaust/FQA/master/freinedapi.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                final String[] show_name = new String[response.length()];
                final String[] show_phone = new String[response.length()];
                final String[] show_dob = new String[response.length()];
                final String[] show_profession = new String[response.length()];

                for (int i =0; i < response.length(); i++){

                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        show_name[i] = jsonObject.getString("name");
                        show_phone[i] = jsonObject.getString("phone");
                        show_dob[i] = jsonObject.getString("bod");
                        show_profession[i] = jsonObject.getString("profession");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                lv.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.mylistview, R.id.nameshow, show_name));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, ShowDetlais.class);
                        intent.putExtra("MyPhone", show_phone[position]);
                        intent.putExtra("MyDob", show_dob[position]);
                        intent.putExtra("MyProfession", show_profession[position]);
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log", error);
            }
        });
        volleyjsonparse.aminulaust.com.volleyjsonparse.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(getApplicationContext(), "Data Loaded Successfully!", Toast.LENGTH_SHORT).show();

    }


}
