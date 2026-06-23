package exemplos.callback;

public class ExemploCallback {

    public static void executar() {
        System.out.println("Iniciando a cascata de callbacks...");
        ServicoProcessamentoComplexo servico = new ServicoProcessamentoComplexo();

        servico.buscarUsuario("987", usuario -> {
            System.out.println("[Nivel 1] Recebido: " + usuario);

            servico.buscarPermissoes(usuario, permissoes -> {
                System.out.println("  [Nivel 2] Recebido: " + permissoes);

                servico.validarAcesso(permissoes, acesso -> {
                    System.out.println("    [Nivel 3] Recebido: " + acesso);

                    servico.extrairDados(acesso, dados -> {
                        System.out.println("      [Nivel 4] Recebido: " + dados);
                        System.out.println("        Fim do Callback Hell!");
                    });
                });
            });
        });

        System.out.println("Thread principal livre. Aguardando a conclusao da arvore de execucao...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}