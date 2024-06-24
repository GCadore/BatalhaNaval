package batalhanaval2;

/**
 *
 * @author Cadore
 */

public class BatalhaNaval2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Cria uma instância da tela principal (JFrame) e a torna visível
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaBatalha tela = new TelaBatalha();
                tela.setVisible(true);
            }
        });
    }
    
}
