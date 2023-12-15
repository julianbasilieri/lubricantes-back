package com.alonso.lubricantes.controllers;

import com.alonso.lubricantes.entities.ArticuloDto;
import com.alonso.lubricantes.services.ArticuloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public ResponseEntity<List<ArticuloDto>> getAll() {
        List<ArticuloDto> values = articuloService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDto> getById(@PathVariable("id") String id) {
        ArticuloDto value = articuloService.getById(id);
        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ArticuloDto entity) {
        articuloService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ArticuloDto entity) {
        articuloService.updatePrecio(entity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        articuloService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/list")
    public ResponseEntity<Void> add_list(@RequestBody List<ArticuloDto> entities) {
        articuloService.add_list(entities);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
