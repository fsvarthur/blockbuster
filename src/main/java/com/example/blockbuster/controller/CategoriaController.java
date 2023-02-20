package com.example.blockbuster.controller;

import com.example.blockbuster.controller.dto.CategoriaDto;
import com.example.blockbuster.exception.NotFoundException;
import com.example.blockbuster.model.Categoria;
import com.example.blockbuster.service.CategoriaService;
import com.example.blockbuster.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
    private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

    private CategoriaService categoriaService;
    private VideoService videoService;

    public CategoriaController(CategoriaService categoriaService, VideoService videoService) {
        this.categoriaService = categoriaService;
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Categoria>> getAllCategorias() {
        log.debug("Returned all categorias");
        return ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable String id) {
        log.debug("Returned categoria with id={}",id);
        //Optional<Categoria> categoria = categoriaService.findById(id);
        //String version = categoria.getVersion(); Need to put versionCategoria
        return categoriaService.findById(id).map(ResponseEntity::ok)
                .orElse(notFound()
                        //.cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
                        //.eTag(version)
                        .build());
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody CategoriaDto categoriaDto) {
        Optional<Categoria> categoria = categoriaService.createCategoria(categoriaDto);
        if(categoria.isPresent()){
            log.debug("Created categoria with id={}",categoriaDto.getId());
            return  status(HttpStatus.OK).body(categoria.get());
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> putCategoria(@PathVariable("id") String id,
                                                  @RequestBody @Valid CategoriaDto categoriaDto) {
        if (Long.valueOf(id).equals(categoriaDto.getId())) {
            log.debug("Updated categoria with id={}", id);
            categoriaService.updateCategoria(id, categoriaDto);
            Optional<Categoria> categoria = categoriaService.findById(id);
            return status(HttpStatus.OK).body(categoria.get());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable String id) throws NotFoundException {
        Optional<Categoria> categoria = categoriaService.findById(id);
        if (categoria.isPresent()){
            log.debug("Deleted categoria with id={}", id);
            categoriaService.deleteCategoriaById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            log.debug("Not found Categoria with id={}",id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //TODO getMapping("/{id}/videos")
}
