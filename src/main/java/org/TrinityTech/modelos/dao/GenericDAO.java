package org.TrinityTech.modelos.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.entidades.Producto;
import org.TrinityTech.modelos.entidades.Proveedor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Clase de DAO para realizar las operaciones CRUD
 * de manera generica utilizando Hibernate
 */
public class GenericDAO {

    SessionFactory sessionFactory;
    Configuration configuration;

    /**
     * Constructor de GenericDAO para genenra las
     * operaciones CRUD
     */
    public GenericDAO(){
        configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Cliente.class);
        configuration.addAnnotatedClass(Producto.class);
        configuration.addAnnotatedClass(Proveedor.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * Método que permite encontrar un elemento por su id
     * @param t clase del elemento que se busca
     * @param id id del elemento que se busca en la base de datos
     * @return Se devuelve el onjetos que se busca
     */
    public Object findById(Class t,int id) {
        Session session = sessionFactory.openSession();
        return session.get(t, id);

    }


    /**
     * Método que guarda un objeto
     * @param objeto Objeto que quiere se quiere guardar
     */
    public void save(Object objeto) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(objeto);
        tx1.commit();
        session.close();

    }


    /**
     * Método para modificar un objeto
     * @param objeto Objeto que se quiere modificar
     */
    public void update(Object objeto) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(objeto);
        tx1.commit();
        session.close();

    }

    /**
     * Método para eliminar un objeto
     * @param objeto Objetos que se quiere eliminar
     */
    public void delete(Object objeto) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(objeto);
        tx1.commit();
        session.close();

    }

    /**
     * Método que devuelve una lista de todos los objetos
     * @param t Clases del objeto a buscar
     * @return Devuelve una lista del objeto
     * @param <T> Elemento genérico
     */
    public <T> List<T> findAll(Class t) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(t);
        criteria.from(t);

        List<T> listaProductos = (List<T>) session.createQuery(criteria).getResultList();

        return listaProductos;

    }
}
