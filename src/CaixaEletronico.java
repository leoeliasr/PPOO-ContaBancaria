package src;

import java.util.Scanner;
import java.util.ArrayList;
public class CaixaEletronico {
   
    private ArrayList<Conta> contas;
    private Scanner scanner;

    public CaixaEletronico() {
        contas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    private void menu() {
        System.out.println("Digite a opção desejada:");
        System.out.println("1 - Criar Contas");
        System.out.println("2 - Consultar Saldo");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Transferir");
        System.out.println("6 - Contas existe");
        System.out.println("7 - Apagar conta");
        System.out.println("8 - Sair");
    }

    public void controlarCaixa() {
        int opcao = 0;
        do {
            menu();
            opcao = Integer.parseInt(scanner.nextLine());
            opcaoMenu(opcao);
        } while (opcao != 8);
    }

    private void opcaoMenu(int opcao) {
        switch (opcao) {
            case 1:
                criarConta();
                break;
            case 2:  
                consultarSaldo();
            case 3:
                depositar();
                break;
            case 4:
                sacar();
                break;
            case 5:
                transferir();
                break;
            case 6:
                listarContas();
                break;
            case 7:
                apagarConta();
                break;
            case 8:
                break;
        }
    }

    private int getIndexListaContas(int numConta) {
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumConta() == numConta) {
                return i;
            }
        }
        return -1;
    }

    private int pedirNumeroConta() {
        System.out.println("Número da conta:");
        int numConta = Integer.parseInt(scanner.nextLine());
        return numConta;
    }

    private void apagarConta() {
        System.out.println("Número da conta a ser apagada:");
        int numConta = Integer.parseInt(scanner.nextLine());
        int index = getIndexListaContas(numConta);
        Conta conta = buscarContaPorNumero(numConta);
        if (conta.getSaldo() == 0) {
            contas.remove(index);
            System.out.println("Conta apagada");
        }
        else if (conta.getSaldo() > 0) {
            System.out.println("Não é possível apagar conta com saldo positivo");
        }
        else {
            System.out.println("Não é possível apagar conta com saldo negativo");
        }
    }

    private void listarContas() {
        System.out.println("Contas existentes:");
        for (Conta conta : contas){
            System.out.println(conta.getNumConta() + " - " + conta.getNomeCliente());
        }
    }

    private Conta buscarContaPorNumero (int numContaBuscada){
        for (Conta conta : contas){
            if (conta.getNumConta() == numContaBuscada) {
                return conta; // encontrou conta com número buscado
            }
        }
        return null; // conta com número buscado não existente
    }

    private Conta criarConta() {
        System.out.println("Criando conta ...");
        System.out.println("1. Criar conta com saldo inicial");
        System.out.println("2. Criar conta sem saldo inicial");
        int opcao = Integer.parseInt(scanner.nextLine());
        switch (opcao) {
            case 1:
                return criarContaComSaldo();
            case 2:
                return criarContaSemSaldo();
            default: 
                return null;
        }
    }

    private Conta criarContaComSaldo() {
        System.out.println("Nome do cliente:");
        String nome = scanner.nextLine();
        System.out.println("Cpf do cliente:");
        String cpf = scanner.nextLine();
        System.out.println("Qual saldo inicial?:");
        double saldo = Double.parseDouble(scanner.nextLine());
        System.out.println("Qual o limite?:");
        double limite = Double.parseDouble(scanner.nextLine());
        Cliente cliente = new Cliente(nome, cpf);
        Conta conta = new Conta(cliente,limite,saldo);
        System.out.print("Conta criada Nº ");
        System.out.println(conta.getNumConta());
        return conta;
    }
    private Conta criarContaSemSaldo() {
        System.out.println("Nome do cliente:");
        String nome = scanner.nextLine();
        System.out.println("Cpf do cliente:");
        String cpf = scanner.nextLine();
        System.out.println("Qual o limite?:");
        double limite = Double.parseDouble(scanner.nextLine());
        Cliente cliente = new Cliente(nome, cpf);
        Conta conta = new Conta(cliente,limite);
        System.out.print("Conta criada Nº ");
        System.out.println(conta.getNumConta());
        return conta;
    }

    private void consultarSaldo() {
        int numConta = pedirNumeroConta();
        Conta conta = buscarContaPorNumero(numConta);
        if (conta != null) {
            System.out.println("Titular: " + conta.getNomeCliente() + " Saldo: " + conta.getSaldo());
        } 
        else {
            System.out.println("Conta não existente");
        }
    }

    private void depositar() {
        int numConta = pedirNumeroConta();
        Conta conta = buscarContaPorNumero(numConta);
        System.out.println("Valor a ser depositado: ");
        double valor = Double.parseDouble(scanner.nextLine());
        conta.depositar(valor);
    }

    private void sacar() {
        int numConta = pedirNumeroConta();
        Conta conta = buscarContaPorNumero(numConta);
        System.out.println("Valor a ser sacado: ");
        double valor = Double.parseDouble(scanner.nextLine());
        conta.depositar(valor);
    }
  
    private void transferir() {
        System.out.println("Número da conta que irá transferir:");
        int numContaA = Integer.parseInt(scanner.nextLine());
        Conta contaA = buscarContaPorNumero(numContaA);
        System.out.println("Número da conta que irá receber:");
        int numContaB = Integer.parseInt(scanner.nextLine());
        System.out.println("Valor:");
        double valor = Double.parseDouble(scanner.nextLine());
        Conta contaB = buscarContaPorNumero(numContaB);
        if (contaA != null && contaB != null) {
            if (contaA.transferir(contaB, valor)) {
                System.out.println("Valor transferido");
            }
            else {
                System.out.println("Valor não transferido");

            }
        }
    }

}