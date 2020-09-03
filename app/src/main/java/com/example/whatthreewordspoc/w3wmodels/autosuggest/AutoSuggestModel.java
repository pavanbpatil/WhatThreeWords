
package com.example.whatthreewordspoc.w3wmodels.autosuggest;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class AutoSuggestModel {

    @Expose
    private List<Suggestion> suggestions;

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

}
