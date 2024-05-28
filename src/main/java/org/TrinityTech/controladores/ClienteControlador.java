package org.TrinityTech.controladores;


import org.TrinityTech.interfaces.VistaCliente;
import org.TrinityTech.modelos.dao.ClienteDAO;
import org.TrinityTech.modelos.dao.GenericDAO;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.xml.XML;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;


/**
 * Controlador del maoldeo y la interfaz de cliente
 */
public class ClienteControlador {

    private VistaCliente vistaCliente;
    private GenericDAO genericDAO;
    private ClienteDAO clienteDAO;
    private XML xml;

    public ClienteControlador(VistaCliente vistaCliente){
         this.vistaCliente = vistaCliente;
         this.genericDAO = new GenericDAO();
         this.clienteDAO = new ClienteDAO();
         this.xml = new XML();


         mostrarClientes();

        // Evento de boton agregar
        vistaCliente.getAgregarClienteDialog().getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = vistaCliente.getAgregarClienteDialog().getNombreField().getText();
                String email = vistaCliente.getAgregarClienteDialog().getEmailField().getText();

                genericDAO.save(new Cliente(nombre, email));
                mostrarClientes();

                JOptionPane.showMessageDialog(null, "Se guardó el cliente correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        // Evento de modificar
        vistaCliente.getModificarClienteDialog().getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(vistaCliente.getModificarClienteDialog().getIdField().getText());
                String nombre = vistaCliente.getModificarClienteDialog().getNombreField().getText();
                String email = vistaCliente.getModificarClienteDialog().getEmailField().getText();

                genericDAO.update(new Cliente(id, nombre, email));

                mostrarClientes();

                JOptionPane.showMessageDialog(null, "Se modifico el cliente con id "+ id+" correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // Evento de boton eleiminar
        vistaCliente.getEliminarClienteDialog().getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(vistaCliente.getEliminarClienteDialog().getIdField().getText());
                String mensaje = "¿Estas seguro de eliminar el cliente con id "+id+"?";
                String opciones[] = {"Confirmar", "Cancelar"};
                int confirmacion = JOptionPane.showOptionDialog(null,mensaje, "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[1] );

                if (confirmacion == 0){
                    genericDAO.delete(new Cliente(id));
                    mostrarClientes();
                    JOptionPane.showMessageDialog(null, "Se eliminó el cliente con id "+ id+" correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });


         //Evento de boton exportar
         vistaCliente.getExportarButton().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 importarXmlCliente();
             }
         });


    }

    /**
     * Método que permite elegir donde guardar el XML de clientes
     */
    public void importarXmlCliente(){

        Boolean correcto = false;

        // Crear un nuevo JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Establecer el nombre de archivo por defecto
        fileChooser.setSelectedFile(new File("clientes.xml"));

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
            List<Cliente> lista = genericDAO.findAll(Cliente.class);

            correcto = new XML().importarClientes(lista, file);

            if (correcto){
                JOptionPane.showMessageDialog(null, "Se guardó el archivo correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // Mostrar los clientes
    /**
     * Método que actualiza los clientes
     */
    public void mostrarClientes(){

        // Obtener la lista de clientes desde el DAO
        List<Cliente> clientes = genericDAO.findAll(Cliente.class);
        String matriz[][] = new String[clientes.size()][3];

        // Recorrer la lista de clientes y añadir cada cliente como una fila en el modelo de la tabla
        for (int i = 0; i < clientes.size(); i++) {
            matriz[i][0] = String.valueOf(clientes.get(i).getIdCliente());
            matriz[i][1] = String.valueOf(clientes.get(i).getNombre());
            matriz[i][2] = String.valueOf(clientes.get(i).getEmail());
        }

        String columnas[] = {"ID Cliente", "Nombre", "Email"};
        DefaultTableModel model = new DefaultTableModel(matriz,columnas);
        vistaCliente.getClientesTable().setModel(model);

    }

    /*public static void main(String[] args) {
        VistaCliente v = new VistaCliente();

        new ClienteControlador(v);

        v.setVisible(true);
    }*/
}
