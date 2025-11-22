package criatura;

import static org.junit.Assert.*;

import org.junit.Test;

public class CriaturaAncestralTest {
    
    @Test
    public void testCreacionCriaturaAncestral() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        assertNotNull(criatura);
        assertEquals("Dragón Ancestral", criatura.getNombre());
        assertEquals(150, criatura.getEnergia());
        assertEquals(AfinidadElemental.TIERRA, criatura.getAfinidad());
        assertEquals(ComportamientoEmocional.TRANQUILA, criatura.getComportamiento());
        assertFalse(criatura.esInestable());
    }
    
    @Test
    public void testEnergiaNuncaBajaDe100() {
        CriaturaAncestral criaturaBaja = new CriaturaAncestral("Ancestral Débil", 50, AfinidadElemental.AGUA);
        assertEquals(100, criaturaBaja.getEnergia());
        
        criaturaBaja.setEnergia(10);
        assertEquals(100, criaturaBaja.getEnergia());
    }
    
    @Test
    public void testEntrenamientoNormalNoLaVuelveInestable() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        criatura.entrenar(20);
        assertFalse(criatura.esInestable());
        assertEquals(ComportamientoEmocional.TRANQUILA, criatura.getComportamiento());
    }
    
    @Test
    public void testEntrenamientoExtremoLaVuelveInestable() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        criatura.entrenar(35);
        assertTrue(criatura.esInestable());
        assertEquals(ComportamientoEmocional.INESTABLE, criatura.getComportamiento());
    }
    
    @Test
    public void testEntrenarAumentaEnergia() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        int energiaInicial = criatura.getEnergia();
        criatura.entrenar(25);
        assertEquals(energiaInicial + 25, criatura.getEnergia());
    }
    
    @Test
    public void testPacificar() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        criatura.entrenar(35);
        criatura.pacificar();
        assertFalse(criatura.esInestable());
        assertEquals(ComportamientoEmocional.TRANQUILA, criatura.getComportamiento());
    }
    
    @Test
    public void testSetEnergia() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        criatura.setEnergia(120);
        assertEquals(120, criatura.getEnergia());
    }
    
    @Test
    public void testSetEnergiaValidaLimites() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        criatura.setEnergia(50);
        assertEquals(100, criatura.getEnergia());
        
        criatura.setEnergia(250);
        assertEquals(200, criatura.getEnergia());
    }
    
    @Test
    public void testSetComportamiento() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        criatura.setComportamiento(ComportamientoEmocional.INESTABLE);
        assertEquals(ComportamientoEmocional.INESTABLE, criatura.getComportamiento());
        assertTrue(criatura.esInestable());
    }
    
    @Test
    public void testToString() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        String resultado = criatura.toString();
        assertTrue(resultado.contains("Dragón Ancestral"));
        assertTrue(resultado.contains("TIERRA"));
        assertTrue(resultado.contains("150"));
        assertTrue(resultado.contains("TRANQUILA"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNombreNulo() {
        new CriaturaAncestral(null, 150, AfinidadElemental.TIERRA);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNombreVacio() {
        new CriaturaAncestral("", 150, AfinidadElemental.TIERRA);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorAfinidadNula() {
        new CriaturaAncestral("Test", 150, null);
    }
    
    @Test
    public void testDiferentesAfinidades() {
        CriaturaAncestral agua = new CriaturaAncestral("Agua", 150, AfinidadElemental.AGUA);
        CriaturaAncestral fuego = new CriaturaAncestral("Fuego", 150, AfinidadElemental.FUEGO);
        CriaturaAncestral aire = new CriaturaAncestral("Aire", 150, AfinidadElemental.AIRE);
        CriaturaAncestral tierra = new CriaturaAncestral("Tierra", 150, AfinidadElemental.TIERRA);
        
        assertEquals(AfinidadElemental.AGUA, agua.getAfinidad());
        assertEquals(AfinidadElemental.FUEGO, fuego.getAfinidad());
        assertEquals(AfinidadElemental.AIRE, aire.getAfinidad());
        assertEquals(AfinidadElemental.TIERRA, tierra.getAfinidad());
    }
    
    @Test
    public void testEnergiaMaxima() {
        CriaturaAncestral criaturaMax = new CriaturaAncestral("Max", 200, AfinidadElemental.FUEGO);
        assertEquals(200, criaturaMax.getEnergia());
        
        criaturaMax.entrenar(10);
        assertEquals(200, criaturaMax.getEnergia());
    }
    
    @Test
    public void testEntrenarConValoresGrandes() {
        CriaturaAncestral criatura = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        criatura.entrenar(100);
        assertEquals(200, criatura.getEnergia());
        assertTrue(criatura.esInestable());
    }
}
