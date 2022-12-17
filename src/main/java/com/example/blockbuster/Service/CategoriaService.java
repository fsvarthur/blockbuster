package com.example.blockbuster.Service;

import com.example.blockbuster.Controller.dto.CategoriaDto;
import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Repository.CategoriaRepository;
import com.example.blockbuster.Repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);
    private CategoriaRepository categoriaRepository;
    private VideoRepository videoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, VideoRepository videoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.videoRepository = videoRepository;
    }

    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(String id) {
        return categoriaRepository.findById(Long.valueOf(id));
    }

    public Optional<Categoria> createCategoria(CategoriaDto catDto) {
        return Optional.of(categoriaRepository.save(toEntity(catDto)));
    }

    public void deleteCategoriaById(String id) {
        categoriaRepository.deleteById(Long.valueOf(id));
    }

    private Categoria toEntity(CategoriaDto categoriaDto){
        Categoria categoria = new Categoria();
        categoria.setCor(categoriaDto.getCor());
        categoria.setTitulo(categoriaDto.getTitulo());
        return categoria;
    }


}
