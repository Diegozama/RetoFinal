package org.TrinityTech.modelos.xml;

import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.entidades.Producto;
import org.TrinityTech.modelos.entidades.Proveedor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Clase con métodos relacionadas con exportación
 * datos a un formato XML
 */
public class XML {

    /**
     * Método que exportar una lista de la clase Cliente en formato XML a una ruta
     * @param lista Lista que contiene elementos de la clase Cliente
     * @param path Ruta donde se guardara el archivo XML
     * @return Devuelve un booleano. Si se guardo correctamente devuelve true, si no delvolverá false
     */
    public boolean importarClientes(List<Cliente> lista, File path) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = dBuilder.newDocument();

            Element rootElement = documento.createElement("Clientes");

            Iterator<Cliente> it = lista.iterator();

            while (it.hasNext()) {

                Cliente p = it.next();
                Element ClienteElement = documento.createElement("Cliente");
                ClienteElement.setAttribute("id_cliente", "" + p.getIdCliente());

                Element nombre = documento.createElement("Nombre");
                nombre.appendChild(documento.createTextNode(p.getNombre()));
                ClienteElement.appendChild(nombre);  // Nombre es hijo de producto

                Element email = documento.createElement("Email");
                email.setTextContent("" + p.getEmail());
                ClienteElement.appendChild(email);  //  Unidades es hijo de producto

                rootElement.appendChild(ClienteElement); // Producto es hijo de tienda

            }

            documento.appendChild(rootElement);

            // Configurar indentación XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Activar la indentación
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // Espacios para la indentación

            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(path);
            transformer.transform(source, result);


            return true;

        } catch (TransformerException trans) {
            System.out.println(trans.getMessage());
        }catch (ParserConfigurationException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
    public boolean importarProductos(List<Producto> lista) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = dBuilder.newDocument();

            Element rootElement = documento.createElement("Productos");

            Iterator<Producto> it = lista.iterator();

            while (it.hasNext()) {

                Producto p = it.next();
                Element ProductoElement = documento.createElement("Producto");
                ProductoElement.setAttribute("id_producto", "" + p.getIdProducto());

                Element nombre = documento.createElement("Nombre");
                nombre.appendChild(documento.createTextNode(p.getNombre()));
                ProductoElement.appendChild(nombre);  // Nombre es hijo de producto

                Element Precio = documento.createElement("Precio");
                Precio.setTextContent("" + p.getPrecio());
                ProductoElement.appendChild(Precio);  //  Unidades es hijo de producto

                Element Stock = documento.createElement("Stock");
                Stock.setTextContent("" + p.getStock());
                ProductoElement.appendChild(Stock);  //  Unidades es hijo de producto

                rootElement.appendChild(ProductoElement); // Producto es hijo de tienda

            }


            documento.appendChild(rootElement);

            // Configurar indentación XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Activar la indentación
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // Espacios para la indentación

            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult("./src/main/resources/xml/Productos.xml");
            transformer.transform(source, result);


            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
    public boolean importarProveedores(List<Proveedor> lista) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = dBuilder.newDocument();

            Element rootElement = documento.createElement("Proveedores");

            Iterator<Proveedor> it = lista.iterator();

            while (it.hasNext()) {

                Proveedor p = it.next();
                Element ProveedorElement = documento.createElement("Proveedor");
                ProveedorElement.setAttribute("id_proveedor", "" + p.getIdProveedor());

                Element nombre = documento.createElement("Nombre");
                nombre.appendChild(documento.createTextNode(p.getNombre()));
                ProveedorElement.appendChild(nombre);  // Nombre es hijo de producto

                rootElement.appendChild(ProveedorElement); // Producto es hijo de tienda

            }
            documento.appendChild(rootElement);

            // Configurar indentación XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Activar la indentación
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // Espacios para la indentación

            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult("./src/main/resources/xml/Proveedores.xml");
            transformer.transform(source, result);


            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
