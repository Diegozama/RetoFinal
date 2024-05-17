package org.TrinityTech.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista_Cliente extends JFrame {

    private JTable clientesTable;
    private DefaultTableModel clientesTableModel;
    private JButton agregarButton;
    private JButton refreshButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton exportarButton;

    public Vista_Cliente() {
        setTitle("Gestionar clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        JPanel panelClientes = new JPanel(new BorderLayout());

        agregarButton = new JButton("Agregar clientes");
        modificarButton = new JButton("Modificar cliente");
        eliminarButton = new JButton("Eliminar cliente");
        refreshButton = new JButton("Actualizar clientes");
        exportarButton = new JButton("Exportar a XML"); // Nuevo botón

        clientesTableModel = new DefaultTableModel(new Object[]{"ID Cliente", "Nombre", "Email"}, 0);
        clientesTable = new JTable(clientesTableModel);
        JScrollPane scrollPane = new JScrollPane(clientesTable);
        panelClientes.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(exportarButton);
        panelClientes.add(buttonPanel, BorderLayout.SOUTH);

        panelPrincipal.add(panelClientes);

        add(panelPrincipal);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarClienteDialog agregarClienteDialog = new AgregarClienteDialog(Vista_Cliente.this);
                agregarClienteDialog.setVisible(true);
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientesTable.getSelectedRow();
                if (selectedRow != -1) {
                    ModificarClienteDialog modificarClienteDialog = new ModificarClienteDialog(Vista_Cliente.this);
                    modificarClienteDialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(Vista_Cliente.this, "Por favor, seleccione un cliente para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarClienteDialog eliminarClienteDialog = new EliminarClienteDialog(Vista_Cliente.this);
                eliminarClienteDialog.setVisible(true);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Vista_Cliente();
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
            panelFormulario.add(new JLabel());
            panelFormulario.add(agregarButton);

            add(panelFormulario);

            agregarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = nombreField.getText();
                    String email = emailField.getText();

                    // Aquí deberías guardar el cliente en tu base de datos o lista
                    // Luego actualiza la tabla
                    clientesTableModel.addRow(new Object[]{"ID", nombre, email});

                    dispose();
                }
            });
        }
    }

    private class ModificarClienteDialog extends JDialog {
        private JTextField idField, nombreField, emailField;
        private JButton modificarButton;

        public ModificarClienteDialog(Frame owner) {
            super(owner, "Modificar Cliente", true);
            setSize(300, 200);
            setLocationRelativeTo(owner);

            JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
            JLabel idLabel = new JLabel("ID del Cliente:");
            idField = new JTextField(20);
            JLabel nombreLabel = new JLabel("Nuevo Nombre:");
            nombreField = new JTextField(20);
            JLabel emailLabel = new JLabel("Nuevo Email:");
            emailField = new JTextField(20);
            modificarButton = new JButton("Modificar");

            panelFormulario.add(idLabel);
            panelFormulario.add(idField);
            panelFormulario.add(nombreLabel);
            panelFormulario.add(nombreField);
            panelFormulario.add(emailLabel);
            panelFormulario.add(emailField);
            panelFormulario.add(new JLabel());
            panelFormulario.add(modificarButton);

            add(panelFormulario);

            modificarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String idCliente = idField.getText();
                    String nombre = nombreField.getText();
                    String email = emailField.getText();


                    dispose();
                }
            });
        }
    }

    private class EliminarClienteDialog extends JDialog {
        private JTextField idField;
        private JButton eliminarButton;

        public EliminarClienteDialog(Frame owner) {
            super(owner, "Eliminar Cliente", true);
            setSize(300, 150);
            setLocationRelativeTo(owner);

            JPanel panelFormulario = new JPanel(new GridLayout(2, 2));
            JLabel idLabel = new JLabel("ID del Cliente a Eliminar:");
            idField = new JTextField(20);
            eliminarButton = new JButton("Eliminar");

            panelFormulario.add(idLabel);
            panelFormulario.add(idField);
            panelFormulario.add(new JLabel());
            panelFormulario.add(eliminarButton);

            add(panelFormulario);

            eliminarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(EliminarClienteDialog.this, "¿Está seguro de que desea eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {

                        dispose();
                    }
                }
            });
        }
    }
}
