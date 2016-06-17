package plin.net.br.plin.dao.spDao;

import android.content.Context;
import android.content.SharedPreferences;

import plin.net.br.plin.R;
import plin.net.br.plin.dao.dao.PostDao;
import plin.net.br.plin.model.Post;
import plin.net.br.plin.util.App;

/**
 * Created by sandeco on 15/05/16.
 */
public class PostSPDAO implements PostDao {


    private Context c = App.getContext();
    private SharedPreferences sharedPref;
    private int defaultValue = 0;
    private SharedPreferences.Editor editor;

    public PostSPDAO() {
        sharedPref = c.getSharedPreferences(c.getString(R.string.wp_id_last_post), Context.MODE_PRIVATE);
    }

    @Override
    public int getIdLastPost() {

        int idLastPost = sharedPref.getInt(c.getString(R.string.wp_id_last_post), defaultValue);
        return idLastPost;
    }

    @Override
    public void setLastPost(Post post) throws Exception {
        try{
            editor = sharedPref.edit();
            editor.putInt(c.getString(R.string.wp_id_last_post), post.getId());



            editor.apply();

        }catch (Exception e){
            throw new Exception(c.getString(R.string.erro_gravar_post));
        }
    }


}
