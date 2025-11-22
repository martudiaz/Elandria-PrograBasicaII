package transformacion;

import criatura.AfinidadElemental;


public interface Transformacion {
    
 
    int aplicarTransformacion(int energiaBase, AfinidadElemental afinidad);
    
    boolean afectaEstabilidad(AfinidadElemental afinidad);
    
    String getNombre();
}

