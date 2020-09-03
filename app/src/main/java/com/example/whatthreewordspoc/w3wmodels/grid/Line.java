
package com.example.whatthreewordspoc.w3wmodels.grid;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Line {

    @Expose
    private End end;
    @Expose
    private Start start;

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

}
