package models;

import lombok.Data;
import java.util.List;

@Data
public  class Retorno {
    private List<Categoria> categorias;

    @Data
    public static class Categoria {
        public Categoria2 categoria;
    }
}
