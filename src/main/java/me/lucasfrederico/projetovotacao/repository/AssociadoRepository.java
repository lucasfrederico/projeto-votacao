package me.lucasfrederico.projetovotacao.repository;

import me.lucasfrederico.projetovotacao.domain.Associado;
import me.lucasfrederico.projetovotacao.dto.AssociadoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    @Query("select new me.lucasfrederico.projetovotacao.dto.AssociadoDTO(a.id, a.nome) from Associado a where (?1 is null or upper(a.nome) like upper(concat('%', ?1, '%'))) order by a.nome")
    Page<AssociadoDTO> findByNomeContainsIgnoreCaseOrderByNomeAsc(@Nullable String nome, Pageable pageable);

    boolean existsByNomeIgnoreCase(String nome);

}
