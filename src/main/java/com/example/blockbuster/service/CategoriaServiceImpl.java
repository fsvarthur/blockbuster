package com.example.blockbuster.service;

import com.example.blockbuster.controller.dto.CategoriaDto;
import com.example.blockbuster.exception.NotFoundException;
import com.example.blockbuster.model.Categoria;
import com.example.blockbuster.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private CategoriaRepository categoriaRepository;
    //private VideoRepository videoRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        //this.videoRepository = videoRepository;
    }

    public Iterable<Categoria> findAll() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(String id) {
        return Optional.ofNullable(categoriaRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException(
                "Categoria by id" + id + " was not found"
        )));
    }

    public Optional<Categoria> createCategoria(CategoriaDto catDto) {
        return Optional.of(categoriaRepository.save(toEntity(catDto)));
    }

    public Optional<Categoria> updateCategoria(String id, CategoriaDto categoriaDto) {
        findOrThrow(Long.valueOf(id));
        categoriaRepository.save(toEntity(categoriaDto));
        return Optional.of(categoriaRepository.save(toEntity(categoriaDto)));
    }

    public void deleteCategoriaById(String id) {
        categoriaRepository.deleteById(Long.valueOf(id));
    }

    private Categoria toEntity(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDto.getId());
        categoria.setCor(categoriaDto.getCor());
        categoria.setTitulo(categoriaDto.getTitulo());
        return categoria;
    }

    private Categoria findOrThrow(final Long id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Categoria com id " + id + " não encontrada.")
        );
    }


}
