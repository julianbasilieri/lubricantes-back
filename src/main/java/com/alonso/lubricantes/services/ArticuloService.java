package com.alonso.lubricantes.services;

import com.alonso.lubricantes.entities.ArticuloDto;

import java.util.List;

public interface ArticuloService extends Service<ArticuloDto, String> {
    void add_list(List<ArticuloDto> articulos);
}
