import pytest
from estacionamiento import Estacionamiento


@pytest.fixture
def sistema():
    """Preparación reutilizable para las pruebas."""
    return Estacionamiento()


@pytest.mark.parametrize("caso_num, minutos, tipo_cliente, boleto_perdido, esperado",
    [
        (1, 10, "normal", False, 0.0),
        (2, 40, "normal", False, 20.0),
    ]
)
def test_casos_normales(sistema, caso_num, minutos, tipo_cliente, boleto_perdido, esperado):
    resultado = sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)
    assert resultado == esperado, f"Fallo en el Caso {caso_num}"


@pytest.mark.parametrize("caso_num, minutos, tipo_cliente, boleto_perdido, esperado",
    [
        (3, 179, "normal", False, 50.0),
        (4, 180, "normal", False, 50.0),
        (5, 181, "normal", False, 65.0),
        (6, 182, "normal", False, 65.0),
        (7, 239, "normal", False, 65.0),
        (8, 240, "normal", False, 65.0),
        (9, 241, "normal", False, 80.0),
        (10, 242, "normal", False, 80.0),
        (11, 299, "normal", False, 80.0),
        (12, 300, "normal", False, 80.0),
        (13, 301, "normal", False, 95.0),
        (14, 302, "normal", False, 95.0)
    ]
)
def test_casos_frontera(sistema, caso_num, minutos, tipo_cliente, boleto_perdido, esperado):
    resultado = sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)
    assert resultado == esperado, f"Fallo en el Caso {caso_num}"


def test_boleto_perdido(sistema):
    minutos = 5
    tipo_cliente = "normal"
    boleto_perdido = True
    resultado = sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)
    assert resultado == 300.0


def test_minutos_negativos(sistema):
    minutos = -16
    tipo_cliente = "normal"
    boleto_perdido = False
    with pytest.raises(ValueError, match="Los minutos no pueden ser negativos"):
        sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)


def test_cliente_entrada_invalida(sistema):
    # Arrange
    minutos = 45
    tipo_cliente = "cliente normal"
    boleto_perdido = False
    with pytest.raises(ValueError, match="Tipo de cliente no válido"):
        sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)


def test_boleto_perdido_cliente_frecuente(sistema):
    minutos = 20
    tipo_cliente = "frecuente"
    boleto_perdido = True
    resultado = sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)
    assert resultado == 300.0


def test_minuto_cero_cliente_frecuente(sistema):
    minutos = 0
    tipo_cliente = "frecuente"
    boleto_perdido = False
    resultado = sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)
    assert resultado == 0.0


def test_boleto_perdido_muchos_minutos(sistema):
    minutos = 10000
    tipo_cliente = "normal"
    boleto_perdido = True
    resultado = sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)
    assert resultado == 300.0


def test_costo_con_decimal(sistema):
    minutos = 301
    tipo_cliente = "frecuente"
    boleto_perdido = False
    resultado = sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)
    assert resultado == pytest.approx(85.5, abs=1e-2)


def test_minutos__con_decimales(sistema):
    minutos = 30.5
    tipo_cliente = "normal"
    boleto_perdido = False
    with pytest.raises((ValueError, TypeError)):
        sistema.calcular_total(minutos, tipo_cliente, boleto_perdido)