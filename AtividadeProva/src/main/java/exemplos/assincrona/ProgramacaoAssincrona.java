package exemplos.assincrona;

public class ProgramacaoAssincrona {

    public static void executar() {
        System.out.println("Iniciando fluxo principal da aplicação...");

        // TAREFA A: Começa antes mas mais demorada
        Thread tarefaDemorada = new Thread(() -> {
            try {
                System.out.println("[Tarefa A] Começou (demora 2s)...");
                Thread.sleep(2000);
                System.out.println("[Tarefa A] Finalizou");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread tarefaRapida = new Thread(() -> {
            try {
                System.out.println("[Tarefa B] Começou depois (demora 1s)...");
                Thread.sleep(1000);
                System.out.println("[Tarefa B] Finalizou");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        tarefaDemorada.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread não pode ser finalizada corretamente.");
        }

        tarefaRapida.start();
        System.out.println("Tarefa A liberou o fluxo principal, outras instruções podem ser executadas imediatamente.");

        // Delay para imprimir corretamente
        // caso não exista, o loop do main é executado novamente devido a ser assíncrona
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
