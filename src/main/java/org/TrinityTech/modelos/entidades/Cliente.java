package org.TrinityTech.modelos.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GenericGenerator(name="incrementId", strategy = "increment") @GeneratedValue(generator = "incrementId")
    @Column(name = "id_cliente") private int IdCliente;
    @Column(name = "nombre") private String nombre;
    @Column(name = "email") private String email;

    // Constructores

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String email) {
        IdCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y setters


    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
