package com.example.blockbuster.Controller;

import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Service.CategoriaService;
import com.example.blockbuster.Service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@CrossOrigin(allowedHeaders = "Content-Type")
@RestController
@RequestMapping("/categorias/v1/categorias")
public class CategoriaController {
    private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

    private CategoriaService categoriaService;
    private VideoService videoService;

    public CategoriaController(CategoriaService categoriaService, VideoService videoService) {
        this.categoriaService = categoriaService;
        this.videoService = videoService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Categoria>> getAllCategorias() {
        return ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@Valid String id) {
        return categoriaService.findById(id).map(ResponseEntity::ok).orElse(notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody CategoriaDto categoriaDto) {
        return status(HttpStatus.CREATED).body(categoriaService.createCategoria(categoriaDto).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> putCategoria(@RequestBody @Valid CategoriaDto categoriaDto,
                                                  String id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        if (categoria.isPresent()) {
            categoriaService.createCategoria(categoriaDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(String id) {
        categoriaService.deleteCategoriaById(id);
        return accepted().build();
    }

    /*@GetMapping("/{id}/videos")
    public ResponseEntity<List<Video>> getVideosByCategoriaId(@NotNull @Valid String categoriaId){
        return ok(videoService.getVideosByCustomerId(categoriaId));
    }*/

}
