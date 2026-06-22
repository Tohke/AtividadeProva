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
            Thread.sleep(2000);
            return "Resultado retornado pela interface Future";
        };

        Future<String> future = executor.submit(tarefa);
        System.out.println(
            "Executando outras operações enquanto a thread secundária trabalha..."
        );

        try {
            String resultado = future.get();
            System.out.println(
                "Operação bloqueada na chamada get(): " + resultado
            );
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
    }
}
