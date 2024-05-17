package org.TrinityTech.interfaces;

import org.TrinityTech.Main;
import org.TrinityTech.controladores.ClienteControlador;
import org.hibernate.boot.model.relational.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  Vista extends JFrame {

    private JTable clientesTable;
    private DefaultTableModel clientesTableModel;
    private JButton agregarButton;
    private JButton refreshButton;

    public Vista() {
        setTitle("Gestionar clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        JPanel panelClientes = new JPanel(new BorderLayout());

        agregarButton = new JButton("Agregar clientes");
        refreshButton = new JButton("Actualizar clientes");

        clientesTableModel = new DefaultTableModel(new Object[]{"ID Cliente", "Nombre", "Email"}, 0);
        clientesTable = new JTable(clientesTableModel);
        JScrollPane scrollPane = new JScrollPane(clientesTable);
        panelClientes.add(scrollPane, BorderLayout.CENTER);
        panelClientes.add(refreshButton, BorderLayout.SOUTH);

        panelPrincipal.add(agregarButton);
        panelPrincipal.add(panelClientes);

        add(panelPrincipal);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarClienteDialog agregarClienteDialog = new AgregarClienteDialog(Vista.this);
                agregarClienteDialog.setVisible(true);
            }
        });


        setVisible(true);
    }

    public static void main(String[] args) {
        new Vista();
    }

    private class AgregarClienteDialog extends JDialog {
        private JTextField nombreField, emailField;
        private JButton agregarButton;

        public AgregarClienteDialog(Frame owner) {
            super(owner, "Agregar Cliente", true);
            setSize(300, 200);
            setLocationRelativeTo(owner);

            JPanel panelFormulario = new JPanel(new GridLayout(3, 2));
            JLabel nombreLabel = new JLabel("Nombre:");
            nombreField = new JTextField(20);
            JLabel emailLabel = new JLabel("Email:");
            emailField = new JTextField(20);
            agregarButton = new JButton("Agregar");

            panelFormulario.add(nombreLabel);
            panelFormulario.add(nombreField);
            panelFormulario.add(emailLabel);
            panelFormulario.add(emailField);
            panelFormulario.add(new JLabel()); // Filler
            panelFormulario.add(agregarButton);

            add(panelFormulario);

            agregarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String nombre = nombreField.getText();
                    String email = emailField.getText();

                    dispose();
                }
            });
        }
    }
}
