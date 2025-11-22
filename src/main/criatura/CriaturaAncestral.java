package criatura;
public class CriaturaAncestral extends Criatura {
    private static final int ENERGIA_ENTRENAMIENTO_EXTREMO = 30;
    private static final int ENERGIA_MINIMA_ANCESTRAL = 100;
    
    public CriaturaAncestral(String nombre, int energia, AfinidadElemental afinidad) {
        super(nombre, energia, afinidad);
    }
    
    @Override
    protected int validarEnergia(int energia) {
        if (energia < ENERGIA_MINIMA_ANCESTRAL) {
            return ENERGIA_MINIMA_ANCESTRAL;
        }
        if (energia > ENERGIA_MAXIMA) {
            return ENERGIA_MAXIMA;
        }
        return energia;
    }
    
    @Override
    public void entrenar(int incrementoEnergia) {
        if (incrementoEnergia > ENERGIA_ENTRENAMIENTO_EXTREMO) {
            this.comportamiento = ComportamientoEmocional.INESTABLE;
        }
        
        int nuevaEnergia = this.energia + incrementoEnergia;
        setEnergia(nuevaEnergia);
    }
}

