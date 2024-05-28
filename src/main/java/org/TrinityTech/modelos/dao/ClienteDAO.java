package org.TrinityTech.modelos.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.entidades.Producto;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO de especifico de clientes
 */
public class ClienteDAO extends GenericDAO {

    public ClienteDAO(){
        super();
    }


    public List<Cliente> findByNombre(String nombre) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);
        criteria.select(root).where(builder.equal(root.get("nombre"), nombre));

        List<Cliente> productos = session.createQuery(criteria).getResultList();

        session.close();

        return productos;
    }

    public List<Cliente> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);
        criteria.select(root).where(builder.equal(root.get("email"), email));

        List<Cliente> clientes = session.createQuery(criteria).getResultList();

        session.close();

        return clientes;
    }



    /*public static void main(String[] args) {
        *//*GenericDAO genericDAO = new GenericDAO();
        Producto producto = (Producto) genericDAO.findById(Producto.class, 7);
        Cliente cliente = (Cliente) genericDAO.findById(Cliente.class,3);
        cliente.addProducto(producto);
        genericDAO.update(cliente);*//*

        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.findByEmail("diegozama23@gmail.com");
        System.out.println(clientes);
    }*/
}
