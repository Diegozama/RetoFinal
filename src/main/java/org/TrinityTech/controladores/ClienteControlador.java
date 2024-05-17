package org.TrinityTech.controladores;


import org.TrinityTech.modelos.dao.GenericDAO;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.xml.XML;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class ClienteControlador {


    public Boolean importarXmlCliente(){


        // Crear un nuevo JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Establecer el nombre de archivo por defecto
        fileChooser.setSelectedFile(new File("clientes.xml"));

        // Mostrar el diálogo de guardar archivo
        int seleccion = fileChooser.showSaveDialog(null);

        File file = new File("./src/main/resources/xml/Clientes.xml");

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            // Obtener la ruta seleccionada por el usuario
            file = fileChooser.getSelectedFile();

            // Asegurarse de que la extensión del archivo sea .xml
            String filePath = file.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xml")) {
                file = new File(filePath + ".xml"); // Agregar extensión .txt si no está presente
            }


        }

        GenericDAO genericDAO = new GenericDAO(Cliente.class);
        List<Cliente> lista = genericDAO.findAll(Cliente.class);

        Boolean correcto = new XML().importarClientes(lista, file);

        if (correcto){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Boolean correcto = new ClienteControlador().importarXmlCliente();
        if(correcto){
            System.out.println("Lista de clientes importada correctamente");
        }else{
            System.out.println("Error");
        }
    }
}
