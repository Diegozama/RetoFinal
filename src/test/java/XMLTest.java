
import org.TrinityTech.modelos.entidades.Cliente;
import org.TrinityTech.modelos.entidades.Producto;
import org.TrinityTech.modelos.entidades.Proveedor;
import org.TrinityTech.modelos.xml.XML;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLTest {
    XML xml = new XML();

    @Test
    public void testImportarCliente(){
        List<Cliente> lista = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Cliente cli = new Cliente(i,"prueba" + i, "prueba"+i+"@prueba.es");
            lista.add(cli);
        }

        Boolean expected = true;
        Boolean actual = xml.importarClientes(lista, new File("./src/main/resources/xml/Clientes.xml"));

        assertEquals(expected, actual);
    }

    @Test
    public void testImportarProductos(){
        List<Producto> lista = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Producto producto = new Producto(i,"producto"+i, i*100,i*100,new Proveedor(i));
            lista.add(producto);
        }

        Boolean expected = true;
        Boolean actual = xml.importarProductos(lista, new File("./src/main/resources/xml/Proveedor.xml"));

        assertEquals(expected, actual);
    }

    @Test
    public void testImportarProveedores(){
        List<Proveedor> lista = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Proveedor proveedor = new Proveedor(1,"Proveedor"+i);
            lista.add(proveedor);
        }

        Boolean expected = true;
        Boolean actual = xml.importarProveedores(lista, new File("./src/main/resources/xml/Producto.xml"));

        assertEquals(expected, actual);
    }
}
