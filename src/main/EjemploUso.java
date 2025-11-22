
import java.util.ArrayList;
import java.util.List;

import criatura.AfinidadElemental;
import criatura.Criatura;
import criatura.CriaturaAncestral;
import criatura.CriaturaDomesticada;
import criatura.CriaturaSalvaje;
import excepcion.MaestriaInsuficienteException;
import interaccion.InteraccionElemental;
import maestro.MaestroElemental;
import reporte.ReportesConsejo;
import transformacion.BendicionDelRio;
import transformacion.LlamaInterna;
import transformacion.VinculoTerrestre;

public class EjemploUso {
    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestión de Criaturas Elementales ===\n");
        
        MaestroElemental maestro1 = new MaestroElemental("Gandalf", 30, AfinidadElemental.FUEGO);
        MaestroElemental maestro2 = new MaestroElemental("Merlín", 25, AfinidadElemental.AGUA);
        
        Criatura dragon = new CriaturaDomesticada("Dragón de Fuego", 80, AfinidadElemental.FUEGO);
        Criatura salamandra = new CriaturaSalvaje("Salamandra Salvaje", 60, AfinidadElemental.FUEGO);
        Criatura kraken = new CriaturaDomesticada("Kraken", 100, AfinidadElemental.AGUA);
        Criatura ancestral = new CriaturaAncestral("Dragón Ancestral", 150, AfinidadElemental.TIERRA);
        
        maestro1.agregarCriatura(dragon);
        maestro1.agregarCriatura(salamandra);
        maestro2.agregarCriatura(kraken);
        maestro2.agregarCriatura(ancestral);
        
        System.out.println("Criaturas creadas:");
        System.out.println(dragon);
        System.out.println(salamandra);
        System.out.println(kraken);
        System.out.println(ancestral);
        System.out.println();
        
        try {
            System.out.println("Entrenando criaturas...");
            maestro1.entrenarCriatura("Dragón de Fuego", 20);
            maestro1.entrenarCriatura("Salamandra Salvaje", 15);
            maestro2.entrenarCriatura("Kraken", 25);
            
            System.out.println("Después del entrenamiento:");
            System.out.println(maestro1.getCriatura("Dragón de Fuego"));
            System.out.println(maestro1.getCriatura("Salamandra Salvaje"));
            System.out.println(maestro2.getCriatura("Kraken"));
            System.out.println();
        } catch (MaestriaInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Aplicando transformaciones");
        maestro1.transformarCriatura("Dragón de Fuego", new BendicionDelRio());
        maestro2.transformarCriatura("Kraken", new LlamaInterna());
        maestro2.transformarCriatura("Kraken", new VinculoTerrestre());
        
        System.out.println("Criaturas transformadas:");
        System.out.println(maestro1.getCriatura("Dragón de Fuego"));
        System.out.println(maestro2.getCriatura("Kraken"));
        System.out.println();
        
        System.out.println("Interacciones entre criaturas:");
        Criatura criatura1 = maestro1.getCriatura("Dragón de Fuego");
        Criatura criatura2 = maestro2.getCriatura("Kraken");
        
        System.out.println("Antes de la interacción:");
        System.out.println("  " + criatura1);
        System.out.println("  " + criatura2);
        
        InteraccionElemental.interactuar(criatura1, criatura2);
        
        System.out.println("Después de la interacción:");
        System.out.println("  " + criatura1);
        System.out.println("  " + criatura2);
        System.out.println();
        
        System.out.println("=== Reportes del Consejo ===");
        List<MaestroElemental> todosLosMaestros = new ArrayList<>();
        todosLosMaestros.add(maestro1);
        todosLosMaestros.add(maestro2);
        
        ReportesConsejo reportes = new ReportesConsejo(todosLosMaestros);
        
        System.out.println("\n1. Todas las criaturas:");
        reportes.listarTodasLasCriaturas().forEach(c -> System.out.println("  - " + c.getNombre() + " (" + c.getEnergia() + " energía)"));
        
        System.out.println("\n2. Criatura con mayor energía:");
        Criatura mayorEnergia = reportes.obtenerCriaturaConMayorEnergia();
        if (mayorEnergia != null) {
            System.out.println("  " + mayorEnergia.getNombre() + " con " + mayorEnergia.getEnergia() + " energía");
        }
        
        System.out.println("\n3. Maestro con más criaturas transformadas:");
        MaestroElemental maestroMasTransformadas = reportes.obtenerMaestroConMasCriaturasTransformadas();
        if (maestroMasTransformadas != null) {
            System.out.println("  " + maestroMasTransformadas.getNombre() + " con " + 
                maestroMasTransformadas.contarCriaturasTransformadas() + " criaturas transformadas");
        }
        
        System.out.println("\n4. Cantidad de criaturas por afinidad:");
        reportes.obtenerCantidadPorAfinidad().forEach((afinidad, cantidad) -> 
            System.out.println("  " + afinidad + ": " + cantidad));
        
        System.out.println("\n=== Fin del ejemplo ===");
    }
}

