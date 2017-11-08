package pl.example.android.garbageapp.data.network.model;

/**
 * Created by miltomasz on 08/11/17.
 */

public class Sector {
    private String id;
    private String term;
    private String type;

    public Sector() {}

    public Sector(String term, String type) {
        this.term = term;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
