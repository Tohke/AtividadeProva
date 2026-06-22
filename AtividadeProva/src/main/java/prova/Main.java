package prova;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println(
                "\n--- Exemplos de Programação Não Bloqueante ---"
            );
            System.out.println("1 - Problemas com threads bloqueantes");
            System.out.println("2 - Programação assíncrona");
            System.out.println("3 - Callbacks");
            System.out.println("4 - Problemas com Future em Java");
            System.out.println("5 - Solução com CompletableFuture");
            System.out.println("6 - Programação reativa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        ProblemasThreadBloqueante.executar();
                        break;
                    case 2:
                        ProgramacaoAssincrona.executar();
                        break;
                    case 3:
                        ExemploCallback.executar();
                        break;
                    case 4:
                        ProblemasFuture.executar();
                        break;
                    case 5:
                        SolucaoCompletableFuture.executar();
                        break;
                    case 6:
                        ProgramacaoReativa.executar();
                        break;
                    case 0:
                        System.out.println("Encerrando a aplicação...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Entrada inválida.");
                scanner.next();
            }
        }
        scanner.close();
    }
}
