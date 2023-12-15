package com.alonso.lubricantes.services.transformations;

import com.alonso.lubricantes.entities.Articulo;
import com.alonso.lubricantes.entities.ArticuloDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ArticuloDtoMapper implements Function<Articulo, ArticuloDto> {
    @Override
    public ArticuloDto apply(Articulo articulo) {
        return new ArticuloDto(articulo.getId(),
                articulo.getDenominacion(),
                articulo.getDenominacionExtendida(),
                articulo.getStock(),
                articulo.getPrecio(),
                articulo.getFechaModificacion());
    }
}
