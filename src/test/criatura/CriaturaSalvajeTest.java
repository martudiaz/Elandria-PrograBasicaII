package criatura;
import excepcion.EnergiaDesbordadaException;

import static org.junit.Assert.*;

import org.junit.Test;

public class CriaturaSalvajeTest {
    
    @Test
    public void testCreacionCriaturaSalvaje() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        assertNotNull(criatura);
        assertEquals("Dragón Salvaje", criatura.getNombre());
        assertEquals(100, criatura.getEnergia());
        assertEquals(AfinidadElemental.FUEGO, criatura.getAfinidad());
        assertEquals(ComportamientoEmocional.INESTABLE, criatura.getComportamiento());
        assertTrue(criatura.esInestable());
    }
    
    @Test
    public void testEntrenarAumentaEnergiaAleatoriamente() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        int energiaInicial = criatura.getEnergia();
        criatura.entrenar(20);
        assertTrue(criatura.getEnergia() >= energiaInicial);
        assertTrue(criatura.getEnergia() <= 200);
    }
    
    @Test
    public void testEntrenarDesdeEnergiaBaja() {
        CriaturaSalvaje criaturaBaja = new CriaturaSalvaje("Baja", 10, AfinidadElemental.AGUA);
        criaturaBaja.entrenar(50);
        assertTrue(criaturaBaja.getEnergia() >= 10);
        assertTrue(criaturaBaja.getEnergia() <= 200);
    }
    
    @Test(expected = EnergiaDesbordadaException.class)
    public void testSetEnergiaSuperaLimiteMaximo() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        criatura.setEnergia(201);
    }
    
    @Test
    public void testPacificarCriaturaInestable() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        criatura.pacificar();
        assertEquals(ComportamientoEmocional.TRANQUILA, criatura.getComportamiento());
        assertFalse(criatura.esInestable());
    }
    
    @Test
    public void testValidarEnergiaMinima() {
        CriaturaSalvaje criaturaBaja = new CriaturaSalvaje("Débil", -10, AfinidadElemental.AGUA);
        assertEquals(0, criaturaBaja.getEnergia());
    }
    
    @Test
    public void testComportamientoEmocional() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        assertEquals(ComportamientoEmocional.INESTABLE, criatura.getComportamiento());
        assertTrue(criatura.esInestable());
        
        criatura.pacificar();
        assertEquals(ComportamientoEmocional.TRANQUILA, criatura.getComportamiento());
        assertFalse(criatura.esInestable());
    }
    
    @Test
    public void testReducirEnergiaNoBajaDeCero() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        criatura.setEnergia(-50);
        assertEquals(0, criatura.getEnergia());
    }
    
    @Test
    public void testSetEnergia() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        criatura.setEnergia(75);
        assertEquals(75, criatura.getEnergia());
    }
    
    @Test
    public void testSetEnergiaValidaMinimo() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        criatura.setEnergia(-5);
        assertEquals(0, criatura.getEnergia());
    }
    
    @Test(expected = EnergiaDesbordadaException.class)
    public void testSetEnergiaSuperaMaximo() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        criatura.setEnergia(201);
    }
    
    @Test
    public void testSetComportamiento() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        criatura.pacificar();
        criatura.setComportamiento(ComportamientoEmocional.INESTABLE);
        assertEquals(ComportamientoEmocional.INESTABLE, criatura.getComportamiento());
        assertTrue(criatura.esInestable());
    }
    
    @Test
    public void testToString() {
        CriaturaSalvaje criatura = new CriaturaSalvaje("Dragón Salvaje", 100, AfinidadElemental.FUEGO);
        String resultado = criatura.toString();
        assertTrue(resultado.contains("Dragón Salvaje"));
        assertTrue(resultado.contains("FUEGO"));
        assertTrue(resultado.contains("100"));
        assertTrue(resultado.contains("INESTABLE"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNombreNulo() {
        new CriaturaSalvaje(null, 50, AfinidadElemental.FUEGO);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNombreVacio() {
        new CriaturaSalvaje("", 50, AfinidadElemental.FUEGO);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorAfinidadNula() {
        new CriaturaSalvaje("Test", 50, null);
    }
    
    @Test
    public void testEntrenarEnergiaMaxima() {
        CriaturaSalvaje criaturaMax = new CriaturaSalvaje("Max", 200, AfinidadElemental.FUEGO);
        criaturaMax.entrenar(10);
        assertEquals(200, criaturaMax.getEnergia());
    }
}
