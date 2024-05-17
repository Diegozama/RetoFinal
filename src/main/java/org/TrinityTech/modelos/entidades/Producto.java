package org.TrinityTech.modelos.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GenericGenerator(name="incrementId", strategy = "increment") @GeneratedValue(generator = "incrementId")
    @Column(name = "id_producto") private int IdProducto;
    @Column(name = "nombre") private String nombre;
    @Column(name = "precio") private double precio;
    @Column(name = "stock") private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;


    @ManyToMany(mappedBy = "productos")
    private List<Cliente> clientes = new ArrayList<>();
    //-----------------------------

    // Cosntructores

    public Producto() {
    }

    public Producto(int idProducto, String nombre, double precio, int stock, Proveedor proveedor) {
        IdProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    public Producto(String nombre, double precio, int stock, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    // Setters y getters


    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "IdProducto=" + IdProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", proveedor=" + proveedor +
                ", clientes=" + clientes +
                '}';
    }
}
