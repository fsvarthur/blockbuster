package com.example.blockbuster.Controller;

import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Controller.form.AtualizarForm;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Model.Video;
import com.example.blockbuster.Repository.CategoriaRepository;
import com.example.blockbuster.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideoRepository videoRepository;

    public CategoriaController(CategoriaRepository categoriaRepository, VideoRepository videoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.videoRepository = videoRepository;
    }

    @GetMapping
    public List<CategoriaDto> getAllCategoria(String search){
        if(search == null){
            List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
            return CategoriaDto.converter(categorias);
        }else{
            List<Categoria> categorias = categoriaRepository.findByTitulo(search);
            return CategoriaDto.converter(categorias);
        }
    }

    @GetMapping("/{id}/videos")
    public List<Video> getCategoriaVideo(@PathVariable Long id){
        return (List<Video>) this.categoriaRepository.findById(id).map(this.videoRepository::findByCategoria).get();
    }

    @GetMapping("/{id}")
     public Optional<Categoria> getCategoriaById(@PathVariable Long id){
        return categoriaRepository.findById(id);
    }

    @PostMapping
    public Categoria setCategoria(@RequestBody @Valid Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria updateCategoria(@RequestBody AtualizarForm form, @PathVariable(value = "id") Long id) {
        Categoria categoria = form.atualizar(id, categoriaRepository);
        return categoriaRepository.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id){
       categoriaRepository.deleteById(id);
    }
}
