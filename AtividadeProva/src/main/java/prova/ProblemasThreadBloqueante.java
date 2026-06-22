package prova;

public class ProblemasThreadBloqueante {

    public static void executar() {
        System.out.println(
            "Iniciando processamento sequencial e bloqueante..."
        );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(
            "Processamento concluído. A thread principal ficou ociosa aguardando a liberação."
        );
    }
}
