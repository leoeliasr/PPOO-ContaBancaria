package src;

public class Conta{
    // atributos
    private double limite;
    private double saldo;
    private Cliente cliente;
    private int numConta;
    private static int nContas = 100;


    public String getNomeCliente() {
        return cliente.getNome();
    }

    public int getNumConta(){
      return numConta;
    }

    public Conta(Cliente cliente, double limite) {
        this.cliente = cliente;
        this.limite = limite;
        numConta = nContas;
        nContas = nContas+1;
        saldo = 0;
    }
    // construtor cliente 
    public Conta (Cliente cliente, double limite,double saldo){
        this(cliente,limite);
        this.saldo = saldo;
        
    }
    // construtor saldo 
    public void saldo (double saldo){
        this.saldo = saldo;
    }

    //metodo de consulta 
    public double getSaldo() {
        return saldo;
    }

    // saque 
      public boolean saque(double valor) {
        if (saldo - valor < limite * -1) {
          return false;
        } else {
          saldo = saldo - valor;
          return true;
        }
      }


    //depositar
    public boolean depositar(double deposito) {
        if (deposito > 0) {
            saldo += deposito;
            return true;
        }
        return false;                                      
    }


    //transferir
    public boolean transferir(Conta conta, double valor) {
        if (this.saque(valor)) {
          conta.depositar(valor);
          return true;
        }
        return false;                              
    }

}