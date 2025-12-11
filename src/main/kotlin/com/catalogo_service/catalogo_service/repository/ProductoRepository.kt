package com.catalogo_service.catalogo_service.repository
import com.catalogo_service.catalogo_service.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductoRepository : JpaRepository<Producto, Long> {

    // 1. Filtrar solo por categoría
    fun findByCategoryIgnoreCase(category: String): List<Producto>

    // 2. Buscar por texto en Titulo O Descripción (Buscador)
    fun findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
        title: String, 
        description: String
    ): List<Producto>

    // 3. (Opcional/Avanzado) Filtrar por Categoría Y Búsqueda a la vez
    @Query("SELECT p FROM Producto p WHERE LOWER(p.category) = LOWER(:cat) AND (LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')))")
    fun searchByCategoryAndText(@Param("cat") cat: String, @Param("query") query: String): List<Producto>
}