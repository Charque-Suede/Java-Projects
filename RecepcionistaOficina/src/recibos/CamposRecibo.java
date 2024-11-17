package recibos;


public class CamposRecibo {
    private String nome;
    private int quant;
    private float preco;
    private float valor;

    public CamposRecibo() {
    }

    public CamposRecibo(String nome, int quant, float preco, float valor) {
        this.nome = nome;
        this.quant = quant;
        this.preco = preco;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
