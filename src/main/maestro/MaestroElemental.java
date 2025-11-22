package maestro;

import java.util.HashMap;
import java.util.Map;

import criatura.AfinidadElemental;
import criatura.Criatura;
import excepcion.MaestriaInsuficienteException;
import transformacion.CriaturaTransformada;
import transformacion.Transformacion;

public class MaestroElemental {
    private String nombre;
    private int nivelMaestria;
    private AfinidadElemental afinidadPrincipal;
    private HashMap<String, Criatura> criaturas;
    
    private static final int MAESTRIA_MINIMA = 1;
    private static final int MAESTRIA_MAXIMA = 50;
    
    public MaestroElemental(String nombre, int nivelMaestria, AfinidadElemental afinidadPrincipal) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (afinidadPrincipal == null) {
            throw new IllegalArgumentException("La afinidad principal no puede ser nula");
        }
        if (nivelMaestria < MAESTRIA_MINIMA || nivelMaestria > MAESTRIA_MAXIMA) {
            throw new IllegalArgumentException(
                "El nivel de maestría debe estar entre " + MAESTRIA_MINIMA + " y " + MAESTRIA_MAXIMA);
        }
        
        this.nombre = nombre;
        this.nivelMaestria = nivelMaestria;
        this.afinidadPrincipal = afinidadPrincipal;
        this.criaturas = new HashMap<>();
    }
    
    public void agregarCriatura(Criatura criatura) {
        if (criatura == null) {
            throw new IllegalArgumentException("La criatura no puede ser nula");
        }
        criaturas.put(criatura.getNombre(), criatura);
    }
    
    public void entrenarCriatura(String nombreCriatura, int incremento) throws MaestriaInsuficienteException {
        Criatura criatura = criaturas.get(nombreCriatura);
        
        if (criatura == null) {
            throw new IllegalArgumentException("La criatura no está a cargo de este maestro");
        }
        
        int maestriaRequerida = criatura.getEnergia() / 4;
        
        if (nivelMaestria < maestriaRequerida) {
            throw new MaestriaInsuficienteException(
                "El maestro " + nombre + " necesita nivel de maestría " + maestriaRequerida + " para entrenar a "
                + nombreCriatura + " (tiene " + nivelMaestria + ")");
        }
        
        int incrementoAjustado = incremento;
        if (criatura.getAfinidad().equals(afinidadPrincipal)) {
            incrementoAjustado = (int) (incremento * 1.2); // 20% más si coincide la afinidad
        }
        
        criatura.entrenar(incrementoAjustado);
    }
    
    public void pacificarCriatura(String nombreCriatura) {
        Criatura criatura = criaturas.get(nombreCriatura);
        
        if (criatura == null) {
            throw new IllegalArgumentException("La criatura no está a cargo de este maestro");
        }
        
        criatura.pacificar();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getNivelMaestria() {
        return nivelMaestria;
    }
    
    public AfinidadElemental getAfinidadPrincipal() {
        return afinidadPrincipal;
    }
    
    public Map<String, Criatura> getCriaturas() {
        return new HashMap<>(criaturas);
    }

    public Criatura getCriatura(String nombre) {
        return criaturas.get(nombre);
    }

     public void transformarCriatura(String nombreCriatura, Transformacion transformacion) {
        Criatura criatura = criaturas.get(nombreCriatura);
        
        if (criatura == null) {
            throw new IllegalArgumentException("La criatura no está a cargo de este maestro");
        }
        
        if (transformacion == null) {
            throw new IllegalArgumentException("La transformación no puede ser nula");
        }

        CriaturaTransformada criaturaTransformada = 
            new CriaturaTransformada(criatura, transformacion);
        
        criaturas.put(nombreCriatura, criaturaTransformada);
    }
     
     public int contarCriaturasTransformadas() {
    	 int cant = 0;
    	 for (Criatura criatura : criaturas.values()) {
    		 if(criatura instanceof CriaturaTransformada) {
    			 cant++;
    		 }
    	 }
    	 
    	 return cant;
     }
}

