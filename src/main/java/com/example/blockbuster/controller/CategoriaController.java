package com.example.blockbuster.controller;

import com.example.blockbuster.controller.dto.CategoriaDto;
import com.example.blockbuster.model.Categoria;
import com.example.blockbuster.service.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/categorias/v1/categorias")
public class CategoriaController {
    private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

    private CategoriaService categoriaService;
    //private VideoService videoService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
        //this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Categoria>> getAllCategorias() {
        log.debug("Returned all categorias");
        return ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@Valid String id) {
        log.debug("Returned categoria with id={}",id);
        return categoriaService.findById(id).map(ResponseEntity::ok).orElse(notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody CategoriaDto categoriaDto) {
        Optional<Categoria> categoria = categoriaService.createCategoria(categoriaDto);
        if(categoria.isPresent()){
            log.debug("Created categoria with id={}",categoriaDto.getId());
            return  status(HttpStatus.OK).body(categoria.get());
        }
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> putCategoria(@RequestBody @Valid CategoriaDto categoriaDto,
                                                  String id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        if (categoria.isPresent()) {
            log.debug("Updated categoria with id={}", id);
            categoriaService.updateCategoria(id, categoriaDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.debug("Not found Categoria with id={}",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(String id) {
        log.debug("Deleted categoria with id={}", id);
        categoriaService.deleteCategoriaById(id);
        return accepted().build();
    }

    //TODO getMapping("/{id}/videos")
}
