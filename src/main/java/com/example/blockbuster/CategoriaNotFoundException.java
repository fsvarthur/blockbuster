package com.example.blockbuster;

public class CategoriaNotFoundException extends RuntimeException{

    public CategoriaNotFoundException(Long id){
        super("Categoria não encontrada "+id);
    }
}
