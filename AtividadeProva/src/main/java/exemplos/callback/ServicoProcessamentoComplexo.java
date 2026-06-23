package exemplos.callback;

class ServicoProcessamentoComplexo {

    void buscarUsuario(String id, ICallback callback) {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            callback.aoCompletar("Usuario_" + id);
        }).start();
    }

    void buscarPermissoes(String usuario, ICallback callback) {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            callback.aoCompletar("Permissoes_Admin_Para_" + usuario);
        }).start();
    }

    void validarAcesso(String permissoes, ICallback callback) {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            callback.aoCompletar("Acesso_Liberado_Com_" + permissoes);
        }).start();
    }

    void extrairDados(String acesso, ICallback callback) {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            callback.aoCompletar("Dados_Finais_Extraidos");
        }).start();
    }
}