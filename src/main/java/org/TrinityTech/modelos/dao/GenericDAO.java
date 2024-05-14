package org.TrinityTech.modelos.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class GenericDAO {

    SessionFactory sessionFactory;
    Configuration configuration;

    public GenericDAO(Class t){
        configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(t);
        sessionFactory = configuration.buildSessionFactory();
    }

    public Object findById(Class t,int id) {
        Session session = sessionFactory.openSession();
        return session.get(t, id);

    }

    public void save(Object objeto) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(objeto);
        tx1.commit();
        session.close();

    }

    public void update(Object objeto) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(objeto);
        tx1.commit();
        session.close();

    }

    public void delete(Object objeto) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(objeto);
        tx1.commit();
        session.close();

    }

    public <T> List<T> findAll(Class t) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(t);
        criteria.from(t);

        List<T> listaProductos = (List<T>) session.createQuery(criteria).getResultList();

        return listaProductos;

    }
}
