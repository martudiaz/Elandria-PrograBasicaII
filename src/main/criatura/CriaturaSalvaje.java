package criatura;
import excepcion.EnergiaDesbordadaException;

public class CriaturaSalvaje extends Criatura {
    
    public CriaturaSalvaje(String nombre, int energia, AfinidadElemental afinidad) {
        super(nombre, energia, afinidad);
        this.comportamiento = ComportamientoEmocional.INESTABLE;
    }
    
    @Override
    protected int validarEnergia(int energia) {
        if (energia < ENERGIA_MINIMA) {
            return ENERGIA_MINIMA;
        }
        if (energia > ENERGIA_MAXIMA) {
            throw new EnergiaDesbordadaException(
                "La criatura salvaje " + nombre + " ha superado el límite máximo de energía (" + ENERGIA_MAXIMA + ")"
            );
        }
        return energia;
    }
    
    @Override
    public void entrenar(int incrementoEnergia) {
        int energiaActual = this.energia;
        int nuevaEnergia = energiaActual + (int) (Math.random() * (ENERGIA_MAXIMA - energiaActual + 1));

        setEnergia(nuevaEnergia);
        
    }
}

