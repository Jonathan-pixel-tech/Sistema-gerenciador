
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mercado {
    private String nome;
    private String CNPJ;
    private String bairro;
    private String cidade;
    private Map<Produto, Integer> estoqueProdutos;

    public Mercado(String nome, String CNPJ, String cidade, String bairro, Map<Produto, Integer> estoqueProdutos) {
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.bairro = bairro;
        this.cidade  = cidade;
        this.estoqueProdutos = estoqueProdutos;
    }
    public Mercado(){
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Map<Produto, Integer> getEstoqueProdutos() {
        return estoqueProdutos;
    }

    public void setEstoqueProdutos(Map<Produto, Integer> estoqueProdutos) {
        this.estoqueProdutos = estoqueProdutos;
    }
    
    
    
    
    
    
}
