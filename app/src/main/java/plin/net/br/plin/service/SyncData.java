package plin.net.br.plin.service;

/**
 * Created by sandeco on 14/05/16.
 */

import com.android.volley.RequestQueue;

import android.content.Context;
import android.util.Log;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

import plin.net.br.plin.R;
import plin.net.br.plin.model.Post;
import plin.net.br.plin.util.App;

public class SyncData {

    private static final String TAG = SyncData.class.getSimpleName();
    private static final String URL = App.getContext().getString(R.string.ulr_rest_last_post);

    private RequestQueue queue;
    private ObjectMapper objectMapper;


    public SyncData(Context c) {
        this.queue = Volley.newRequestQueue(c);
        this.queue.stop(); // deixando a fila parada

        this.objectMapper = new ObjectMapper();
    }

    public void start() {
        JsonArrayRequest request = new JsonArrayRequest(
                URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            List<Post> posts = objectMapper.readValue(response.toString(), new TypeReference<List<Post>>() {});
                            Log.d(TAG, posts.toString());

                            for (Post p : posts) {
                                Log.d(TAG, String.valueOf(p.getId()));
                                Log.d(TAG, p.getDate().toString());
                                Log.d(TAG, p.getLink());
                                Log.d(TAG, p.getSlug());
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        };
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage());
                    }
                }
        );

        this.queue.add(request);
        this.queue.start();
    }

    public void stop() {
        this.queue.stop();
    }
}
