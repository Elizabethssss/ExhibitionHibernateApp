package com.elizabeth.exhibition.util;

import com.elizabeth.exhibition.entity.ExhibitionEntity;
import com.elizabeth.exhibition.entity.ExpositionEntity;
import com.elizabeth.exhibition.entity.RoleEntity;
import com.elizabeth.exhibition.entity.TicketEntity;
import com.elizabeth.exhibition.entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private HibernateUtil() {}

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure()
                    .addAnnotatedClass(UserEntity.class)
                    .addAnnotatedClass(RoleEntity.class)
                    .addAnnotatedClass(ExhibitionEntity.class)
                    .addAnnotatedClass(ExpositionEntity.class)
                    .addAnnotatedClass(TicketEntity.class)
                    .buildSessionFactory();
        } catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
