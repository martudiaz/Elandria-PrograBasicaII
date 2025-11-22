package criatura;

import static org.junit.Assert.*;

import org.junit.Test;

public class CriaturaDomesticadaTest {
    
    @Test
    public void testCreacionCriaturaDomesticada() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        assertNotNull(criatura);
        assertEquals("Perro Fuego", criatura.getNombre());
        assertEquals(50, criatura.getEnergia());
        assertEquals(AfinidadElemental.FUEGO, criatura.getAfinidad());
        assertEquals(ComportamientoEmocional.TRANQUILA, criatura.getComportamiento());
        assertFalse(criatura.esInestable());
    }
    
    @Test
    public void testEntrenarAumentaEnergiaFija() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        int energiaInicial = criatura.getEnergia();
        criatura.entrenar(100);
        assertEquals(energiaInicial + 10, criatura.getEnergia());
    }
    
    @Test
    public void testEntrenarNoSuperaLimite() {
        CriaturaDomesticada criaturaAlta = new CriaturaDomesticada("Fuerte", 195, AfinidadElemental.AIRE);
        criaturaAlta.entrenar(100);
        assertEquals(200, criaturaAlta.getEnergia());
    }
    
    @Test
    public void testNuncaSeVuelveInestable() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        criatura.entrenar(100);
        criatura.entrenar(100);
        assertFalse(criatura.esInestable());
        assertEquals(ComportamientoEmocional.TRANQUILA, criatura.getComportamiento());
    }
    
    @Test
    public void testValidarEnergiaMinima() {
        CriaturaDomesticada criaturaBaja = new CriaturaDomesticada("Débil", -10, AfinidadElemental.AGUA);
        assertEquals(0, criaturaBaja.getEnergia());
    }
    
    @Test
    public void testValidarEnergiaMaxima() {
        CriaturaDomesticada criaturaAlta = new CriaturaDomesticada("Alta", 250, AfinidadElemental.TIERRA);
        assertEquals(200, criaturaAlta.getEnergia());
    }
    
    @Test
    public void testPacificar() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        criatura.setComportamiento(ComportamientoEmocional.INESTABLE);
        criatura.pacificar();
        assertEquals(ComportamientoEmocional.TRANQUILA, criatura.getComportamiento());
        assertFalse(criatura.esInestable());
    }
    
    
    @Test
    public void testReducirEnergiaNoBajaDeCero() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        criatura.setEnergia(-10);
        assertEquals(0, criatura.getEnergia());
    }
    
    @Test
    public void testSetEnergia() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        criatura.setEnergia(75);
        assertEquals(75, criatura.getEnergia());
    }
    
    @Test
    public void testSetEnergiaValidaLimites() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        criatura.setEnergia(-5);
        assertEquals(0, criatura.getEnergia());
        
        criatura.setEnergia(250);
        assertEquals(200, criatura.getEnergia());
    }
    
    @Test
    public void testSetComportamiento() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        criatura.setComportamiento(ComportamientoEmocional.INESTABLE);
        assertEquals(ComportamientoEmocional.INESTABLE, criatura.getComportamiento());
        assertTrue(criatura.esInestable());
    }
    
    @Test
    public void testToString() {
        CriaturaDomesticada criatura = new CriaturaDomesticada("Perro Fuego", 50, AfinidadElemental.FUEGO);
        String resultado = criatura.toString();
        assertTrue(resultado.contains("Perro Fuego"));
        assertTrue(resultado.contains("FUEGO"));
        assertTrue(resultado.contains("50"));
        assertTrue(resultado.contains("TRANQUILA"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNombreNulo() {
        new CriaturaDomesticada(null, 50, AfinidadElemental.FUEGO);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNombreVacio() {
        new CriaturaDomesticada("", 50, AfinidadElemental.FUEGO);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNombreSoloEspacios() {
        new CriaturaDomesticada("   ", 50, AfinidadElemental.FUEGO);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorAfinidadNula() {
        new CriaturaDomesticada("Test", 50, null);
    }
   
}
