package com.example.blockbuster.Controller;

import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Controller.dto.VideoDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Service.CategoriaService;
import com.example.blockbuster.Service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/categorias/v1/categorias")
@Validated
public class CategoriaController {
    private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

    private CategoriaService categoriaService;
    private VideoService videoService;

    public CategoriaController(CategoriaService categoriaService, VideoService videoService) {
        this.categoriaService = categoriaService;
        this.videoService = videoService;
    }

    @GetMapping("/all")
    public Page<CategoriaDto> getAllCategorias(@RequestParam(required = false) String query,
                                                  @PageableDefault(sort = "titulo",direction = Direction.DESC,
                                                  page = 0, size = 10) Pageable paginacao){

        if(query==null){
            Page<Categoria> categorias = categoriaService.findAll(paginacao);
            return CategoriaDto.converter(categorias);
        }else{
            Page<Categoria> categorias = categoriaService.findByTitulo(query, paginacao);
            return CategoriaDto.converter(categorias);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaService.findById(id);
        if(categoria.isPresent()){
            return new ResponseEntity<>(new CategoriaDto(categoria.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/videos")
    public List<VideoDto> getCategoriaIdVideos(@PathVariable Long id){
        List<Video> video = videoService.findByCategoriaId(id);
        return VideoDto.listar(video);
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody CategoriaDto categoriaDto){
        return status(HttpStatus.CREATED).body(categoriaService.createCategoria(categoriaDto).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> putCategoria(@RequestBody @Valid CategoriaDto putCategoria,
                                                        @PathVariable Long id){
        Optional<Categoria> categoria = categoriaService.findById(id);
        if(categoria.isPresent()){
            Categoria cat = categoria.get();
            cat.setCor(putCategoria.getCor());
            cat.setTitulo(putCategoria.getTitulo());
            categoriaService.save(cat);
            return new ResponseEntity<>(new CategoriaDto(cat), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaService.findById(id);
        if(categoria.isPresent()){
            categoriaService.deleteById(id);
           return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
