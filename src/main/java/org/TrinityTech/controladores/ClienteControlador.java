package org.TrinityTech.controladores;


import org.TrinityTech.modelos.dao.GenericDAO;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.xml.XML;

import java.util.List;

public class ClienteControlador {


    public Boolean importarXmlCliente(){
        GenericDAO genericDAO = new GenericDAO(Cliente.class);
        List<Cliente> lista = genericDAO.findAll(Cliente.class);

        Boolean correcto = new XML().importarClientes(lista);

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
