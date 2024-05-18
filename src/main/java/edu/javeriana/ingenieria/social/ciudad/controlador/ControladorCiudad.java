package edu.javeriana.ingenieria.social.ciudad.controlador;

import edu.javeriana.ingenieria.social.ciudad.dominio.Ciudad;
import edu.javeriana.ingenieria.social.ciudad.servicio.ServicioCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/ciudades")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorCiudad {
    @Autowired
    private ServicioCiudad servicio;

    @GetMapping("listar")
    public List<Ciudad> obtenerCiudades(){
        return servicio.obtenerCiudades();
    }

    @GetMapping("obtenerCodigo")
    public ResponseEntity<Ciudad> obtenerCiudad(@RequestParam Integer codigo){
        if(codigo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!servicio.ciudadExistePorCodigo(codigo)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerCiudad(codigo), HttpStatus.OK);
    }

    @GetMapping("obtener")
    public ResponseEntity<Ciudad> obtenerCiudad(@RequestParam String nombre){
        if(nombre == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!servicio.ciudadExistePorNombre(nombre)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerCiudad(nombre), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Ciudad> obtenerCiudad (@RequestBody Ciudad ciudad){
        if(ciudad == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.ciudadExistePorCodigo(ciudad.getCodigo())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearCiudad(ciudad), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Ciudad> actualizarCiudad(@RequestParam Integer id, @RequestBody Ciudad ciudad){
        if(!Objects.equals(id, ciudad.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarCiudad(id, ciudad) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<>(ciudad, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarCiudad(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarCiudad(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
