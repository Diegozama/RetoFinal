package org.TrinityTech.controladores;

import org.TrinityTech.interfaces.VistaProducto;
import org.TrinityTech.interfaces.VistaProveedor;
import org.TrinityTech.modelos.dao.GenericDAO;
import org.TrinityTech.modelos.dao.ProveedorDAO;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.entidades.Proveedor;
import org.TrinityTech.modelos.xml.XML;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class ProveedorControlador {

    private VistaProveedor vistaProveedor;
    private GenericDAO genericDAO;
    private ProveedorDAO proveedorDAO;
    private XML xml;

    public ProveedorControlador( VistaProveedor vistaProveedor){
        this.vistaProveedor = vistaProveedor;
        genericDAO = new GenericDAO();
        proveedorDAO = new ProveedorDAO();
        xml = new XML();

        mostrarProveedores();

        // Evento de boton agregar
        vistaProveedor.getAgregarProveedorDialog().getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = vistaProveedor.getAgregarProveedorDialog().getNombreField().getText();

                genericDAO.save(new Proveedor(nombre));

                mostrarProveedores();
            }
        });

        // Evento de modificar
        vistaProveedor.getModificarProveedorDialog().getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(vistaProveedor.getModificarProveedorDialog().getIdField().getText());
                String nombre = vistaProveedor.getModificarProveedorDialog().getNombreField().getText();


                genericDAO.update(new Proveedor(id, nombre));

                mostrarProveedores();
            }
        });

        // Evento de boton eliminar
        vistaProveedor.getEliminarProveedorDialog().getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(vistaProveedor.getEliminarProveedorDialog().getIdField().getText());

                genericDAO.delete(new Proveedor(id));

                mostrarProveedores();
            }
        });

    }

    public void importarXmlCliente(){

        Boolean correcto = false;

        // Crear un nuevo JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Establecer el nombre de archivo por defecto
        fileChooser.setSelectedFile(new File("proveedores.xml"));

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

            GenericDAO genericDAO = new GenericDAO();
            List<Proveedor> lista = genericDAO.findAll(Proveedor.class);

            correcto = new XML().importarProveedores(lista, file);

            if (correcto){
                JOptionPane.showMessageDialog(null, "Se guardó el archivo correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // Mostrar los clientes
    public void mostrarProveedores(){

        // Obtener la lista de proveedores desde el DAO
        List<Proveedor> proveedores = genericDAO.findAll(Proveedor.class);
        String matriz[][] = new String[proveedores.size()][2];

        // Recorrer la lista de proveedores y añadir cada cliente como una fila en el modelo de la tabla
        for (int i = 0; i < proveedores.size(); i++) {
            matriz[i][0] = String.valueOf(proveedores.get(i).getIdProveedor());
            matriz[i][1] = String.valueOf(proveedores.get(i).getNombre());
        }

        String columnas[] = {"ID Proveedor", "Nombre"};
        DefaultTableModel model = new DefaultTableModel(matriz,columnas);
        vistaProveedor.getProveedoresTable().setModel(model);

    }

    public static void main(String[] args) {
        VistaProveedor v = new VistaProveedor();
        new ProveedorControlador(v);

        v.setVisible(true);
    }
}
