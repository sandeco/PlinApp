package plin.net.br.plin.dao.dao;

import plin.net.br.plin.model.Post;

/**
 * Created by sandeco on 15/05/16.
 */
public interface PostDao {

    public int getIdLastPost();
    public void setLastPost(Post post) throws Exception;

}
