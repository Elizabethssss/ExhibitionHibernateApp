package com.elizabeth.exhibition.dao.impl;

import com.elizabeth.exhibition.dao.GenericDao;
import com.elizabeth.exhibition.dao.Page;
import com.elizabeth.exhibition.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements GenericDao<T> {

    @Override
    public void save(T object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<T> findById(long id, String str) {
        return findByParam(id, str);
    }

    @Override
    public void update(T object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id, String str) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<T> query = session.createQuery(str);
        query.setParameter(1, id);
        T object = query.uniqueResult();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }

    protected <P> Optional<T> findByParam(P param, String str) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<T> query= session.createQuery(str);
        query.setParameter(1, param);
        Optional<T> object = query.uniqueResultOptional();
        session.getTransaction().commit();
        session.close();
        return object;
    }

    protected <P, S> List<T> findByParams(P param1, S param2, String str) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<T> query= session.createQuery(str);
        query.setParameter(1, param1);
        query.setParameter(2, param2);
        List<T> list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    protected <P> List<T> findAll(P param, Page page, String str) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<T> query= session.createQuery(str);
        query.setParameter(1, param);
        query.setFirstResult(page.getPageNumber());
        query.setMaxResults(page.getRecordNumber());
        List<T> list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Long count(Long id, String str) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<?> query= session.createQuery(str);
        query.setParameter(1, id);
        Long count = (Long) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }

}
