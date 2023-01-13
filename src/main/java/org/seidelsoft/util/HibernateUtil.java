package org.seidelsoft.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class HibernateUtil {

    public static EntityManagerFactory factory = null;

    static {
        init();
    }

    private static void init() {
        try {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory("orm-jpa-hibernate");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static Object getPK(Object entity) {
        return factory.getPersistenceUnitUtil().getIdentifier(entity);
    }
}
