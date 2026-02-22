package com.biblioteca.cqrs.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Libro {
    private final String id;
    private final String titulo;
    private boolean prestado;
    private String usuarioId;

    public Libro(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.prestado = false;
        this.usuarioId = null;
    }

    public void prestar(String usuarioId) {
        if (!estaDisponible()) {
            log.warn("[DOMAIN] El libro ya está prestado: {}", id);
            throw new IllegalStateException("No se puede prestar un libro que ya está prestado.");
        }
        this.prestado = true;
        this.usuarioId = usuarioId;
        log.info("[DOMAIN] Libro marcado como prestado: {} por usuario {}", id, usuarioId);
    }

    public void devolver() {
        if (estaDisponible()) {
            log.warn("[DOMAIN] El libro no está prestado: {}", id);
            throw new IllegalStateException("El libro no está prestado.");
        }
        this.prestado = false;
        this.usuarioId = null;
        log.info("[DOMAIN] Libro marcado como devuelto: {}", id);
    }

    public boolean estaDisponible() {
        return !prestado;
    }
}
