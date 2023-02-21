package br.com.bling.ApiProdutos.models;

import lombok.Data;
import java.util.List;

@Data
public class Retorno {

    public List<Produto> produtos;
    public List<Categoria> categorias;
    public List<Deposito> depositos;

    @Data
    public static class Produto {

        public Produto2 produto;
    }

    @Data
    public static class Categoria {
        public Categoria2 categoria;
    }

    @Data
    public static class Deposito {
        private Deposito2 deposito;
    }
}
