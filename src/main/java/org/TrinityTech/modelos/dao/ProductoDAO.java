package org.TrinityTech.modelos.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.entidades.Producto;
import org.TrinityTech.modelos.entidades.Proveedor;
import org.hibernate.Session;

import java.util.List;

public class ProductoDAO extends GenericDAO {

    public ProductoDAO(){
        super(Producto.class);
    }


    public List<Producto> findByNombre(String nombre) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
        Root<Producto> root = criteria.from(Producto.class);
        criteria.select(root).where(builder.equal(root.get("nombre"), nombre));

        List<Producto> productos = session.createQuery(criteria).getResultList();

        session.close();

        return productos;
    }

    public List<Producto> findByPrecio(double precio) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
        Root<Producto> root = criteria.from(Producto.class);
        criteria.select(root).where(builder.equal(root.get("precio"), precio));

        List<Producto> productos = session.createQuery(criteria).getResultList();

        session.close();

        return productos;
    }

    public List<Producto> findByStock(int stock) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
        Root<Producto> root = criteria.from(Producto.class);
        criteria.select(root).where(builder.equal(root.get("stock"), stock));

        List<Producto> productos = session.createQuery(criteria).getResultList();

        session.close();

        return productos;
    }

    public List<Producto> findByProveedor(Proveedor proveedor) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
        Root<Producto> root = criteria.from(Producto.class);
        criteria.select(root).where(builder.equal(root.get("proveedor"), proveedor));

        List<Producto> productos = session.createQuery(criteria).getResultList();

        session.close();

        return productos;
    }

    public List<Producto> getProductosDeCliente (int clienteId) {

        Session session = sessionFactory.openSession();
        TypedQuery<Producto> query
                = session.createQuery(
                "SELECT c.productos FROM Cliente c WHERE c.id_cliente = :clienteId ", Producto.class);

        query.setParameter("clienteId", clienteId);
        return query.getResultList();
    }


    public static void main(String[] args) {
        /*GenericDAO genericDAO = new GenericDAO(Proveedor.class);
        Proveedor proveedor = (Proveedor)genericDAO.findById(Proveedor.class,1);*/

        ProductoDAO productoDAO = new ProductoDAO();
        //List<Producto> list = productoDAO.findByProveedor(proveedor);

        List<Producto> list = productoDAO.getProductosDeCliente(new Cliente(1).getIdCliente());

        System.out.println(list);
    }
}
