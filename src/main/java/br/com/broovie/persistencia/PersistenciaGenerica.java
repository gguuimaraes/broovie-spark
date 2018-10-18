package br.com.broovie.persistencia;

import br.com.broovie.util.Uteis;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class PersistenciaGenerica<T> implements Serializable {
    private Class<T> clazz;
    private EntityManager em;

    protected final void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T findOne(int id) {
        em = Uteis.getEntityManager();
        return em.find(clazz, id);
    }

    public List<T> findAll() {
        em = Uteis.getEntityManager();
        return em.createQuery("FROM " + clazz.getName()).getResultList();
    }

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

    public void remove(T entidade) {
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

    public void remove(int id) {
        remove(findOne(id));
    }
}
