package com.LS.Dominio;

import com.LS.Dominio.Entidad.TestEntidad;
import com.LS.Dominio.ObjetoValor.TestObjetoValor;
import com.LS.Dominio.Parser.TestParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DominioApplicationTests {

    TestEntidad testEntidad = new TestEntidad();

    TestObjetoValor testObjetoValor = new TestObjetoValor();

    TestParser testParser = new TestParser();

    @Test
    void main() {
        System.out.println("Ejecutando test del dominio...");
        System.out.println("[Test] - Entidad: Espacio");
        testEntidad.TestEspacio();
        System.out.println("[Test] - Entidad: Gerente");
        testEntidad.TestGerente();
        System.out.println("[Test] - Entidad: Reserva");
        testEntidad.TestReserva();
        System.out.println("[Test] - Objeto-Valor: Equipamiento");
        testObjetoValor.TestEquipamiento();
        System.out.println("[Test] - Objeto-Valor: Ubicacion");
        testObjetoValor.TestUbicacion();
        System.out.println("[Test] - Objeto-Valor: Usuario");
        testObjetoValor.TestUsuario();

        System.out.println("[Test] - Parser: ObjetosValor");
        testParser.TestObjetosValorParser();
        System.out.println("[Test] - Parser: Espacio");
        testParser.TestEspacioParser();
        System.out.println("[Test] - Parser: Reserva");
        testParser.TestReservaParser();

        System.out.println("Test del controlador pasados");
    }

}
