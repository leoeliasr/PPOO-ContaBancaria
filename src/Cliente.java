package src;

public class Cliente {
    //atributos 
    private String nome;
    private String cpf;

    public Cliente (String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
   
    //metodo de consulta cpf
    public String getCpf(){
        return cpf;
    }
    //metodo de consulta nome 
    public String getNome(){
        return nome;
    }
}
