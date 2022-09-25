package com.example.blockbuster.Controller;

import com.example.blockbuster.CategoriaNotFoundException;
import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@Validated
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> getAllCategorias(){
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id){
        return categoriaRepository.findById(id).orElseThrow(()-> new CategoriaNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<String> postCategoria(@Valid @RequestBody Categoria categoria){
         return ResponseEntity.ok("Categoria é valida");
    }


}
