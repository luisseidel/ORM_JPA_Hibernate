package org.seidelsoft.DAO;

import org.seidelsoft.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BaseDAO<T> {

    private EntityManager entityManager = HibernateUtil.getEntityManager();

    public void save(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    public T update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        T saved = entityManager.merge(entity);
        transaction.commit();

        return saved;
    }

    public T search(T entity) {
        Object id  = HibernateUtil.getPK(entity);
        T e = (T) entityManager.find(entity.getClass(), id);

        return e;
    }

    public T search(Long id, Class<T> entity) {
        return (T) entityManager.find(entity, id);
    }

}
