package reporte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import criatura.AfinidadElemental;
import criatura.Criatura;
import maestro.MaestroElemental;


public class ReportesConsejo {
    
    private List<MaestroElemental> maestros;
    
    public ReportesConsejo(List<MaestroElemental> maestros) {
        if (maestros == null) {
            throw new IllegalArgumentException("La lista de maestros no puede ser nula");
        }
        this.maestros = new ArrayList<>(maestros);
    }
    
    public List<Criatura> listarTodasLasCriaturas() {
        List<Criatura> todasLasCriaturas = new ArrayList<>();
        
        for (MaestroElemental maestro : maestros) {
            todasLasCriaturas.addAll(maestro.getCriaturas().values());
        }
        
        return todasLasCriaturas;
    }
    

    public Criatura obtenerCriaturaConMayorEnergia() {
        List<Criatura> todasLasCriaturas = listarTodasLasCriaturas();
        
        if (todasLasCriaturas.isEmpty()) {
            return null;
        }
        
        Criatura criaturaConMayorEnergia = todasLasCriaturas.get(0);
        int mayorEnergia = criaturaConMayorEnergia.getEnergia();
        
        for (Criatura criatura : todasLasCriaturas) {
            if (criatura.getEnergia() > mayorEnergia) {
                mayorEnergia = criatura.getEnergia();
                criaturaConMayorEnergia = criatura;
            }
        }
        
        return criaturaConMayorEnergia;
    }
    
    public MaestroElemental obtenerMaestroConMasCriaturasTransformadas() {
        if (maestros.isEmpty()) {
            return null;
        }
        
        MaestroElemental maestroConMasTransformadas = maestros.get(0);
        int maxTransformadas = maestroConMasTransformadas.contarCriaturasTransformadas();
        
        int cantidadTransformadas;
        for (MaestroElemental maestro : maestros) {
            cantidadTransformadas = maestro.contarCriaturasTransformadas();
            if (cantidadTransformadas > maxTransformadas) {
                maxTransformadas = cantidadTransformadas;
                maestroConMasTransformadas = maestro;
            }
        }
        
        return maestroConMasTransformadas;
    }
    

    public Map<AfinidadElemental, Integer> obtenerCantidadPorAfinidad() {
        Map<AfinidadElemental, Integer> cantidadPorAfinidad = new HashMap<>();
        
        List<Criatura> todasLasCriaturas = listarTodasLasCriaturas();
        
        AfinidadElemental afinidad;
        int cantAgua = 0;
        int cantFuego = 0;
        int cantAire = 0;
        int cantTierra = 0;
        for (Criatura criatura : todasLasCriaturas) {
            afinidad = criatura.getAfinidad();
            switch (afinidad) {
                case AGUA:
                    cantAgua++;
                    break;
                case FUEGO:
                    cantFuego++;
                    break;
                case AIRE:
                    cantAire++;
                    break;
                case TIERRA:
                    cantTierra++;
                    break;
                default:
                    break;
            }
        }


        cantidadPorAfinidad.put(AfinidadElemental.AGUA, cantAgua);
        cantidadPorAfinidad.put(AfinidadElemental.FUEGO, cantFuego);
        cantidadPorAfinidad.put(AfinidadElemental.AIRE, cantAire);
        cantidadPorAfinidad.put(AfinidadElemental.TIERRA, cantTierra);
        
        return cantidadPorAfinidad;
    }
}

