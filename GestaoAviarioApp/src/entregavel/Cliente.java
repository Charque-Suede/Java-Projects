package entregavel;

public class Cliente {
    
    private int matricula, idade;
    private String nome;
    private char sexo;
    
    public Cliente(int matricula, String nome, int idade, char sexo){
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }
    
    public Cliente(){
        
    }

    public int getMatricula() {
        return matricula;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    public String toString(){
        return matricula+" "+nome+" "+idade+" "+sexo;
    }
    

    
}
