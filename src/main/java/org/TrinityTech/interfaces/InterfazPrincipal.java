package org.TrinityTech.interfaces;

import org.TrinityTech.controladores.ClienteControlador;
import org.TrinityTech.controladores.ProductoControlador;
import org.TrinityTech.controladores.ProveedorControlador;
import org.TrinityTech.modelos.entidades.Cliente;

import javax.swing.*;

public class InterfazPrincipal extends JFrame {

    public InterfazPrincipal(){
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        VistaCliente vistaCliente = new VistaCliente();
        VistaProveedor vistaProveedor = new VistaProveedor();
        VistaProducto vistaProducto = new VistaProducto();

        ClienteControlador clienteControlador = new ClienteControlador(vistaCliente);
        /*ProveedorControlador proveedorControlador = new ProveedorControlador(vistaProveedor);
        ProductoControlador productoControlador = new ProductoControlador(vistaProducto);*/

        tabbedPane.addTab("Clientes" , vistaCliente.getContentPane());
        tabbedPane.addTab("Proveedores" , vistaProveedor.getContentPane());
        tabbedPane.addTab("Productos" , vistaProducto.getContentPane());

        add(tabbedPane);

        setVisible(true);
    }

}
