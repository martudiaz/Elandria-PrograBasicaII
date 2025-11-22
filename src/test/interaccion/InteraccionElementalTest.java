package interaccion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import criatura.AfinidadElemental;
import criatura.Criatura;
import criatura.CriaturaAncestral;
import criatura.CriaturaDomesticada;

public class InteraccionElementalTest {
    
    private Criatura criaturaFuego;
    private Criatura criaturaAgua;
    private Criatura criaturaAire;
    private Criatura criaturaTierra;
    
    @Before
    public void setUp() {
        criaturaFuego = new CriaturaDomesticada("Fuego", 50, AfinidadElemental.FUEGO);
        criaturaAgua = new CriaturaDomesticada("Agua", 50, AfinidadElemental.AGUA);
        criaturaAire = new CriaturaDomesticada("Aire", 50, AfinidadElemental.AIRE);
        criaturaTierra = new CriaturaDomesticada("Tierra", 50, AfinidadElemental.TIERRA);
    }
    
    @Test
    public void testInteraccionMismaAfinidad() {
        Criatura otraFuego = new CriaturaDomesticada("Otro Fuego", 50, AfinidadElemental.FUEGO);
        int energiaInicial1 = criaturaFuego.getEnergia();
        int energiaInicial2 = otraFuego.getEnergia();
        
        InteraccionElemental.interactuar(criaturaFuego, otraFuego);
        
        assertEquals(energiaInicial1 + 10, criaturaFuego.getEnergia());
        assertEquals(energiaInicial2 + 10, otraFuego.getEnergia());
    }
    
    @Test
    public void testInteraccionAfinidadesOpuestas() {
        int energiaInicialFuego = criaturaFuego.getEnergia();
        int energiaInicialAgua = criaturaAgua.getEnergia();
        
        InteraccionElemental.interactuar(criaturaFuego, criaturaAgua);
        
        assertTrue(criaturaFuego.esInestable());
        assertTrue(criaturaAgua.esInestable());
        // La energía no cambia en interacciones opuestas
        assertEquals(energiaInicialFuego, criaturaFuego.getEnergia());
        assertEquals(energiaInicialAgua, criaturaAgua.getEnergia());
    }
    
    @Test
    public void testInteraccionAireTierraOpuestas() {
        InteraccionElemental.interactuar(criaturaAire, criaturaTierra);
        
        assertTrue(criaturaAire.esInestable());
        assertTrue(criaturaTierra.esInestable());
    }
    
    @Test
    public void testInteraccionConAncestral() {
        CriaturaAncestral ancestral = new CriaturaAncestral("Ancestral", 120, AfinidadElemental.FUEGO);
        int energiaInicialAncestral = ancestral.getEnergia();
        int energiaInicialNormal = criaturaAgua.getEnergia();
        
        InteraccionElemental.interactuar(ancestral, criaturaAgua);
        
        assertEquals(energiaInicialAncestral + 20, ancestral.getEnergia());
        assertEquals(Math.max(0, energiaInicialNormal - 15), criaturaAgua.getEnergia());
    }
    
    @Test
    public void testInteraccionAncestralDomina() {
        CriaturaAncestral ancestral = new CriaturaAncestral("Ancestral", 100, AfinidadElemental.AGUA);
        Criatura normal = new CriaturaDomesticada("Normal", 20, AfinidadElemental.FUEGO);
        
        InteraccionElemental.interactuar(ancestral, normal);
        
        assertTrue(ancestral.getEnergia() >= 100 + 20);
        assertEquals(5, normal.getEnergia());
    }
}

