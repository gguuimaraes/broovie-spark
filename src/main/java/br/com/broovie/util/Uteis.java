package br.com.broovie.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Uteis {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("unit_app");
    private static EntityManager entityManager = null;

    public static EntityManager getEntityManager() {
        if (entityManager == null)
            entityManager = factory.createEntityManager();
        return entityManager;
    }
}
