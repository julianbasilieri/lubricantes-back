package com.alonso.lubricantes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloDto {
    private String id;
    private String denominacion;
    private String denominacionExtendida;
    private int stock;
    private double precio;
    private String fechaModificacion;
}
