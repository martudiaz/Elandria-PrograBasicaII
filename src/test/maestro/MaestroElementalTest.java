package maestro;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import criatura.AfinidadElemental;
import criatura.ComportamientoEmocional;
import criatura.Criatura;
import criatura.CriaturaDomesticada;
import excepcion.MaestriaInsuficienteException;
import transformacion.BendicionDelRio;
import transformacion.CriaturaTransformada;

public class MaestroElementalTest {
    
    private MaestroElemental maestro;
    private Criatura criatura;
    
    @Before
    public void setUp() {
        maestro = new MaestroElemental("Gandalf", 25, AfinidadElemental.FUEGO);
        criatura = new CriaturaDomesticada("Dragón", 50, AfinidadElemental.FUEGO);
    }
    
    @Test
    public void testCreacionMaestro() {
        assertNotNull(maestro);
        assertEquals("Gandalf", maestro.getNombre());
        assertEquals(25, maestro.getNivelMaestria());
        assertEquals(AfinidadElemental.FUEGO, maestro.getAfinidadPrincipal());
        assertTrue(maestro.getCriaturas().isEmpty());
    }
    
    @Test
    public void testAgregarCriatura() {
        maestro.agregarCriatura(criatura);
        
        Map<String, Criatura> criaturas = maestro.getCriaturas();
        assertEquals(1, criaturas.size());
        assertTrue(criaturas.containsKey("Dragón"));
    }
    
    @Test
    public void testEntrenarCriaturaConMaestriaSuficiente() throws MaestriaInsuficienteException {
        maestro.agregarCriatura(criatura);
        int energiaInicial = criatura.getEnergia();
        
        maestro.entrenarCriatura("Dragón", 20);
        
        assertTrue(criatura.getEnergia() > energiaInicial);
    }
    
    @Test(expected = MaestriaInsuficienteException.class)
    public void testEntrenarCriaturaSinMaestriaSuficiente() throws MaestriaInsuficienteException {
        MaestroElemental maestroDebil = new MaestroElemental("Novato", 5, AfinidadElemental.AGUA);
        Criatura criaturaAlta = new CriaturaDomesticada("Titan", 150, AfinidadElemental.AGUA);
        
        maestroDebil.agregarCriatura(criaturaAlta);
        maestroDebil.entrenarCriatura("Titan", 10);
    }
    
    @Test
    public void testPacificarCriatura() {
        Criatura criaturaInestable = new CriaturaDomesticada("Inestable", 50, AfinidadElemental.FUEGO);
        criaturaInestable.setComportamiento(ComportamientoEmocional.INESTABLE);
        
        maestro.agregarCriatura(criaturaInestable);
        maestro.pacificarCriatura("Inestable");
        
        assertFalse(criaturaInestable.esInestable());
    }

    @Test
    public void testTransformarCriatura() {
        maestro.agregarCriatura(criatura);
        maestro.transformarCriatura("Dragón", new BendicionDelRio());
        
        Criatura criaturaTransformada = maestro.getCriatura("Dragón");
        assertTrue(criaturaTransformada instanceof CriaturaTransformada);
    }
}

