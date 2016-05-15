package plin.net.br.plin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by sandeco on 15/05/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Title implements Serializable {

    public Title(){
        
    }

    private String rendered;

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

}
