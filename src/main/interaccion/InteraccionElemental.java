package interaccion;

import criatura.AfinidadElemental;
import criatura.ComportamientoEmocional;
import criatura.Criatura;
import criatura.CriaturaAncestral;

public class InteraccionElemental {
    
    private static final int ENERGIA_GANADA_AFINIDAD = 10;
    private static final int ENERGIA_GANADA_ANCESTRAL = 20;
    private static final int ENERGIA_PERDIDA_ANCESTRAL = 15;
    
    public static void interactuar(Criatura criatura1, Criatura criatura2) {
        if (criatura1 == null || criatura2 == null) {
            throw new IllegalArgumentException("Las criaturas no pueden ser nulas");
        }
        
        if (criatura1 instanceof CriaturaAncestral || criatura2 instanceof CriaturaAncestral) {
            aplicarInteraccionAncestral(criatura1, criatura2);
            return;
        }
        
        AfinidadElemental afinidad1 = criatura1.getAfinidad();
        AfinidadElemental afinidad2 = criatura2.getAfinidad();
        
        if (afinidad1.equals(afinidad2)) {
            aplicarGananciaEnergia(criatura1, ENERGIA_GANADA_AFINIDAD);
            aplicarGananciaEnergia(criatura2, ENERGIA_GANADA_AFINIDAD);
            return;
        }

        if(sonAfinidadesOpuestas(afinidad1, afinidad2)) {
            desestabilizar(criatura1);
            desestabilizar(criatura2);
            return;
        }
    }
    
    private static void aplicarInteraccionAncestral(Criatura criatura1, Criatura criatura2) {
        Criatura ancestral;
        Criatura otra;
        
        if (criatura1 instanceof CriaturaAncestral) {
            ancestral = criatura1;
            otra = criatura2;
        } else {
            ancestral = criatura2;
            otra = criatura1;
        }
        
        aplicarGananciaEnergia(ancestral, ENERGIA_GANADA_ANCESTRAL);
    
        aplicarPerdidaEnergia(otra, ENERGIA_PERDIDA_ANCESTRAL);
    }
    
    private static void aplicarGananciaEnergia(Criatura criatura, int cantidad) {
        criatura.setEnergia(criatura.getEnergia() + cantidad);
    }
    
    private static void aplicarPerdidaEnergia(Criatura criatura, int cantidad) {
        criatura.setEnergia(criatura.getEnergia() - cantidad);
    }

    private static boolean sonAfinidadesOpuestas(AfinidadElemental afinidad1, AfinidadElemental afinidad2) {
        return (afinidad1.equals(AfinidadElemental.AGUA) && afinidad2.equals(AfinidadElemental.FUEGO)) ||
               (afinidad1.equals(AfinidadElemental.FUEGO) && afinidad2.equals(AfinidadElemental.AGUA)) ||
               (afinidad1.equals(AfinidadElemental.AIRE) && afinidad2.equals(AfinidadElemental.TIERRA)) ||
               (afinidad1.equals(AfinidadElemental.TIERRA) && afinidad2.equals(AfinidadElemental.AIRE));
    }

    private static void desestabilizar(Criatura criatura) {
        criatura.setComportamiento(ComportamientoEmocional.INESTABLE);
    }
}

