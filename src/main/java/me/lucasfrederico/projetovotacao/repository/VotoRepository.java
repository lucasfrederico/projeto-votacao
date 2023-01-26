package me.lucasfrederico.projetovotacao.repository;

import me.lucasfrederico.projetovotacao.domain.Voto;
import me.lucasfrederico.projetovotacao.domain.enums.VotoOpcaoEnum;
import me.lucasfrederico.projetovotacao.dto.VotoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

    Integer countByPauta_IdAndOpcao(Long pautaId, VotoOpcaoEnum opcao);

    boolean existsByAssociado_IdAndPauta_Id(Long associadoId, Long pautaId);

    @Query("""
            select NEW me.lucasfrederico.projetovotacao.dto.VotoDTO(v.id, v.dataVoto, v.opcao, a.id, a.nome)
            from Voto v
            join Associado a
            join Pauta p
            where p.id = ?1
            order by v.id
            """
    )
    Page<VotoDTO> findAllByPautaId(Long pautaId, Pageable pageable);
}
