package com.javiera.modelos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Usuario {
    private Long id;
    
    @NotBlank(message="Por favor proporciona tu nombre.")
    @Size(min=3, message="El nombre debe tener al menos 3 caracteres.")
    private String nombre;
    
    @NotBlank(message="Por favor proporciona tu apellido.")
    private String apellido;
    
    @NotBlank(message="Por favor ingresa un correo válido.")
    @Email(message="Por favor ingresa un correo válido.")
    private String correo;
    
    @NotBlank(message="La contraseña debe tener al menos 8 caracteres.")
    @Size(min=8, message="La contraseña debe tener al menos 8 caracteres.")
    private String password;
    
    private String confirmacion;

    public Usuario() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getConfirmacion() { return confirmacion; }
    public void setConfirmacion(String confirmacion) { this.confirmacion = confirmacion; }
}