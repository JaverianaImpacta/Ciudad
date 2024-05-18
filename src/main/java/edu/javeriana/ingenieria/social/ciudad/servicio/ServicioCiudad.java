package edu.javeriana.ingenieria.social.ciudad.servicio;

import edu.javeriana.ingenieria.social.ciudad.dominio.Ciudad;
import edu.javeriana.ingenieria.social.ciudad.repositorio.RepositorioCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCiudad {
    @Autowired
    private RepositorioCiudad repositorio;

    public List<Ciudad> obtenerCiudades(){
        return repositorio.findAll();
    }

    public Ciudad obtenerCiudad(String nombre){
        return repositorio.findOneByNombre(nombre);
    }

    public Ciudad obtenerCiudad(Integer codigo){
        return repositorio.findOneByCodigo(codigo);
    }

    public Ciudad crearCiudad(Ciudad ciudad){
        return repositorio.save(ciudad);
    }

    public Ciudad actualizarCiudad(Integer id, Ciudad ciudad){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        ciudad.setId(id);
        return repositorio.save(ciudad);
    }

    public Ciudad borrarCiudad(Integer id){
        Ciudad aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return aux;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean ciudadExistePorCodigo(Integer codigo){
        return repositorio.existsByCodigo(codigo);
    }

    public boolean ciudadExistePorNombre(String nombre){
        return repositorio.existsByNombre(nombre);
    }
}
