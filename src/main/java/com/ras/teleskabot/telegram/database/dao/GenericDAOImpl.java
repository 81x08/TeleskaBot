package com.ras.teleskabot.telegram.database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;

public class GenericDAOImpl <T, Id extends Serializable> implements GenericDAO<T, Id> {

    private Session currentSession;
    private static final SessionFactory sessionFactory = getSessionFactory();

    private Transaction currentTransaction;

    private final Class<T> type;

    public GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Id create(T entity) {
        return (Id) getCurrentSession().save(entity);
    }

    @Override
    public T read(Id id) {
        return getCurrentSession().get(type, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> readAll() {
        return getCurrentSession().createQuery("from " + type.getSimpleName()).list();
    }

    @Override
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public Session openCurrentSession() {
        currentSession = sessionFactory.openSession();

        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();

        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction transaction) {
        this.currentTransaction = transaction;
    }

    private static SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

}