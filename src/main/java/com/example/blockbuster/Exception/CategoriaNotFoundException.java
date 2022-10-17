package com.example.blockbuster.Exception;

public class CategoriaNotFoundException extends RuntimeException{

    public CategoriaNotFoundException(Long id){
        super("Categoria não encontrada "+id);
    }
}
