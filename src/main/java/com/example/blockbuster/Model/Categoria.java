package com.example.blockbuster.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String cor;

    public Categoria() {
    }

    public Categoria(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }
}
