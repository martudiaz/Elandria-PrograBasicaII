package transformacion;

import java.util.ArrayList;
import java.util.List;

import criatura.AfinidadElemental;
import criatura.ComportamientoEmocional;
import criatura.Criatura;
import criatura.CriaturaAncestral;


public class CriaturaTransformada extends Criatura {
    
    private Criatura criaturaBase;
    
    private List<Transformacion> transformaciones;
    private AfinidadElemental afinidadTemporal;

    public CriaturaTransformada(Criatura criaturaBase, Transformacion transformacion) {
        super(criaturaBase.getNombre(), criaturaBase.getEnergia(), criaturaBase.getAfinidad());
        
        this.criaturaBase = criaturaBase;
        this.transformaciones = new ArrayList<>();
        this.afinidadTemporal = null;
        this.comportamiento = criaturaBase.getComportamiento();
        
       if (criaturaBase instanceof CriaturaTransformada) {
            CriaturaTransformada baseTransformada = (CriaturaTransformada) criaturaBase;
            this.transformaciones.addAll(baseTransformada.getTransformaciones());
            if (baseTransformada.afinidadTemporal != null) {
                this.afinidadTemporal = baseTransformada.afinidadTemporal;
            }
        }
        
        aplicarTransformacion(transformacion);
    }

    public void aplicarTransformacion(Transformacion transformacion) {
        if (transformacion == null) {
            throw new IllegalArgumentException("La transformación no puede ser nula");
        }
        
        transformaciones.add(transformacion);
        
        Criatura criaturaBaseOriginal = getCriaturaBaseOriginal();
        int energiaCalculada = criaturaBaseOriginal.getEnergia();
        AfinidadElemental afinidadActual = criaturaBaseOriginal.getAfinidad();
        
        for (Transformacion t : transformaciones) {
            energiaCalculada = t.aplicarTransformacion(energiaCalculada, afinidadActual);
            
            if (t.afectaEstabilidad(afinidadActual)) {
                this.comportamiento = ComportamientoEmocional.INESTABLE;
            }
            
            if (t instanceof AscensoDelViento) {
                afinidadActual = ((AscensoDelViento) t).getAfinidadTemporal();
                this.afinidadTemporal = AfinidadElemental.AIRE;
            }
        }
        
        setEnergia(energiaCalculada);
    }
    
    @Override
    protected int validarEnergia(int energia) {
      if (energia < ENERGIA_MINIMA) {
            return ENERGIA_MINIMA;
        }
        if (energia > ENERGIA_MAXIMA) {
            return ENERGIA_MAXIMA;
        }
        return energia;
    }
    
    @Override
    public void entrenar(int incremento) {
       criaturaBase.entrenar(incremento);
        
       Criatura criaturaBaseOriginal = getCriaturaBaseOriginal();
        int energiaCalculada = criaturaBaseOriginal.getEnergia();
        AfinidadElemental afinidadActual = criaturaBaseOriginal.getAfinidad();
        
        for (Transformacion t : transformaciones) {
            energiaCalculada = t.aplicarTransformacion(energiaCalculada, afinidadActual);
            
            if (t instanceof AscensoDelViento) {
                afinidadActual = ((AscensoDelViento) t).getAfinidadTemporal();
            }
        }
        
        this.energia = energiaCalculada;
        this.comportamiento = criaturaBase.getComportamiento();
    }
    
    @Override
    public void pacificar() {
        criaturaBase.pacificar();
    }
    
    @Override
    public AfinidadElemental getAfinidad() {
        if (afinidadTemporal != null) {
            return afinidadTemporal;
        }
        return criaturaBase.getAfinidad();
    }
    
    public AfinidadElemental getAfinidadOriginal() {
        return criaturaBase.getAfinidad();
    }
    
    public Criatura getCriaturaBase() {
        return criaturaBase;
    }
    
    public Criatura getCriaturaBaseOriginal() {
        if (criaturaBase instanceof CriaturaTransformada) {
            return ((CriaturaTransformada) criaturaBase).getCriaturaBaseOriginal();
        }
        return criaturaBase;
    }

    public List<Transformacion> getTransformaciones() {
        return new ArrayList<>(transformaciones);
    }

    public boolean tieneTransformacion(String nombreTransformacion) {
        return transformaciones.stream()
            .anyMatch(t -> t.getNombre().equals(nombreTransformacion));
    }
    

    public boolean esAncestral() {
        return criaturaBase instanceof CriaturaAncestral;
    }
    
    @Override
    public String toString() {
        String resultado = nombre + " [" + getAfinidad() + "] - Energía: " + this.energia + " - " + comportamiento + " [Transformaciones: ";
        for (int i = 0; i < transformaciones.size(); i++) {
            if (i > 0) resultado += ", ";
            resultado += transformaciones.get(i).getNombre();
        }
        resultado += "]";
        return resultado;
    }
}

