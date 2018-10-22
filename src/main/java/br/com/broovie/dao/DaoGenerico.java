package br.com.broovie.dao;

import br.com.broovie.contrato.OperacoesDao;
import br.com.broovie.util.Uteis;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class DaoGenerico<T> implements OperacoesDao<T>, Serializable {
    private Class<T> clazz;
    protected EntityManager em;

    protected final void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T findOne(Long id) {
        em = Uteis.getEntityManager();
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        em = Uteis.getEntityManager();
        return em.createQuery("FROM " + clazz.getName()).getResultList();
    }

    @Override
    public void create(T entidade) {
        em = Uteis.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(entidade);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T entidade) {
        em = Uteis.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(entidade);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(T entidade) {
        em = Uteis.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(entidade);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
