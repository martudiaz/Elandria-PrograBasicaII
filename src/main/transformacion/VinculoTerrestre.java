package transformacion;

import criatura.AfinidadElemental;

public class VinculoTerrestre implements Transformacion {
    
    private static final int ENERGIA_MINIMA_VINCULO = 50;
    
    @Override
    public int aplicarTransformacion(int energiaBase, AfinidadElemental afinidad) {
        return Math.max(energiaBase, ENERGIA_MINIMA_VINCULO);
    }
    
    @Override
    public boolean afectaEstabilidad(AfinidadElemental afinidad) {
        return false;
    }
    
    @Override
    public String getNombre() {
        return "Vínculo Terrestre";
    }
}

