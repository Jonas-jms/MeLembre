package com.zetcode.repository;

import com.zetcode.model.Lembrete;
import com.zetcode.util.Dia_Semana_Mes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class ImplementacaoLembreteRepository
{
    @PersistenceContext
    EntityManager entityManager;

    public List<Lembrete> findAtivoOrdenado()
    {
        try
        {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Lembrete> criteriaQuery = criteriaBuilder.createQuery(Lembrete.class);

            Root<Lembrete> root = criteriaQuery.from(Lembrete.class);

            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("horario")));
            
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(criteriaBuilder.equal(root.<String>get("ativo"), "Ativo"));
            
            criteriaQuery.where(predicates.toArray(new Predicate[]{}));

            TypedQuery<Lembrete> query = entityManager.createQuery(criteriaQuery);

            entityManager.close();

            return query.getResultList();
        }
        catch (NoResultException e)
        {
            entityManager.close();
            
            e.printStackTrace();

            return null;
        }
    }
}
