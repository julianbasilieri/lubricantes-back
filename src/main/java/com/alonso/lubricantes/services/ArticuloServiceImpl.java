package com.alonso.lubricantes.services;

import com.alonso.lubricantes.entities.Articulo;
import com.alonso.lubricantes.entities.ArticuloDto;
import com.alonso.lubricantes.entities.ArticuloDtoWithImage;
import com.alonso.lubricantes.repositories.ArticuloRepository;
import com.alonso.lubricantes.services.transformations.ArticuloDtoMapper;
import com.alonso.lubricantes.services.transformations.ArticuloDtoWithImageMapper;
import com.alonso.lubricantes.services.transformations.ArticuloMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ArticuloServiceImpl implements ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final ArticuloDtoMapper articuloDtoMapper;
    private final ArticuloMapper articuloMapper;
    private final ArticuloDtoWithImageMapper articuloDtoWithImageMapper;

    public ArticuloServiceImpl(ArticuloRepository articuloRepository, ArticuloDtoMapper articuloDtoMapper, ArticuloMapper articuloMapper, ArticuloDtoWithImageMapper articuloDtoWithImageMapper) {
        this.articuloRepository = articuloRepository;
        this.articuloDtoMapper = articuloDtoMapper;
        this.articuloMapper = articuloMapper;
        this.articuloDtoWithImageMapper = articuloDtoWithImageMapper;
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
        Articulo articulo = articuloMapper.apply(entity);
        // Actualizar solo el precio
        articuloRepository.save(articulo);
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
        long startTime = System.currentTimeMillis();

        Optional<Articulo> optionalArticulo = articuloRepository.findById(id);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Finalizado en " + elapsedTime + " milisegundos");

        return optionalArticulo
                .map(articuloDtoMapper)
                .orElseThrow();
    }

    @Override
    public void addList(List<ArticuloDto> articulos) {
        articulos.forEach(this::addOrUpdateArticulo);
        System.out.println("finalizado");
    }

    private void addOrUpdateArticulo(ArticuloDto articulo) {
        List<Articulo> allArticulos = articuloRepository.findAll();

        allArticulos
                .stream()
                .filter(existingArticulo -> existingArticulo.getId().equals(articulo.getId()))
                .findFirst()
                .ifPresentOrElse(
                        existingArticulo -> {
                            if (existingArticulo.getPrecio() != articulo.getPrecio()) {
                                updatePrecio(articulo);
                                System.out.println("Se actualizó el precio del artículo " + articulo.getId());
                            }
                        },
                        () -> {
                            add(articulo);
                            System.out.println("Se agregó el nuevo artículo " + articulo.getId() + " a la base de datos");
                        }
                );
        System.out.println(articulo.getId());
    }

    @Override
    public ArticuloDtoWithImage getByIdWithImage(String id) {
        Optional<Articulo> optionalArticulo = articuloRepository.findById(id);
        return optionalArticulo
                .map(articuloDtoMapper)
                .map(articuloDtoWithImageMapper)
                .orElseThrow();
    }
    @Override
    public List<ArticuloDtoWithImage> getAllWithImage() {
        List<Articulo> articulos = articuloRepository.findAll();
        return articulos
                .stream()
                .map(articuloDtoMapper)
                .map(articuloDtoWithImageMapper)
                .toList();
    }
}
