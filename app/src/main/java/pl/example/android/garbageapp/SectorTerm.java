package pl.example.android.garbageapp;

public class SectorTerm {
    private String term;

    public SectorTerm(String term, String termType) {
        this.term = term;
        this.termType = termType;
    }

    private String termType;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }
}
