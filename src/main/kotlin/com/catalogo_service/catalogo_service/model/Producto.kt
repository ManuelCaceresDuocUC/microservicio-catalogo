package com.catalogo_service.catalogo_service.model
import jakarta.persistence.*

@Entity
@Table(name = "productos")
data class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,
    
    @Column(length = 1000) // Damos espacio para descripciones largas
    val description: String,
    
    val price: String, // Ejemplo: "$3.500" (Igual que en tu CSV actual)
    
    val imageUrl: String,
    
    val category: String
)