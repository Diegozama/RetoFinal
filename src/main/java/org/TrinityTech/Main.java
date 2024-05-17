package org.TrinityTech;

import org.TrinityTech.modelos.dao.GenericDAO;
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.xml.XML;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        GenericDAO dao = new GenericDAO(Cliente.class);
        List<Cliente> lista = new ArrayList<>();
        lista = dao.findAll(Cliente.class);
        // System.out.println(lista);

       // new XML().importarClientes(lista);
    }
}