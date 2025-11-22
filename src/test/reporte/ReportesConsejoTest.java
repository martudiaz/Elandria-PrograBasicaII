package reporte;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import criatura.AfinidadElemental;
import criatura.Criatura;
import criatura.CriaturaDomesticada;
import maestro.MaestroElemental;
import transformacion.BendicionDelRio;
import transformacion.LlamaInterna;

public class ReportesConsejoTest {
    
    private List<MaestroElemental> maestros;
    private ReportesConsejo reportes;
    
    @Before
    public void setUp() {
        maestros = new ArrayList<>();
        
        MaestroElemental maestro1 = new MaestroElemental("Gandalf", 30, AfinidadElemental.FUEGO);
        Criatura criatura1 = new CriaturaDomesticada("Dragón", 100, AfinidadElemental.FUEGO);
        Criatura criatura2 = new CriaturaDomesticada("Salamandra", 80, AfinidadElemental.FUEGO);
        maestro1.agregarCriatura(criatura1);
        maestro1.agregarCriatura(criatura2);
        maestro1.transformarCriatura("Dragón", new BendicionDelRio());
        
        MaestroElemental maestro2 = new MaestroElemental("Merlín", 25, AfinidadElemental.AGUA);
        Criatura criatura3 = new CriaturaDomesticada("Kraken", 150, AfinidadElemental.AGUA);
        Criatura criatura4 = new CriaturaDomesticada("Sirena", 60, AfinidadElemental.AGUA);
        maestro2.agregarCriatura(criatura3);
        maestro2.agregarCriatura(criatura4);
        maestro2.transformarCriatura("Kraken", new LlamaInterna());
        maestro2.transformarCriatura("Sirena", new BendicionDelRio());
        
        maestros.add(maestro1);
        maestros.add(maestro2);
        
        reportes = new ReportesConsejo(maestros);
    }
    
    @Test
    public void testListarTodasLasCriaturas() {
        List<Criatura> todasLasCriaturas = reportes.listarTodasLasCriaturas();
        
        assertEquals(4, todasLasCriaturas.size());
    }
    
    @Test
    public void testObtenerCriaturaConMayorEnergia() {
        Criatura mayorEnergia = reportes.obtenerCriaturaConMayorEnergia();
        
        assertNotNull(mayorEnergia);
        assertTrue(mayorEnergia.getEnergia() >= 150);
    }
    
    @Test
    public void testObtenerMaestroConMasCriaturasTransformadas() {
        MaestroElemental maestroConMas = reportes.obtenerMaestroConMasCriaturasTransformadas();
        
        assertNotNull(maestroConMas);
        assertEquals("Merlín", maestroConMas.getNombre());
        assertEquals(2, maestroConMas.contarCriaturasTransformadas());
    }
    
    @Test
    public void testObtenerCantidadPorAfinidad() {
        Map<AfinidadElemental, Integer> cantidadPorAfinidad = reportes.obtenerCantidadPorAfinidad();
        
        assertNotNull(cantidadPorAfinidad);
        assertEquals(2, cantidadPorAfinidad.get(AfinidadElemental.FUEGO).intValue());
        assertEquals(2, cantidadPorAfinidad.get(AfinidadElemental.AGUA).intValue());
        assertEquals(0, cantidadPorAfinidad.get(AfinidadElemental.AIRE).intValue());
        assertEquals(0, cantidadPorAfinidad.get(AfinidadElemental.TIERRA).intValue());
    }
    
    @Test
    public void testReportesConListaVacia() {
        ReportesConsejo reportesVacios = new ReportesConsejo(new ArrayList<>());
        
        assertTrue(reportesVacios.listarTodasLasCriaturas().isEmpty());
        assertNull(reportesVacios.obtenerCriaturaConMayorEnergia());
        assertNull(reportesVacios.obtenerMaestroConMasCriaturasTransformadas());
    }
}

