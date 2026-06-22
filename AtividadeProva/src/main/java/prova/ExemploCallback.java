package prova;

interface CallbackOperacao {
    void aoCompletar(String resultado);
}

class ServicoProcessamento {

    void executarProcessoLongoS(CallbackOperacao callback) {
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                callback.aoCompletar(
                    "Dados processados com sucesso no banco de dados."
                );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

public class ExemploCallback {

    public static void executar() {
        System.out.println("Iniciando serviço com injeção de callback...");
        ServicoProcessamento servico = new ServicoProcessamento();

        servico.executarProcessoLongoS(resultado -> {
            System.out.println("Retorno do Callback invocado: " + resultado);
        });

        System.out.println("Aguardando processamento...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
