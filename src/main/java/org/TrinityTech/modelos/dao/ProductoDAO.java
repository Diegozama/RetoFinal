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

/**
 * DAO de especifico de productos
 */
public class ProductoDAO extends GenericDAO {

    public ProductoDAO(){
        super();
    }

    /**
     * Método para buscar los productos por su monbre
     * @param nombre parámetro de busqueda
     * @return
     */
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

    /**
     * Método para buscar un producto por el precio
     * @param precio parámetro de busqueda
     * @return
     */
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

    /**
     * Método para buscar un producto por su stock
     * @param stock parámetro de busqueda
     * @return
     */
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

    /**
     * Método para buscar un producto por su proveedor
     * @param proveedor parámetro de busqueda
     * @return
     */
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

    /**
     * Método para buscar los productos por su cliente
     * @param cliente parámetro de busqueda
     * @return
     */
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


    /*public static void main(String[] args) {

    }*/
}
