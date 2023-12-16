package com.alonso.lubricantes.services;

import com.alonso.lubricantes.entities.ArticuloDto;
import com.alonso.lubricantes.entities.ArticuloDtoWithImage;

import java.util.List;

public interface ArticuloService extends Service<ArticuloDto, String> {
    void addList(List<ArticuloDto> articulos);

    ArticuloDtoWithImage getByIdWithImage(String id);
    List<ArticuloDtoWithImage> getAllWithImage();
}
