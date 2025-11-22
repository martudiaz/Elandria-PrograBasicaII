package transformacion;

import criatura.AfinidadElemental;

public class AscensoDelViento implements Transformacion {
    
    @Override
    public int aplicarTransformacion(int energiaBase, AfinidadElemental afinidad) {
        return energiaBase;
    }
    
    @Override
    public boolean afectaEstabilidad(AfinidadElemental afinidad) {
        return false;
    }
    
    @Override
    public String getNombre() {
        return "Ascenso del Viento";
    }
    
    public AfinidadElemental getAfinidadTemporal() {
        return AfinidadElemental.AIRE;
    }
}

