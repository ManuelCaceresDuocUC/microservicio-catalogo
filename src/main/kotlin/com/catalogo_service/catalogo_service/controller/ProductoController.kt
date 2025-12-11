package com.catalogo_service.catalogo_service.controller

import com.catalogo_service.catalogo_service.model.Producto
import com.catalogo_service.catalogo_service.repository.ProductoRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/productos")
class ProductoController(private val repository: ProductoRepository) {

    @GetMapping
    fun obtenerProductos(
        @RequestParam(required = false) categoria: String?,
        @RequestParam(required = false) busqueda: String?
    ): List<Producto> {
        
        // Lógica de filtrado inteligente
        return if (!categoria.isNullOrBlank() && !busqueda.isNullOrBlank()) {
            // Caso 1: Tiene categoría Y búsqueda (Ej: Buscar "Papas" en categoría "Acompañamiento")
            repository.searchByCategoryAndText(categoria, busqueda)
            
        } else if (!categoria.isNullOrBlank()) {
            // Caso 2: Solo filtro por categoría (Ej: Clic en botón "Bebidas")
            repository.findByCategoryIgnoreCase(categoria)
            
        } else if (!busqueda.isNullOrBlank()) {
            // Caso 3: Solo búsqueda general (Ej: Escribió "Queso" en la barra)
            repository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(busqueda, busqueda)
            
        } else {
            // Caso 4: Traer todo (Carga inicial)
            repository.findAll()
        }
    }
    
    // Endpoint extra para crear productos (útil para cumplir requisito de "Gestión")
    @PostMapping
    fun crearProducto(@RequestBody producto: Producto): Producto {
        return repository.save(producto)
    }
}