package com.alonso.lubricantes.services.transformations;

import com.alonso.lubricantes.entities.ArticuloDto;
import com.alonso.lubricantes.entities.ArticuloDtoWithImage;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ArticuloDtoWithImageMapper implements Function<ArticuloDto, ArticuloDtoWithImage> {

    @Override
    public ArticuloDtoWithImage apply(ArticuloDto articuloDto) {
        String imageUrl = "https://raw.githubusercontent.com/julianbasilieri/lubricantes-front/main/images/" + articuloDto.getId() + ".jpg";
        return new ArticuloDtoWithImage(articuloDto, imageUrl);
    }
}
