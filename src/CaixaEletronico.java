package src;

import java.util.Scanner;
public class CaixaEletronico {
    private Conta contaA;
    private Conta contaB;
    private Scanner scanner;

    public CaixaEletronico() {
        scanner = new Scanner(System.in);
    }

    private void menu() {
        System.out.println("Digite a opção desejada:");
        System.out.println("1 - Criar Contas");
        System.out.println("2 - Consultar Saldo");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Transferir");
        System.out.println("6 - Sair");
     }

    public void controlarCaixa() {
        int opcao = 0;
        do {
            menu();
            opcao = Integer.parseInt(scanner.nextLine());
            opcaoMenu(opcao);
        } while (opcao != 6);
    }

    private void opcaoMenu(int opcao) {
        int numConta;
        switch (opcao) {
            case 1:
                System.out.println("Criando conta A...");
                contaA = criarConta();
                System.out.println("Criando conta B...");
                contaB = criarConta();
                break;
            case 2:
                System.out.println("Número da conta:");
                numConta = Integer.parseInt(scanner.nextLine());
                if (numConta == contaA.getNumConta()) consultarsaldo(contaA);
                else if (numConta == contaB.getNumConta()) consultarsaldo(contaB);
                break;
            case 3:
                System.out.println("Número da conta:");
                numConta = Integer.parseInt(scanner.nextLine());
                if (numConta == contaA.getNumConta()) depositar(contaA);
                else if (numConta == contaB.getNumConta()) depositar(contaB);
                break;
            case 4:
                System.out.println("Número da conta:");
                numConta = Integer.parseInt(scanner.nextLine());
                if (numConta == contaA.getNumConta()) sacar(contaA);
                else if (numConta == contaB.getNumConta()) sacar(contaB);
                break;
            case 5:
                System.out.println("Número da conta que irá transferir:");
                numConta = Integer.parseInt(scanner.nextLine());
                System.out.println("Valor:");
                double valor = Double.parseDouble(scanner.nextLine());
                if (numConta == contaA.getNumConta()) contaA.transferir(contaB, valor);
                else if (numConta == contaB.getNumConta()) contaB.transferir(contaA, valor);
                break;
            case 6:
                break;
        }
    }

    private Conta criarConta() {
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

    private void consultarsaldo(Conta conta) {
        System.out.println("Titular: " + conta.getNomeCliente() + " Saldo: " + conta.getSaldo());
    }

    private void depositar(Conta conta) {
        System.out.println("Valor a ser depositado: ");
        double valor = Double.parseDouble(scanner.nextLine());
        conta.depositar(valor);
    }

    private void sacar(Conta conta) {
        System.out.println("Valor a ser sacado: ");
        double valor = Double.parseDouble(scanner.nextLine());
        conta.saque(valor);
    }
  

}
    
