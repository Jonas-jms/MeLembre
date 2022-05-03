package com.zetcode.repository;

import com.zetcode.model.Lembrete;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long>
{
        public List<Lembrete> findByAtivo(String ativo);
        
        @Query(value = "SELECT * FROM Lembrete WHERE ATIVO = 'Ativo' AND HORARIO = :horario", nativeQuery = true)
        List<Lembrete> getProgramacaoAtiva(@Param("horario") LocalTime horario);
        
}