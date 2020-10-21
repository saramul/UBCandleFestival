package cs.ubru.ubcandlefestival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TempleAdapter.OnItemClickListener{
    private ArrayList<Temple> temples;
    private TempleAdapter templeAdapter;
    private RequestQueue requestQueue;
    private RecyclerView recyclerViewTemple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTemple = findViewById(R.id.recyclerview_temple);
        recyclerViewTemple.setHasFixedSize(true);
        recyclerViewTemple.setLayoutManager(new LinearLayoutManager(this));

        temples = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON() {
        String url = "http://suriyon.cs.ubru.ac.th/uboncandlefest/api/temple_api.php";

        JsonArrayRequest request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        for(int i=0; i<response.length(); i++) {
                            try {
                                jsonObject = (JSONObject) response.get(i);
                                int id = jsonObject.getInt("id");
                                String templeName = jsonObject.getString("temple_name");
                                String city = jsonObject.getString("city");

                                temples.add(new Temple(id, templeName, city));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            templeAdapter = new TempleAdapter(MainActivity.this, temples);
                            recyclerViewTemple.setAdapter(templeAdapter);
                            templeAdapter.setOnItemClickListener(MainActivity.this);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        intent.putExtra("id", temples.get(position).getId());

        startActivity(intent);
    }
}
