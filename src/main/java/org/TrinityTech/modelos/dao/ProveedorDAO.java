package org.TrinityTech.modelos.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.entidades.Proveedor;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO de especifico de proveedores
 */
public class ProveedorDAO extends GenericDAO{

    public ProveedorDAO(){
        super();
    }

    /**
     * Método para buscar los proveedores por su nombre
     * @param nombre parámetros de busqueda
     * @return
     */
    public List<Proveedor> findByNombre(String nombre) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Proveedor> criteria = builder.createQuery(Proveedor.class);
        Root<Proveedor> root = criteria.from(Proveedor.class);
        criteria.select(root).where(builder.equal(root.get("nombre"), nombre));

        List<Proveedor> proveedors = session.createQuery(criteria).getResultList();

        session.close();

        return proveedors;
    }



    /*public static void main(String[] args) {
    }*/
}
