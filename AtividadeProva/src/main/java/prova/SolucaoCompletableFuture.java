package prova;

import java.util.concurrent.CompletableFuture;

public class SolucaoCompletableFuture {

    public static void executar() {
        System.out.println(
            "[Thread Principal] Iniciando a busca de entregador de forma ASSÍNCRONA..."
        );

        CompletableFuture<String> buscaEntregador =
            CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Entregador Lucas (ID: 45)";
            });

        buscaEntregador.thenAccept(entregador -> {
            System.out.println(
                "\n--------------------------------------------------------------"
            );
            System.out.println(
                "[Pipeline CompletableFuture] SINAL RECEBIDO: " +
                    entregador +
                    " alocado!"
            );
            System.out.println(
                "[Pipeline CompletableFuture] Atualizando o mapa do cliente sem bloquear ninguém."
            );
            System.out.println(
                "--------------------------------------------------------------\n"
            );
        });

        System.out.println(
            "[Thread Principal] Pipeline configurada. A thread principal CONTINUA LIVRE!"
        );

        for (int segundo = 1; segundo <= 5; segundo++) {
            System.out.println(
                "[Thread Principal] Processando outras coisas... (Segundo " +
                    segundo +
                    "/5)"
            );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(
            "[Thread Principal] Fim do exemplo de CompletableFuture."
        );
    }
}
