package org.TrinityTech.modelos.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GenericGenerator(name="incrementId", strategy = "increment") @GeneratedValue(generator = "incrementId")
    @Column(name = "id_proveedores") private int IdProveedores;
    @Column(name = "nombre") private String nombre;

    // Construtores
    public Proveedor() {
    }

    public Proveedor(int idProveedores, String nombre) {
        IdProveedores = idProveedores;
        this.nombre = nombre;
    }

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters


    public int getIdProveedores() {
        return IdProveedores;
    }

    public void setIdProveedores(int idProveedores) {
        IdProveedores = idProveedores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
