package plin.net.br.plin.service;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import plin.net.br.plin.R;
import plin.net.br.plin.dao.dao.PostDao;
import plin.net.br.plin.dao.spDao.PostSPDAO;
import plin.net.br.plin.model.Post;
import plin.net.br.plin.util.App;
import plin.net.br.plin.util.InternetCheck;
import plin.net.br.plin.util.Notifier;
import plin.net.br.plin.util.NotifyNewPost;

/**
 * Created by sandeco on 12/05/16.
 */
public class FindNewPostTask implements Runnable, Response.ErrorListener, Response.Listener<JSONArray> {

    // constantes
    private static final String TAG = "LAST-POST";
    private static final String JSONURL = App.getContext().getString(R.string.ulr_rest_last_post);

    // objetos da thread
    private Thread thread;
    private boolean started = false;

    // objetos para o volley
    private RequestQueue queue;
    private ObjectMapper objectMapper;

    // objeto de acesso a dados
    private PostDao postDao;

    //objeto de notificação
    private Notifier notifier;

    //SINGLETON
    private static FindNewPostTask instance;

    private FindNewPostTask() {

        this.queue = Volley.newRequestQueue(App.getContext());
        this.queue.stop(); // deixando a fila parada

        this.objectMapper = new ObjectMapper();
        postDao = new PostSPDAO();

    }


    public static FindNewPostTask getInstance(){
        if(instance==null){
            instance = new FindNewPostTask();
        }

        return instance;

    }


    @Override
    public void run() {
        while(started) {
            try {
                thread.sleep(5 * 1000);
                getPostInternet();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void getPostInternet(){
        if (InternetCheck.isConnected()) {

            JsonArrayRequest request = new JsonArrayRequest(
                    JSONURL, this, this);

            this.queue.add(request);
            this.queue.start();

        }

    }


    /****
     * VOLLEY METHODS
     * @param response
     */

    @Override
    public void onResponse(JSONArray response) {
        Log.d(TAG, response.toString());

        try {
            List<Post> posts = objectMapper.readValue(response.toString(), new TypeReference<List<Post>>() {});
            Log.d(TAG, posts.toString());

            if(posts.size()==1){
                Post post = posts.get(0);
                int idLastpost = postDao.getIdLastPost();

                if(post.getId()>idLastpost) {
                    postDao.setLastPost(post);
                    NotifyNewPost.notify(post);
                }

            }

        } catch (IOException e) {
            Log.d(TAG,e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }




    /**********
     * THREAD METHODS
     ************/

    public void start(){
        started = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        started = false;
    }



}
