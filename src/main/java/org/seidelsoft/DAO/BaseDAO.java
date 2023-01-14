package org.seidelsoft.DAO;

import org.seidelsoft.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

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

    public void delete(Class<T> clazz, Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String query = "delete from " + clazz.getSimpleName().toLowerCase() + " where id = " + id;
        entityManager.createNativeQuery(query).executeUpdate();

        transaction.commit();
    }

    public void delete(T entity) {
        Object id = HibernateUtil.getPK(entity);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String query = "delete from " + entity.getClass().getSimpleName().toLowerCase() + " where id = " + id;
        entityManager.createNativeQuery(query).executeUpdate();

        transaction.commit();
    }

    public T search(T entity) {
        Object id = HibernateUtil.getPK(entity);
        T e = (T) entityManager.find(entity.getClass(), id);

        return e;
    }

    public T search(Long id, Class<T> entity) {
        return (T) entityManager.find(entity, id);
    }

    public List<T> list(Class<T> entity, int maxResults) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<T> lista = entityManager
                .createQuery("from " + entity.getName())
                .setMaxResults(maxResults)
                .getResultList();
        transaction.commit();
        return lista;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
