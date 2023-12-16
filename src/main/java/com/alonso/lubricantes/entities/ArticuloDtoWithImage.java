package com.alonso.lubricantes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloDtoWithImage {
    private ArticuloDto articuloDto;
    private String imageUrl;
}
