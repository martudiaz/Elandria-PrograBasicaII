package transformacion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import criatura.AfinidadElemental;
import criatura.Criatura;
import criatura.CriaturaDomesticada;

public class TransformacionesTest {
    
    private Criatura criatura;
    
    @Before
    public void setUp() {
        criatura = new CriaturaDomesticada("Test", 50, AfinidadElemental.AGUA);
    }
    
    @Test
    public void testBendicionDelRio() {
        Transformacion bendicion = new BendicionDelRio();
        CriaturaTransformada transformada = new CriaturaTransformada(criatura, bendicion);
        
        assertEquals(100, transformada.getEnergia()); 
        assertTrue(transformada.tieneTransformacion("Bendición del Río"));
    }
    
    @Test
    public void testBendicionDelRioNoSupera180() {
        Criatura criaturaAlta = new CriaturaDomesticada("Alta", 100, AfinidadElemental.FUEGO);
        Transformacion bendicion = new BendicionDelRio();
        CriaturaTransformada transformada = new CriaturaTransformada(criaturaAlta, bendicion);
        
        assertEquals(180, transformada.getEnergia()); 
    }
    
    @Test
    public void testLlamaInternaConFuego() {
        Criatura criaturaFuego = new CriaturaDomesticada("Fuego", 50, AfinidadElemental.FUEGO);
        Transformacion llama = new LlamaInterna();
        CriaturaTransformada transformada = new CriaturaTransformada(criaturaFuego, llama);
        
        assertEquals(80, transformada.getEnergia());
        assertFalse(transformada.esInestable());
    }
    
    @Test
    public void testLlamaInternaSinFuego() {
        Transformacion llama = new LlamaInterna();
        CriaturaTransformada transformada = new CriaturaTransformada(criatura, llama);
        
        assertEquals(50, transformada.getEnergia());
        assertTrue(transformada.esInestable()); 
    }
    
    @Test
    public void testVinculoTerrestre() {
        Criatura criaturaBaja = new CriaturaDomesticada("Baja", 20, AfinidadElemental.TIERRA);
        Transformacion vinculo = new VinculoTerrestre();
        CriaturaTransformada transformada = new CriaturaTransformada(criaturaBaja, vinculo);
        
        assertEquals(50, transformada.getEnergia()); 
    }
    
    @Test
    public void testAscensoDelViento() {
        Transformacion ascenso = new AscensoDelViento();
        CriaturaTransformada transformada = new CriaturaTransformada(criatura, ascenso);
        
        assertEquals(AfinidadElemental.AIRE, transformada.getAfinidad());
        assertEquals(AfinidadElemental.AGUA, transformada.getAfinidadOriginal());
    }
    
    @Test
    public void testMultiplesTransformaciones() {
        CriaturaTransformada transformada = new CriaturaTransformada(criatura, new BendicionDelRio());
        transformada.aplicarTransformacion(new VinculoTerrestre());
        
        assertEquals(2, transformada.getTransformaciones().size());
        assertTrue(transformada.tieneTransformacion("Bendición del Río"));
        assertTrue(transformada.tieneTransformacion("Vínculo Terrestre"));
    }
}

