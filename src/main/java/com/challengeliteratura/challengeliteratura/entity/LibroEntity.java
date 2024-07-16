package com.challengeliteratura.challengeliteratura.entity;

import com.challengeliteratura.challengeliteratura.model.AutorDatos;
import com.challengeliteratura.challengeliteratura.model.LibroDatos;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "libro") // Nombre de la tabla en la base de datos
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String lenguaje;
    private Integer descargas;
    @ManyToOne
    private AutorEntity autor;


    // Constructor (necesario para JPA)
    public LibroEntity(LibroDatos libroDatos) {
        this.titulo = libroDatos.titulo();
        this.lenguaje = libroDatos.lenguaje();
        this.descargas = libroDatos.descargas();
    }

    // Método toString para imprimir información
    @Override
    public String toString() {
        return "LibroEntity{" +
                "id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", lenguaje='" + lenguaje + '\'' +
                ", descargas=" + descargas +
                ", autor=" + autor +
                '}';
    }

    // Getters y Setters (necesarios para JPA)
    public Long getId() {return Id;}

    public void setId(Long Id) {this.Id = Id;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getLenguaje() {return lenguaje;}

    public void setLenguaje(String lenguaje) {this.lenguaje = lenguaje;}

    public Integer getDescargas() {return descargas;}

    public void setDescargas(Integer descargas) {this.descargas = descargas;}

    public AutorEntity getAutor() {return autor;}

    public void setAutor(AutorEntity autor) { this.autor = autor;}

}
