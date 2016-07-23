package com.badoo.testapp.model.graph;

/**
 * Used only for graph. Particularly useful for doing a BFS.
 */
public class Currency {
    private String name;
    private Boolean seen;

    public Currency(String name){
        this.name = name;
        this.seen = false;
    }

    public String getName() {
        return name;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;

        return name.equals(currency.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
