package com.example.blockbuster.Controller;

import com.example.blockbuster.Controller.dto.CategoriaReqDto;
import com.example.blockbuster.Controller.dto.CategoriaResDto;
import com.example.blockbuster.Controller.dto.VideoResDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.CategoriaRepository;
import com.example.blockbuster.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@Validated
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideoRepository videosRepository;

    @GetMapping
    public List<CategoriaResDto> getAllCategorias(){
        return CategoriaResDto.listar(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResDto> getCategoriaById(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return new ResponseEntity<>(new CategoriaResDto(categoria.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/videos")
    public List<VideoResDto> getCategoriaIdVideos(@PathVariable Long id){
        List<Video> video = videosRepository.findByCategoriaId(id);
        return VideoResDto.listar(video);
    }

    @PostMapping
    public ResponseEntity<CategoriaReqDto> postCategoria(@Valid @RequestBody CategoriaReqDto postCategoria,
                                                         UriComponentsBuilder builder){
        Categoria categoria = postCategoria.toEntity();
        categoriaRepository.save(categoria);

        URI uri = builder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaReqDto(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaReqDto> putCategoria(@RequestBody @Valid CategoriaReqDto putCategoria,
                                                        @PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            Categoria cat = categoria.get();
            cat.setCor(putCategoria.getCor());
            cat.setTitulo(putCategoria.getTitulo());
            categoriaRepository.save(cat);
            return new ResponseEntity<>(new CategoriaReqDto(cat), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            categoriaRepository.deleteById(id);
           return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
