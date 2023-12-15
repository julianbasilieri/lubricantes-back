package com.alonso.lubricantes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articulos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulo {
    @Id
    private String id;
    private String denominacion;
    @Column(name = "denominacion_extendida")
    private String denominacionExtendida;
    private int stock;
    private double precio;
    @Column(name = "fecha_modificacion")
    private String fechaModificacion;
}
