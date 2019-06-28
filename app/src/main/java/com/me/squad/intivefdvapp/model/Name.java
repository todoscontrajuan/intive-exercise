package com.me.squad.intivefdvapp.model;

import java.io.Serializable;

public class Name implements Serializable {
    private String first;
    private String last;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
