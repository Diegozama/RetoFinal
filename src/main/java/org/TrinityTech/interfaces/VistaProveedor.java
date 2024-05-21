package org.TrinityTech.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaProveedor extends JFrame{
    private JTable proveedoresTable;
    private DefaultTableModel proveedoresTableModel;
    private JButton agregarButton;
    private JButton refreshButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton exportarButton;
    private AgregarProveedorDialog agregarProveedorDialog;
    private ModificarProveedorDialog modificarProveedorDialog;
    private EliminarProveedorDialog eliminarProveedorDialog;


    public VistaProveedor() {


        setTitle("Gestionar proveedores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        JPanel panelProveedores = new JPanel(new BorderLayout());

        agregarButton = new JButton("Agregar proveedor");
        modificarButton = new JButton("Modificar proveedor");
        eliminarButton = new JButton("Eliminar proveedor");
        exportarButton = new JButton("Exportar a XML");

        proveedoresTable = new JTable(proveedoresTableModel);

        JScrollPane scrollPane = new JScrollPane(proveedoresTable);
        panelProveedores.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(exportarButton);
        panelProveedores.add(buttonPanel, BorderLayout.SOUTH);

        panelPrincipal.add(panelProveedores);

        add(panelPrincipal);

        agregarProveedorDialog = new VistaProveedor.AgregarProveedorDialog();
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                agregarProveedorDialog.setVisible(true);
            }
        });

        modificarProveedorDialog = new VistaProveedor.ModificarProveedorDialog();
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProveedorDialog.setVisible(true);
            }
        });

        eliminarProveedorDialog = new VistaProveedor.EliminarProveedorDialog();
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                eliminarProveedorDialog.setVisible(true);
            }
        });

        setVisible(true);
    }

    public JButton getExportarButton() {
        return exportarButton;
    }

    public JTable getProveedoresTable() {
        return proveedoresTable;
    }

    public DefaultTableModel getProveedoresTableModel() {
        return proveedoresTableModel;
    }


    public class AgregarProveedorDialog extends JDialog {
        private JTextField nombreField;
        private JButton agregarButton;

        public AgregarProveedorDialog() {

            setSize(300, 200);
            setLocationRelativeTo(null);

            JPanel panelFormulario = new JPanel(new GridLayout(2, 2));
            JLabel nombreLabel = new JLabel("Nombre:");
            nombreField = new JTextField(20);
            agregarButton = new JButton("Agregar");

            panelFormulario.add(nombreLabel);
            panelFormulario.add(nombreField);
            panelFormulario.add(new JLabel());
            panelFormulario.add(agregarButton);

            add(panelFormulario);

        }

        public JTextField getNombreField() {
            return nombreField;
        }


        public JButton getAgregarButton() {
            return agregarButton;
        }
    }

    public class ModificarProveedorDialog extends JDialog {
        private JTextField idField, nombreField;
        private JButton modificarButton;

        public ModificarProveedorDialog() {

            setSize(300, 200);
            setLocationRelativeTo(null);

            JPanel panelFormulario = new JPanel(new GridLayout(3, 2));
            JLabel idLabel = new JLabel("ID del Proveedor:");
            idField = new JTextField(20);
            JLabel nombreLabel = new JLabel("Nuevo Nombre:");
            nombreField = new JTextField(20);
            modificarButton = new JButton("Modificar");

            panelFormulario.add(idLabel);
            panelFormulario.add(idField);
            panelFormulario.add(nombreLabel);
            panelFormulario.add(nombreField);
            panelFormulario.add(new JLabel());
            panelFormulario.add(modificarButton);

            add(panelFormulario);
        }

        public JTextField getIdField() {
            return idField;
        }

        public JTextField getNombreField() {
            return nombreField;
        }

        public JButton getModificarButton() {
            return modificarButton;
        }
    }

    public class EliminarProveedorDialog extends JDialog {
        private JTextField idField;
        private JButton eliminarButton;

        public JTextField getIdField() {
            return idField;
        }

        public JButton getEliminarButton() {
            return eliminarButton;
        }

        public EliminarProveedorDialog() {
            setSize(300, 150);
            setLocationRelativeTo(null);

            JPanel panelFormulario = new JPanel(new GridLayout(2, 2));
            JLabel idLabel = new JLabel("ID del Proveedor a Eliminar:");
            idField = new JTextField(20);
            eliminarButton = new JButton("Eliminar");

            panelFormulario.add(idLabel);
            panelFormulario.add(idField);
            panelFormulario.add(new JLabel());
            panelFormulario.add(eliminarButton);

            add(panelFormulario);


        }
    }

    public static void main(String[] args) {
        new VistaProveedor();
    }
}


