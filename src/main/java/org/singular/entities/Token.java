package org.singular.entities;

import java.io.Serializable;

public class Token implements Serializable{
    private long time;

    public Token(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
