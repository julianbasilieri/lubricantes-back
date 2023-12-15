package com.alonso.lubricantes.services;

import com.alonso.lubricantes.entities.Articulo;
import com.alonso.lubricantes.entities.ArticuloDto;
import com.alonso.lubricantes.repositories.ArticuloRepository;
import com.alonso.lubricantes.services.transformations.ArticuloDtoMapper;
import com.alonso.lubricantes.services.transformations.ArticuloMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ArticuloServiceImpl implements ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final ArticuloDtoMapper articuloDtoMapper;
    private final ArticuloMapper articuloMapper;

    public ArticuloServiceImpl(ArticuloRepository articuloRepository, ArticuloDtoMapper articuloDtoMapper, ArticuloMapper articuloMapper) {
        this.articuloRepository = articuloRepository;
        this.articuloDtoMapper = articuloDtoMapper;
        this.articuloMapper = articuloMapper;
    }

    @Override
    public void add(ArticuloDto entity) {
        Articulo articulo = Optional.of(entity).map(articuloMapper).get();
        articuloRepository.save(articulo);
    }

    @Override
    public ArticuloDto delete(String id) {
        Optional<Articulo> optionalArticulo = articuloRepository.findById(id);
        optionalArticulo.ifPresent(articuloRepository::delete);
        return optionalArticulo
                .map(articuloDtoMapper)
                .orElseThrow();
    }

    @Override
    public void updatePrecio(ArticuloDto entity) {
        Optional<Articulo> optionalArticulo = Optional.of(entity).map(articuloMapper).stream().findFirst();
        optionalArticulo.ifPresent(existingArticulo -> {
            // Actualizar solo el precio
            existingArticulo.setPrecio(entity.getPrecio());
            articuloRepository.save(existingArticulo);
        });
    }


    @Override
    public List<ArticuloDto> getAll() {
        List<Articulo> articulos = articuloRepository.findAll();
        return articulos
                .stream()
                .map(articuloDtoMapper)
                .toList();
    }

    @Override
    public ArticuloDto getById(String id) {
        Optional<Articulo> optionalArticulo = articuloRepository.findById(id);
        return optionalArticulo
                .map(articuloDtoMapper)
                .orElseThrow();
    }

    @Override
    public void add_list(List<ArticuloDto> articulos) {
        for (ArticuloDto articulo : articulos) {
            Optional<Articulo> optionalArticulo = articuloRepository.findById(articulo.getId());

            if (optionalArticulo.isPresent()) {
                // Si el artículo existe, verificar si el precio es diferente
                Articulo articuloExistente = optionalArticulo.get();
                if (articuloExistente.getPrecio() != articulo.getPrecio()) {
                    // Actualizar el precio
                    updatePrecio(articulo);
                    System.out.println("Se actualizó el precio del artículo " + articulo.getId());
                }
            } else {
                // Si el artículo no existe, agregarlo a la base de datos
                add(articulo);
                System.out.println("Se agregó el nuevo artículo " + articulo.getId() + " a la base de datos");
            }
        }
    }
}
