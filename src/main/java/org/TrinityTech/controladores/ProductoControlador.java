package org.TrinityTech.controladores;

import org.TrinityTech.interfaces.VistaProducto;
import org.TrinityTech.modelos.dao.GenericDAO;
import org.TrinityTech.modelos.dao.ProductoDAO;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.entidades.Producto;
import org.TrinityTech.modelos.entidades.Proveedor;
import org.TrinityTech.modelos.xml.XML;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class ProductoControlador {

    private VistaProducto vistaProducto;
    private GenericDAO genericDAO;
    private ProductoDAO productoDAO;
    private XML xml;

    public ProductoControlador(VistaProducto vistaProducto){
        this.vistaProducto = vistaProducto;
        this.genericDAO = new GenericDAO(Producto.class);
        this.productoDAO = new ProductoDAO();
        this.xml = new XML();

        mostrarProductos();

        // Evento de boton agregar
        vistaProducto.getAgregarClienteDialog().getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = vistaProducto.getAgregarClienteDialog().getNombreField().getText();
                Double precio = Double.parseDouble(vistaProducto.getAgregarClienteDialog().getPrecioField().getText());
                int stock = Integer.parseInt(vistaProducto.getAgregarClienteDialog().getStockField().getText());
                List<Proveedor> proveedores = genericDAO.findAll(Proveedor.class);
                DefaultComboBoxModel<Proveedor> defaultComboBoxModel = new DefaultComboBoxModel<>(proveedores.toArray(new Proveedor[0]));
                vistaProducto.getAgregarClienteDialog().getProveedorJComboBox().setModel(defaultComboBoxModel);
                Proveedor proveedor = (Proveedor) vistaProducto.getAgregarClienteDialog().getProveedorJComboBox().getSelectedItem();
                genericDAO.save(new Producto(nombre,precio,stock,proveedor));

                mostrarProductos();
            }
        });

        // Evento de modificar
        vistaProducto.getModificarProductoDialog().getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(vistaProducto.getModificarProductoDialog().getIdField().getText());
                String nombre = vistaProducto.getModificarProductoDialog().getNombreField().getText();
                Double precio = Double.parseDouble(vistaProducto.getAgregarClienteDialog().getPrecioField().getText());
                int stock = Integer.parseInt(vistaProducto.getAgregarClienteDialog().getStockField().getText());
                List<Proveedor> proveedores = genericDAO.findAll(Proveedor.class);
                DefaultComboBoxModel<Proveedor> defaultComboBoxModel = new DefaultComboBoxModel<>(proveedores.toArray(new Proveedor[0]));
                vistaProducto.getAgregarClienteDialog().getProveedorJComboBox().setModel(defaultComboBoxModel);
                Proveedor proveedor = (Proveedor) vistaProducto.getAgregarClienteDialog().getProveedorJComboBox().getSelectedItem();

                genericDAO.update(new Producto(nombre,precio,stock,proveedor));

                mostrarProductos();
            }
        });

        // Evento de boton eliminar
        vistaProducto.getEliminarProductoDialog().getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(vistaProducto.getEliminarProductoDialog().getIdField().getText());

                genericDAO.delete(new Producto(id));

                mostrarProductos();
            }
        });

        //Evento de boton exportar
        vistaProducto.getExportarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarXmlProductos();
            }
        });

    }

    public void importarXmlProductos(){

        Boolean correcto = false;

        // Crear un nuevo JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Establecer el nombre de archivo por defecto
        fileChooser.setSelectedFile(new File("productos.xml"));

        // Mostrar el diálogo de guardar archivo
        int seleccion = fileChooser.showSaveDialog(null);

        File file;

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            // Obtener la ruta seleccionada por el usuario
            file = fileChooser.getSelectedFile();

            // Asegurarse de que la extensión del archivo sea .xml
            String filePath = file.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xml")) {
                file = new File(filePath + ".xml"); // Agregar extensión .txt si no está presente
            }

            GenericDAO genericDAO = new GenericDAO(Producto.class);
            List<Producto> lista = genericDAO.findAll(Producto.class);

            correcto = new XML().importarProductos(lista, file);

            if (correcto){
                JOptionPane.showMessageDialog(null, "Se guardó el archivo correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // Mostrar los productos
    public void mostrarProductos(){

        // Obtener la lista de productos desde el DAO
        List<Producto> productos = genericDAO.findAll(Producto.class);
        String matriz[][] = new String[productos.size()][5];

        // Recorrer la lista de productos y añadir cada cliente como una fila en el modelo de la tabla
        for (int i = 0; i < productos.size(); i++) {
            matriz[i][0] = String.valueOf(productos.get(i).getIdProducto());
            matriz[i][1] = String.valueOf(productos.get(i).getNombre());
            matriz[i][2] = String.valueOf(productos.get(i).getPrecio());
            matriz[i][3] = String.valueOf(productos.get(i).getStock());
            matriz[i][4] = String.valueOf(productos.get(i).getProveedor().getIdProveedor());
        }

        String columnas[] = {"ID Cliente", "Nombre", "Precio","Stock","Id Proveedor"};
        DefaultTableModel model = new DefaultTableModel(matriz,columnas);
        vistaProducto.getProductoTable().setModel(model);

    }

    public static void main(String[] args) {
        new ProductoControlador(new VistaProducto());
    }
}
