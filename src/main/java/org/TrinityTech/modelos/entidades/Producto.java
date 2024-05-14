package org.TrinityTech.modelos.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GenericGenerator(name="incrementId", strategy = "increment") @GeneratedValue(generator = "incrementId")
    @Column(name = "id_producto") private int IdProducto;
    @Column(name = "nombre") private String nombre;
    @
}
