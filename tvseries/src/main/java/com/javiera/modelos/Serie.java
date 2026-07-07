package com.javiera.modelos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Serie {
    private Long id;
    
    @NotBlank(message="Por favor proporciona el título.")
    @Size(min=5, message="El título debe tener al menos 5 caracteres.")
    private String titulo;
    
    @NotNull(message="Por favor proporciona el año.")
    @Min(value=1800, message="El año debe ser un número válido.")
    private Integer anio;
    
    @NotBlank(message="Por favor proporciona la descripción.")
    @Size(min=10, message="La descripción debe de tener al menos 10 caracteres.")
    private String descripcion;
    
    private Long idUsuarioCreador;

    public Serie() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public String getDescription() { return descripcion; }
    public void setDescription(String descripcion) { this.descripcion = descripcion; }
    public Long getIdUsuarioCreador() { return idUsuarioCreador; }
    public void setIdUsuarioCreador(Long idUsuarioCreador) { this.idUsuarioCreador = idUsuarioCreador; }
}