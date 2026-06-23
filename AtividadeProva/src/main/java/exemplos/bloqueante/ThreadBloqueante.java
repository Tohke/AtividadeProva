package exemplos.bloqueante;

public class ThreadBloqueante {

    public static void executar() {
        System.out.println(
            "Iniciando processamento sequencial e bloqueante..."
        );
        try {
            Thread.sleep(3000); // A thread poderia ter que dormir por ainda mais tempo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(
            "Processamento concluído. A thread principal ficou ociosa aguardando a liberação."
        );
    }
}
