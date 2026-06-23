package exemplos.reativa;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class ProgramacaoReativa {

    public static void executar() {
        SubmissionPublisher<String> publicador = new SubmissionPublisher<>();

        // CORREÇÃO: O tipo <String> foi explicitado na criação da classe anônima
        Subscriber<String> assinante = new Subscriber<String>() {
            private Subscription assinatura;

            @Override
            public void onSubscribe(Subscription assinatura) {
                this.assinatura = assinatura;
                this.assinatura.request(1);
            }

            @Override
            public void onNext(String evento) {
                System.out.println("Evento consumido reativamente: " + evento);
                this.assinatura.request(1);
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

        publicador.close();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
