package com.challengeliteratura.challengeliteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDatos(
    @JsonAlias("title") String titulo,
    @JsonAlias("languaje") String lenguaje,
    @JsonAlias("downloads") Integer descargas){
}
