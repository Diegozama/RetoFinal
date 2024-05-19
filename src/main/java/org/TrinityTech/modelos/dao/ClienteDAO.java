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

public class ClienteDAO extends GenericDAO {

    public ClienteDAO(){
        super(Cliente.class);
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

    public List<Producto> findProductosByCliente(Cliente cliente) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
        Root<Cliente> rootCliente = criteria.from(Cliente.class);

        // Join para obtener los productos asociados al cliente
        Join<Cliente, Producto> joinProducto = rootCliente.join("productos");

        // Filtrar por cliente
        criteria.select(joinProducto).where(builder.equal(rootCliente, cliente));

        List<Producto> productos = session.createQuery(criteria).getResultList();

        session.close();

        return productos;
    }

    public static void main(String[] args) {
        GenericDAO genericDAO = new GenericDAO(Cliente.class);
        //genericDAO.save(new Cliente("Rodrigo", "rodrigo@prueba.com"));
        //genericDAO.delete(new Cliente(14,"",""));
        genericDAO.update(new Cliente(14,"Mario", "mario@gmail.com"));
        List<Cliente> lista = genericDAO.findAll(Cliente.class);
        //ClienteDAO clienteDAO = new ClienteDAO();

        //List<Cliente> lista = clienteDAO.findByNombre("prueba");
        System.out.println(lista);

        /*GenericDAO genericDAO = new ClienteDAO();
        Cliente cliente = (Cliente) genericDAO.findById(Cliente.class, 2);

        ClienteDAO clienteDAO = new ClienteDAO();
        List<Producto> lista = clienteDAO.findProductosByCliente(cliente);
        System.out.println(lista);*/
    }
}
