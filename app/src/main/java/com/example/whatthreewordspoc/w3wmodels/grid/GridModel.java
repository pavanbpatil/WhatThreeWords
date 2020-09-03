
package com.example.whatthreewordspoc.w3wmodels.grid;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class GridModel {

    @Expose
    private List<Line> lines;

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

}
