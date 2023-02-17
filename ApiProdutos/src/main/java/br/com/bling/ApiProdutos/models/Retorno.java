package br.com.bling.ApiProdutos.models;

import lombok.Data;
import java.util.List;

@Data
public  class Retorno {

    public List<Produto> produtos;
    private List<Categoria> categorias;
    private List<Deposito> depositos;

    public static class Produto {

        public Produto2 produto;
    }

    public static class Categoria {
        public Categoria2 categoria;
    }

    public static class Deposito {
        private Deposito2 deposito;
    }
}
