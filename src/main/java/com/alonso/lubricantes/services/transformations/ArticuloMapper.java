package com.alonso.lubricantes.services.transformations;

import com.alonso.lubricantes.entities.Articulo;
import com.alonso.lubricantes.entities.ArticuloDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ArticuloMapper implements Function<ArticuloDto, Articulo> {
    @Override
    public Articulo apply(ArticuloDto articuloDto) {
        return new Articulo(articuloDto.getId(),
                articuloDto.getDenominacion(),
                articuloDto.getDenominacionExtendida(),
                articuloDto.getStock(),
                articuloDto.getPrecio(),
                articuloDto.getFechaModificacion());
    }
}
