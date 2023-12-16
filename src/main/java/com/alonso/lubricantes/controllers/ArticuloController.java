package com.alonso.lubricantes.controllers;

import com.alonso.lubricantes.entities.ArticuloDto;
import com.alonso.lubricantes.entities.ArticuloDtoWithImage;
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
    public ResponseEntity<String> add(@RequestBody ArticuloDto entity) {
        articuloService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Artículo agregado exitosamente");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ArticuloDto entity) {
        articuloService.updatePrecio(entity);
        return ResponseEntity.status(HttpStatus.OK).body("Precio del artículo actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        articuloService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Artículo eliminado exitosamente");
    }

    @PostMapping("/list")
    public ResponseEntity<String> addList(@RequestBody List<ArticuloDto> entities) {
        articuloService.addList(entities);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lista de artículos procesada exitosamente");
    }

    @GetMapping("/image")
    public ResponseEntity<List<ArticuloDtoWithImage>> getAllWithImage() {
        List<ArticuloDtoWithImage> values = articuloService.getAllWithImage();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<ArticuloDtoWithImage> getByIdWithImage(@PathVariable("id") String id) {
        ArticuloDtoWithImage value = articuloService.getByIdWithImage(id);
        return ResponseEntity.ok(value);
    }
}
