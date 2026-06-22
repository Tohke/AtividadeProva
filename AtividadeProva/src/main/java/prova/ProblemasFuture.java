package prova;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProblemasFuture {

    public static void executar() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> tarefa = () -> {
            System.out.println(
                "[Thread Secundaria] Buscando entregador disponivel para o pedido do restaurante..."
            );
            Thread.sleep(5000);
            return "[Thread Secundaria] Entregador localizado e alocado!";
        };

        System.out.println(
            "[Thread Principal] Submetendo a tarefa ao executor..."
        );
        Future<String> future = executor.submit(tarefa);

        System.out.println(
            "[Thread Principal] A thread principal esta livre para executar outras operacoes parciais."
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
            "[Thread Principal] Chegou o momento em que a informacao do Future e obrigatoria."
        );
        System.out.println(
            "[Thread Principal] Chamando future.get()... A Thread principal é bloqueada!"
        );

        long tempoInicioBloco = System.currentTimeMillis();

        try {
            String resultado = future.get(); // <----- Aqui está o problema
            long tempoCongelado = System.currentTimeMillis() - tempoInicioBloco;

            System.out.println(
                "[Thread Principal] Fluxo destravado! Retorno: " + resultado
            );
            System.out.println(
                "[Thread Principal] A thread principal ficou totalmente inoperante por " +
                    tempoCongelado +
                    " milissegundos."
            );
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
    }
}
