package org.TrinityTech.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista_Productos extends JFrame {

    private JTable productosTable;
    private DefaultTableModel productosTableModel;
    private JButton agregarButton;
    private JButton refreshButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton exportarButton;
    AgregarProductoDialog agregarProductoDialog;
    EliminarProductoDialog eliminarProductoDialog;
    ModificarProductoDialog modificarProductoDialog;

    public Vista_Productos() {


        setTitle("Gestionar productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        JPanel panelProductos = new JPanel(new BorderLayout());

        agregarButton = new JButton("Agregar productos");
        modificarButton = new JButton("Modificar producto");
        eliminarButton = new JButton("Eliminar producto");
        refreshButton = new JButton("Actualizar producto");
        exportarButton = new JButton("Exportar a XML");

        productosTable = new JTable(productosTableModel);

        JScrollPane scrollPane = new JScrollPane(productosTable);
        panelProductos.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(exportarButton);
        panelProductos.add(buttonPanel, BorderLayout.SOUTH);

        panelPrincipal.add(panelProductos);

        add(panelPrincipal);

        agregarProductoDialog = new AgregarProductoDialog();
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                agregarProductoDialog.setVisible(true);
            }
        });

        modificarProductoDialog = new ModificarProductoDialog();
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProductoDialog.setVisible(true);
            }
        });

        eliminarProductoDialog = new EliminarProductoDialog();
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                eliminarProductoDialog.setVisible(true);
            }
        });

        setVisible(true);
    }

    public JButton getExportarButton() {
        return exportarButton;
    }

    public JTable getProductosTable() {
        return productosTable;
    }

    public DefaultTableModel getProductosTableModel() {
        return productosTableModel;
    }

    public AgregarProductoDialog getAgregarProductoDialog() {
        return agregarProductoDialog;
    }

    public EliminarProductoDialog getEliminarProductoDialog() {
        return eliminarProductoDialog;
    }

    public ModificarProductoDialog getModificarProductoDialog() {
        return modificarProductoDialog;
    }

    public class AgregarProductoDialog extends JDialog {
        private JTextField nombreField, emailField;
        private JButton agregarButton;

        public AgregarProductoDialog() {

            setSize(300, 200);
            setLocationRelativeTo(null);

            JPanel panelFormulario = new JPanel(new GridLayout(3, 2));
            JLabel nombreLabel = new JLabel("Nombre:");
            nombreField = new JTextField(20);
            JLabel precioLabel = new JLabel("Precio:");
            precioField = new JTextField(20);

            JLabel stockLabel= new JLabel("Stock");
            sotckField= new JTextField(20);

            agregarButton = new JButton("Agregar");

            panelFormulario.add(nombreLabel);
            panelFormulario.add(nombreField);
            panelFormulario.add(precioLabel);
            panelFormulario.add(precioField);
            panelFormulario.add(stockLabel);
            panelFormulario.add(stockField);


            panelFormulario.add(new JLabel());
            panelFormulario.add(agregarButton);

            add(panelFormulario);

        }

        public JTextField getNombreField() {
            return nombreField;
        }

        public JTextField getPrecioField() {
            return precioField;
        }

        public JtextField getStockField(){return getStockField;}

        public JButton getAgregarButton() {
            return agregarButton;
        }
    }

    public class ModificarProductoDialog extends JDialog {
        private JTextField idField, nombreField, precioField, stockField;
        private JButton modificarButton;

        public ModificarProductoDialog() {

            setSize(300, 200);
            setLocationRelativeTo(null);

            JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
            JLabel idLabel = new JLabel("ID del Producto:");
            idField = new JTextField(20);
            JLabel nombreLabel = new JLabel("Nuevo Nombre:");
            nombreField = new JTextField(20);
            JLabel precioLabel = new JLabel("Nuevo Precio:");
            precioField = new JTextField(20);
            JLabel stockLabel= new Jlabel("Nuevo Stock");
            stockField= new JTextField(20);
            modificarButton = new JButton("Modificar");

            panelFormulario.add(idLabel);
            panelFormulario.add(idField);
            panelFormulario.add(nombreLabel);
            panelFormulario.add(nombreField);
            panelFormulario.add(precioLabel);
            panelFormulario.add(precioField);
            panelFormulario.add(stockLabel);
            panelFormulario.add(stockField);
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

        public JTextField getPrecioField() {
            return precioField;
        }

        public JTextField getStockField(){return stockField;}

        public JButton getModificarButton() {
            return modificarButton;
        }
    }

    public class EliminarProductoDialog extends JDialog {
        private JTextField idField;
        private JButton eliminarButton;

        public JTextField getIdField() {
            return idField;
        }

        public JButton getEliminarButton() {
            return eliminarButton;
        }

        public EliminarProductoDialog() {
            setSize(300, 150);
            setLocationRelativeTo(null);

            JPanel panelFormulario = new JPanel(new GridLayout(2, 2));
            JLabel idLabel = new JLabel("ID del Producto a Eliminar:");
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
        new Vista_Productos();
    }
}
