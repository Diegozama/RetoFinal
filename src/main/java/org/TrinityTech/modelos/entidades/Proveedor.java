package org.TrinityTech.modelos.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * Clase que servirá para mapear los datos de los proveedores
 * en la base de datos
 */
@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GenericGenerator(name="incrementId", strategy = "increment") @GeneratedValue(generator = "incrementId")
    @Column(name = "id_proveedor") private int idProveedor;
    @Column(name = "nombre") private String nombre;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    // Construtores
    public Proveedor() {
    }

    public Proveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(int idProveedor, String nombre) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
    }

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters


    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
