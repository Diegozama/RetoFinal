
import org.TrinityTech.modelos.entidades.Cliente;
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
        }

        Boolean expected = true;
        Boolean actual = xml.importarClientes(lista, new File("./src/main/resources/xml/Clientes.xml"));

        assertEquals(expected, actual);
    }
}
