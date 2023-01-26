package me.lucasfrederico.projetovotacao.repository;

import me.lucasfrederico.projetovotacao.domain.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {

    boolean existsByTituloIgnoreCase(@Param("titulo") String titulo);
}
