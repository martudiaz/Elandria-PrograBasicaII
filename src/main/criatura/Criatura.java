package criatura;
public abstract class Criatura {
    
    protected String nombre;
    protected int energia;
    protected AfinidadElemental afinidad;
    protected ComportamientoEmocional comportamiento;
    
    protected static final int ENERGIA_MINIMA = 0;
    protected static final int ENERGIA_MAXIMA = 200;
    
    public Criatura(String nombre, int energia, AfinidadElemental afinidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (afinidad == null) {
            throw new IllegalArgumentException("La afinidad no puede ser nula");
        }
        
        this.nombre = nombre;
        this.afinidad = afinidad;
        this.comportamiento = ComportamientoEmocional.TRANQUILA;
        this.energia = validarEnergia(energia);
    }
    
    protected abstract int validarEnergia(int energia);
    
    public abstract void entrenar(int incrementoEnergia);
    
  
    public void pacificar() {
        this.comportamiento = ComportamientoEmocional.TRANQUILA;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getEnergia() {
        return energia;
    }
    
    public void setEnergia(int energia) {
        this.energia = validarEnergia(energia);
    }
    
    public AfinidadElemental getAfinidad() {
        return afinidad;
    }
    
    public ComportamientoEmocional getComportamiento() {
        return comportamiento;
    }
    
    public boolean esInestable() {
        return comportamiento == ComportamientoEmocional.INESTABLE;
    }
    
    public void setComportamiento(ComportamientoEmocional comportamiento) {
        this.comportamiento = comportamiento;
    }
       
    @Override
    public String toString() {
        return nombre + " [" + afinidad + "] - Energía: " + energia + " - " + comportamiento;
    }
}

