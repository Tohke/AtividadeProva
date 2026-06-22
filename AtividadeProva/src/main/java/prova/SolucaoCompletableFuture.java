package prova;

import java.util.concurrent.CompletableFuture;

public class SolucaoCompletableFuture {

    public static void executar() {
        System.out.println(
            "Iniciando requisição assíncrona com CompletableFuture..."
        );

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Dados resolvidos via CompletableFuture";
        }).thenAccept(resultado -> {
            System.out.println(
                "Processado na pipeline sem bloqueio: " + resultado
            );
        });

        System.out.println("Thread principal liberada imediatamente.");

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
