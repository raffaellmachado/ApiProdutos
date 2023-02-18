package br.com.bling.ApiProdutos.models;

import lombok.Data;
import java.util.List;

@Data
public  class Retorno {

    public List<Produtos> produtos;
    private List<Categoria> categorias;
    private List<Deposito> depositos;

    public static class Produtos {

        public Produto produto;
    }

    public static class Categoria {
        public Categoria categoria;
    }

    public static class Deposito {
        private Deposito deposito;
    }
}
