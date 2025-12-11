package com.catalogo_service.catalogo_service.config

import com.catalogo_service.catalogo_service.model.Producto
import com.catalogo_service.catalogo_service.repository.ProductoRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataLoader {

    @Bean
    fun initDatabase(repository: ProductoRepository) = CommandLineRunner {
        if (repository.count() == 0L) {
            val lista = listOf(
                Producto(
                    title = "Hamburguesa Clásica",
                    description = "Carne 200g, queso cheddar, lechuga y tomate.",
                    price = "$5.990",
                    imageUrl = "https://d2fggeox6a5y4y.cloudfront.net/Combo1.jpg",
                    category = "Sandwich"
                ),
                Producto(
                    title = "Papas Fritas",
                    description = "Papas crujientes con salsa de la casa.",
                    price = "$3.500",
                    imageUrl = "https://d2fggeox6a5y4y.cloudfront.net/Combo2.jpg",
                    category = "Acompañamiento"
                ),
                Producto(
                    title = "Bebida Lata",
                    description = "Coca Cola, Fanta o Sprite.",
                    price = "$1.500",
                    imageUrl = "https://d2fggeox6a5y4y.cloudfront.net/Combo3.jpg",
                    category = "Bebidas"
                )
            )
            repository.saveAll(lista)
            println("--- PRODUCTOS DE PRUEBA CARGADOS ---")
        }
    }
}