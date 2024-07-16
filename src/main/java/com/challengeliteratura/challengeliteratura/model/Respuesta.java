package com.challengeliteratura.challengeliteratura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Respuesta {

    @JsonAlias("results")
    private List<LibroDatos> results;

    public List<LibroDatos> getResults() {
        return results;
    }

    public void setResults(List<LibroDatos> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "results=" + results +
                '}';
    }
}
