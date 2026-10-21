
package mx.edu.practica4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EstacionamientoTest {

    private Estacionamiento sistema;

    @BeforeEach
    void setUp() {
        sistema = new Estacionamiento();
    }

    @ParameterizedTest(name = "Caso {0}: {1} min, cliente ''{2}'', perdido={3} -> ${4}")
    @CsvSource({
        "1, 10, normal, false, 0.0",
        "2, 40, normal, false, 20.0"
    })
    void testCasosNormales(int casoNum, int minutos, String tipoCliente, boolean boletoPerdido, double esperado) {
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(esperado, resultado, 0.001, "Fallo en el Caso " + casoNum);
    }

    
    @ParameterizedTest(name = "Caso {0}: {1} min -> ${4}")
    @CsvSource({
        "3, 179, normal, false, 50.0",
        "4, 180, normal, false, 50.0",
        "5, 181, normal, false, 65.0",
        "6, 182, normal, false, 65.0"
    })
    void testCasosFrontera179a182(int casoNum, int minutos, String tipoCliente, boolean boletoPerdido, double esperado) {
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(esperado, resultado, 0.001, "Fallo en el Caso " + casoNum);
    }

    
    @ParameterizedTest(name = "Caso {0}: {1} min -> ${4}")
    @CsvSource({
        "7, 239, normal, false, 65.0",
        "8, 240, normal, false, 65.0",
        "9, 241, normal, false, 80.0",
        "10, 242, normal, false, 80.0"
    })
    void testCasosFrontera239a242(int casoNum, int minutos, String tipoCliente, boolean boletoPerdido, double esperado) {
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(esperado, resultado, 0.001, "Fallo en el Caso " + casoNum);
    }

    
    @ParameterizedTest(name = "Caso {0}: {1} min -> ${4}")
    @CsvSource({
        "11, 299, normal, false, 80.0",
        "12, 300, normal, false, 80.0",
        "13, 301, normal, false, 95.0",
        "14, 302, normal, false, 95.0"
    })
    void testCasosFrontera299a302(int casoNum, int minutos, String tipoCliente, boolean boletoPerdido, double esperado) {
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(esperado, resultado, 0.001, "Fallo en el Caso " + casoNum);
    }


    @Test
    void testMinutoCero() {
        int minutos = 0;
        String tipoCliente = "normal";
        boolean boletoPerdido = false;
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(0.0, resultado, 0.001);
    }


    @Test
    void testBoletoPerdido() {
        int minutos = 5;
        String tipoCliente = "normal";
        boolean boletoPerdido = true;
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(300.0, resultado, 0.001);
    }


    @Test
    void testMinutosNegativos() {
        int minutos = -16;
        String tipoCliente = "normal";
        boolean boletoPerdido = false;
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> sistema.calcularTotal(minutos, tipoCliente, boletoPerdido)
        );
        assertEquals("Los minutos no pueden ser negativos", excepcion.getMessage());
    }


    @Test
    void testClienteEntradaInvalida() {
        int minutos = 45;
        String tipoCliente = "cliente normal";
        boolean boletoPerdido = false;
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> sistema.calcularTotal(minutos, tipoCliente, boletoPerdido)
        );
        assertEquals("Tipo de cliente no válido", excepcion.getMessage());
    }


    @Test
    void testBoletoPerdidoClienteFrecuente() {
        int minutos = 20;
        String tipoCliente = "frecuente";
        boolean boletoPerdido = true;
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(300.0, resultado, 0.001);
    }


    @Test
    void testMinutoCeroClienteFrecuente() {
        int minutos = 0;
        String tipoCliente = "frecuente";
        boolean boletoPerdido = false;
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(0.0, resultado, 0.001);
    }


    @Test
    void testBoletoPerdidoMuchosMinutos() {
        int minutos = 10000;
        String tipoCliente = "normal";
        boolean boletoPerdido = true;
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(300.0, resultado, 0.001);
    }


    @Test
    void testCostoConDecimal() {
        int minutos = 301;
        String tipoCliente = "frecuente";
        boolean boletoPerdido = false;
        double resultado = sistema.calcularTotal(minutos, tipoCliente, boletoPerdido);
        assertEquals(85.5, resultado, 0.001);
    }


    @Test
    void testMinutosConDecimales() {
        double minutos = 30.5;
        String tipoCliente = "normal";
        boolean boletoPerdido = false;
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> sistema.calcularTotal(minutos, tipoCliente, boletoPerdido)
        );
        assertEquals("Solo se admiten minutos expresados en números enteros", excepcion.getMessage());
    }
}
