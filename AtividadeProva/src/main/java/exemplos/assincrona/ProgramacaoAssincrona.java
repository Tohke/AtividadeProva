package prova;

public class ProgramacaoAssincrona {

    public static void executar() {
        System.out.println("Iniciando fluxo principal da aplicação...");

        Thread threadAssincrona = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(
                    "Tarefa assíncrona finalizada em background."
                );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadAssincrona.start();
        System.out.println(
            "Fluxo principal livre. Outras instruções podem ser executadas imediatamente."
        );

        try {
            threadAssincrona.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
