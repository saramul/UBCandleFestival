package cs.ubru.ubcandlefestival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class ImageActivity extends AppCompatActivity {
    private RecyclerView recyclerViewImage;
    private ImageAdapter imageAdapter;
    private ArrayList<Image> images;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        recyclerViewImage = findViewById(R.id.recyclerview_image);
        recyclerViewImage.setHasFixedSize(true);
        recyclerViewImage.setLayoutManager(new LinearLayoutManager(this));

        images = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        String id = intent.getIntExtra("id", 0) + "";
        //Toast.makeText(ImageActivity.this, "Id: " + id, Toast.LENGTH_SHORT).show();

        parseJSON(id);
    }

    private void parseJSON(final String id) {
        String url = "http://suriyon.cs.ubru.ac.th/uboncandlefest/api/image_api.php";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("response: " , response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("images");
                            for(int i=0; i<jsonArray.length(); i++) {
                                JSONObject image = jsonArray.getJSONObject(i);

                                String templeName = image.getString("temple_name");
                                String description = image.getString("description");
                                String imgName = image.getString("image_name");

                                images.add(new Image(templeName, description, imgName));
                            }
                            imageAdapter = new ImageAdapter(ImageActivity.this, images);
                            recyclerViewImage.setAdapter(imageAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("id", id);
                return param;
            }
        };
        requestQueue.add(request);
    }
}
