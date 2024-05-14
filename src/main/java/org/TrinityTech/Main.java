package org.TrinityTech;

import org.TrinityTech.modelos.dao.GenericDAO;
import org.TrinityTech.modelos.entidades.Cliente;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        GenericDAO daoClientes = new GenericDAO(Cliente.class);
        daoClientes.save(new Cliente("kleyner","prueba@prueba.es"));

        Cliente cli = (Cliente) daoClientes.findById(Cliente.class,1);

        //daoClientes.delete(cli);

        System.out.println("Con genericos :" + cli.getNombre());
    }
}