package transformacion;

import criatura.AfinidadElemental;

public class LlamaInterna implements Transformacion {
    
    private static final int INCREMENTO_FUEGO = 30;
    
    @Override
    public int aplicarTransformacion(int energiaBase, AfinidadElemental afinidad) {
        if (afinidad.equals(AfinidadElemental.FUEGO)) {
            return energiaBase + INCREMENTO_FUEGO;
        }
        return energiaBase;
    }
    
    @Override
    public boolean afectaEstabilidad(AfinidadElemental afinidad) {
        return afinidad != AfinidadElemental.FUEGO;
    }
    
    @Override
    public String getNombre() {
        return "Llama Interna";
    }
}

