package org.TrinityTech.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista_Producto extends JFrame {
    private JTable productosTable;
    private DefaultTableModel productosTableModel;
    private JButton agregarButton;
    private JButton refreshButton;


    public Vista_Producto() {
        setTitle("Gestionar clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        JPanel panelProductos = new JPanel(new BorderLayout());

        agregarButton = new JButton("Agregar productos");
        refreshButton = new JButton("Actualizar productos");

        productosTableModel = new DefaultTableModel(new Object[]{"ID Producto", "Nombre", "Precio", "Stock", "ID Proveedor"}, 0);
        productosTable = new JTable(productosTableModel);
        JScrollPane scrollPane = new JScrollPane(productosTable);

        panelProductos.add(scrollPane, BorderLayout.CENTER);
        panelProductos.add(refreshButton, BorderLayout.SOUTH);

        panelPrincipal.add(agregarButton);
        panelPrincipal.add(panelProductos);

        add(panelPrincipal);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarProductoDialog agregarProductoDialog = new AgregarProductoDialog(Vista_Producto.this);
                agregarProductoDialog.setVisible(true);
            }
        });

        setVisible(true);

    }
    public static void main(String[] args) {
        new Vista_Producto();
    }

    private class AgregarProductoDialog extends JDialog{

        private JTextField nombreField, precioField, stockField;
        private JButton agregarButton;

        public AgregarProductoDialog(Frame owner){
            super(owner,"Agregar Producto",true);
            setSize(300,200);
            setLocationRelativeTo(owner);

            JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
            JLabel nombreLabel = new JLabel("Nombre:");
            nombreField = new JTextField(20);

            JLabel precioLabel = new JLabel("Precio:");
            precioField = new JTextField(20);

            JLabel stockLabel= new JLabel("Stock");
            stockField= new JTextField(20);

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

            agregarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String nombre = nombreField.getText();
                    String precio = precioField.getText();
                    String stock = stockField.getText();
                    dispose();
                }
            });
        }
    }
}
