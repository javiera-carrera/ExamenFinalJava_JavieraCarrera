package com.javiera.controladores;

import com.javiera.modelos.Usuario;
import com.javiera.modelos.Serie;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorSeries {

    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static List<Serie> listaSeries = new ArrayList<>();
    private static Long contadorUsuarios = 1L;
    private static Long contadorSeries = 1L;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/dashboard";
        }
        if (!model.containsAttribute("nuevoUsuario")) {
            model.addAttribute("nuevoUsuario", new Usuario());
        }
        return "index";
    }

    @PostMapping("/registro")
    public String registrar(@RequestParam("nombre") String nombre,
                            @RequestParam("apellido") String apellido,
                            @RequestParam("correo") String correo,
                            @RequestParam("password") String password,
                            @RequestParam("confirmacion") String confirmacion,
                            HttpSession session, Model model) {
        
        // validacion manual basica
        if (nombre.trim().length() < 3 || apellido.trim().isEmpty() || correo.trim().isEmpty()) {
            model.addAttribute("errorRegistro", "El nombre y apellido deben tener al menos 3 caracteres.");
            return "index";
        }
        
        if (password.length() < 8) {
            model.addAttribute("errorRegistro", "La contraseña debe tener al menos 8 caracteres.");
            return "index";
        }
        
        if (!password.equals(confirmacion)) {
            model.addAttribute("errorRegistro", "Las contraseñas no coinciden.");
            return "index";
        }
        
        // verificacion de correo
        for (Usuario u : listaUsuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                model.addAttribute("errorRegistro", "Este correo ya se encuentra registrado.");
                return "index";
            }
        }
        
        //si todo está bien se crea usuario y se encripta
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setId(contadorUsuarios++);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setCorreo(correo);
        
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        nuevoUsuario.setPassword(hashed);
        
        listaUsuarios.add(nuevoUsuario);
        session.setAttribute("usuarioLogueado", nuevoUsuario);
        
        return "redirect:/dashboard";
    }
    @PostMapping("/login")
    public String login(@RequestParam("correo") String correo, 
                        @RequestParam("password") String password, 
                        HttpSession session, Model model) {
        
        Usuario usuarioEncontrado = null;
        for (Usuario u : listaUsuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                usuarioEncontrado = u;
                break;
            }
        }
        
        if (usuarioEncontrado == null || !BCrypt.checkpw(password, usuarioEncontrado.getPassword())) {
            model.addAttribute("errorLogin", "Credenciales inválidas.");
            model.addAttribute("nuevoUsuario", new Usuario());
            return "index";
        }
        
        session.setAttribute("usuarioLogueado", usuarioEncontrado);
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }
        model.addAttribute("series", listaSeries);
        return "dashboard";
    }
    

    // formulario para crear serie
    @GetMapping("/series/nuevo") 
    public String formularioCrear(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }
        return "crearSerie";
    }

    @PostMapping("/series/crear")
    public String crearSerie(@RequestParam("titulo") String titulo,
                             @RequestParam("anio") Integer anio,
                             @RequestParam("descripcion") String descripcion,
                             HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }

        // validaciones
        if (titulo.trim().length() < 5) {
            model.addAttribute("errorSerie", "El título debe tener al menos 5 caracteres.");
            return "crearSerie";
        }
        if (anio == null || anio < 1800) {
            model.addAttribute("errorSerie", "El año debe ser un número válido mayor a 1800.");
            return "crearSerie";
        }
        if (descripcion.trim().length() < 10) {
            model.addAttribute("errorSerie", "La descripción debe tener al menos 10 caracteres.");
            return "crearSerie";
        }

        Usuario actual = (Usuario) session.getAttribute("usuarioLogueado");
        
        Serie nuevaSerie = new Serie();
        nuevaSerie.setId(contadorSeries++);
        nuevaSerie.setTitulo(titulo);
        nuevaSerie.setAnio(anio);
        nuevaSerie.setDescription(descripcion); 
        nuevaSerie.setIdUsuarioCreador(actual.getId());

        listaSeries.add(nuevaSerie);
        return "redirect:/dashboard";
    }

    // ver detalle
    @GetMapping("/series/{id}")
    public String verDetalle(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }
        
        Serie encontrada = null;
        for (Serie s : listaSeries) {
            if (s.getId().equals(id)) {
                encontrada = s;
                break;
            }
        }
        
        model.addAttribute("serie", encontrada);
        return "detalleSerie";
    }

    // formulario para editar
    @GetMapping("/series/{id}/editar")
    public String formularioEditar(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }
        
        Serie encontrada = null;
        for (Serie s : listaSeries) {
            if (s.getId().equals(id)) {
                encontrada = s;
                break;
            }
        }
        
        model.addAttribute("serie", encontrada);
        return "editarSerie";
    }

    // procesar actualizacion de la serie
    @PostMapping("/series/{id}/actualizar")
    public String actualizarSerie(@PathVariable("id") Long id,
                                  @RequestParam("titulo") String titulo,
                                  @RequestParam("anio") Integer anio,
                                  @RequestParam("descripcion") String descripcion,
                                  HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }

        if (titulo.trim().length() < 5 || anio == null || anio < 1800 || descripcion.trim().length() < 10) {
            
            Serie errorSerie = new Serie();
            errorSerie.setId(id);
            errorSerie.setTitulo(titulo);
            errorSerie.setAnio(anio);
            errorSerie.setDescription(descripcion);
            model.addAttribute("serie", errorSerie);
            model.addAttribute("errorSerie", "Validaciones incompletas. Por favor revisa los campos.");
            return "editarSerie";
        }

        for (Serie s : listaSeries) {
            if (s.getId().equals(id)) {
                s.setTitulo(titulo);
                s.setAnio(anio);
                s.setDescription(descripcion);
                break;
            }
        }
        return "redirect:/dashboard";
    }

    // eliminar series
    @PostMapping("/series/{id}/eliminar")
    public String eliminarSerie(@PathVariable("id") Long id, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }
        listaSeries.removeIf(s -> s.getId().equals(id));
        return "redirect:/dashboard";
    }
}