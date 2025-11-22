package criatura;
public class CriaturaDomesticada extends Criatura {
    private static final int INCREMENTO_DOMESTICADA = 10;
    
    public CriaturaDomesticada(String nombre, int energia, AfinidadElemental afinidad) {
        super(nombre, energia, afinidad);
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
    public void entrenar(int incrementoEnergia) {
        int nuevaEnergia = this.energia + INCREMENTO_DOMESTICADA;
        setEnergia(nuevaEnergia);
    }
}

