package com.example.blockbuster.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_livre")
    @SequenceGenerator(name = "categoria_livre", initialValue = 2)
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
