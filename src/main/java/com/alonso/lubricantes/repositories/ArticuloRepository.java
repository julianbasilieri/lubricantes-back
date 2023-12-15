package com.alonso.lubricantes.repositories;

import com.alonso.lubricantes.entities.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, String> {
}
