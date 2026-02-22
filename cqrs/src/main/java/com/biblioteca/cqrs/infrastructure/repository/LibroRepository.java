package com.biblioteca.cqrs.infrastructure.repository;

import com.biblioteca.cqrs.domain.Libro;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LibroRepository {
    private final Map<String, Libro> almacenamiento = new HashMap<>();

    public void guardar(Libro libro) {
        almacenamiento.put(libro.getId(), libro);
    }

    public Libro buscarPorId(String id) {
        Libro libro = almacenamiento.get(id);
        if (libro == null) {
            throw new IllegalStateException("No existe un libro con id: " + id);
        }
        return libro;
    }
}
