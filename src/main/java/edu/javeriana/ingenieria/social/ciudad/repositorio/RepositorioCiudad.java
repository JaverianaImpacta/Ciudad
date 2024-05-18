package edu.javeriana.ingenieria.social.ciudad.repositorio;

import edu.javeriana.ingenieria.social.ciudad.dominio.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCiudad extends JpaRepository<Ciudad, Integer> {
    Ciudad findOneByNombre(String nombre);

    Ciudad findOneByCodigo(Integer codigo);

    boolean existsByCodigo(Integer codigo);

    boolean existsByNombre(String nombre);
}
