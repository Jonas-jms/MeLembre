package com.zetcode.repository;

import com.zetcode.model.Lembrete;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long>
{
        public List<Lembrete> findByAtivo(String ativo);
}