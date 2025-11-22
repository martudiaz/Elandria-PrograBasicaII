package transformacion;

import criatura.AfinidadElemental;

public class BendicionDelRio implements Transformacion {
    
    private static final int LIMITE_BENDICION = 180;
    
    @Override
    public int aplicarTransformacion(int energiaBase, AfinidadElemental afinidad) {
        int energiaDuplicada = energiaBase * 2;
        return Math.min(energiaDuplicada, LIMITE_BENDICION);
    }
    
    @Override
    public boolean afectaEstabilidad(AfinidadElemental afinidad) {
        return false;
    }
    
    @Override
    public String getNombre() {
        return "Bendición del Río";
    }
}

