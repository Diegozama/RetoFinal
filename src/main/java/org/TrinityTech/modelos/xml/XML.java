package org.TrinityTech.modelos.xml;

import org.TrinityTech.modelos.entidades.Cliente;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XML {
    public boolean escribir(List<Cliente> lista) {

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
            StreamResult result = new StreamResult("./src/main/resources/xml/Clientes.xml");
            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente.");

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static void main(String[] args) {
        ArrayList<Cliente> lista = new ArrayList<>();
        lista.add(new Cliente("Juan", "prueba@prueba.com"));

        new XML().escribir(lista);
    }
}
