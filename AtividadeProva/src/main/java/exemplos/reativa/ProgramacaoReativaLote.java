package exemplos.reativa;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class ProgramacaoReativaLote {

    public static void executar() {
        SubmissionPublisher<String> publicador = new SubmissionPublisher<>();

        Subscriber<String> assinante = new Subscriber<String>() {
            private Subscription assinatura;
            private int tamanhoDoLote = 2; // N° de dados enviados
            private int contadorDeLote = 0; // N° de dados processados

            @Override
            public void onSubscribe(Subscription assinatura) {
                this.assinatura = assinatura;
                System.out.println("Pedindo o primeiro lote de " + tamanhoDoLote + " itens.");
                this.assinatura.request(tamanhoDoLote); // Pede os 2 primeiros
            }

            @Override
            public void onNext(String evento) {
                System.out.println("-> Processando item do lote: " + evento);
                contadorDeLote++;

                // Se o lote atual terminar pede o próximo lote
                if (contadorDeLote == tamanhoDoLote) {
                    System.out.println("Lote de " + tamanhoDoLote + " concluído. Pedindo mais " + tamanhoDoLote + "...");
                    contadorDeLote = 0; // Reseta o contador
                    this.assinatura.request(tamanhoDoLote); // Pede mais n dado
                }
            }

            @Override
            public void onError(Throwable erro) {
                System.err.println("Falha no fluxo reativo.");
            }

            @Override
            public void onComplete() {
                System.out.println("Transmissão do fluxo de dados concluída.");
            }
        };

        publicador.subscribe(assinante);

        System.out.println("Iniciando a emissão de eventos em stream...");
        publicador.submit("Evento A");
        publicador.submit("Evento B");
        publicador.submit("Evento C");
        publicador.submit("Evento D");

        publicador.close();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}