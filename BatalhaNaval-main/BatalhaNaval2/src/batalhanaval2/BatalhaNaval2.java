package batalhanaval2;

public class BatalhaNaval2 {

    boolean fasePlanejamento = true;
    int pecas = 9;
    int tamanho = 1;
    boolean vertical = false;
    String[][] tabuleiroPlayer = new String[5][5];
    String[][] tabuleiroComputador = new String[5][5];
    private TelaBatalha telaBatalha; // Adicione uma referência à TelaBatalha

    // * @param args the command line arguments
    public static void main(String[] args) {
        BatalhaNaval2 jogo = new BatalhaNaval2(); // Crie uma instância de BatalhaNaval2
        jogo.iniciarJogo(); // Inicie o jogo
    }

    private void iniciaTabuleiro(String[][] tabuleiro) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tabuleiro[i][j] = "~"; // Inicializa cada posição da matriz com o caractere "~"
            }
        }
    }

    public void iniciarJogo() {
        // Cria uma instância da tela principal (JFrame) e a torna visível
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                iniciaTabuleiro(tabuleiroPlayer);
                telaBatalha = new TelaBatalha(BatalhaNaval2.this); // Passe a referência de BatalhaNaval2 para TelaBatalha
                telaBatalha.setVisible(true);
            }
        });
    }

    public void posicionarNavio(int linha, int coluna, int tamanhoNavio, boolean vertical) {
        if (vertical) {
            // Posicionamento vertical do navio
            for (int i = 0; i < tamanhoNavio; i++) {
                tabuleiroPlayer[linha + i][coluna] = "N"; // Marca cada parte do navio com "N"
                pecas--;
            }
        } else {
            // Posicionamento horizontal do navio
            for (int i = 0; i < tamanhoNavio; i++) {
                tabuleiroPlayer[linha][coluna + i] = "N"; // Marca cada parte do navio com "N"
                pecas--;
            }
        }

        // Após posicionar o navio, você pode atualizar a interface gráfica se necessário
        // Por exemplo, atualizar um JLabel ou outro componente para refletir o posicionamento do navio
    }

    public boolean posicaoValida(int linha, int coluna, int tamanhoNavio) {
        // Verifica se a posição inicial está dentro dos limites da matriz
        if (linha < 0 || linha >= 5 || coluna < 0 || coluna >= 5) {
            return false;
        }

        if (!vertical) {
            // Verifica se o navio cabe completamente na horizontal
            if (coluna + tamanhoNavio > 5) {
                System.out.println("mairo que coluna");
                return false;
            }
        } else {
            // Verifica se o navio cabe completamente na vertical
            if (linha + tamanhoNavio > 5) {
                System.out.println("maior que linha");
                return false;
            }
        }

        // Verifica se as células necessárias para o tamanho do navio estão vazias
        for (int i = 0; i < tamanhoNavio; i++) {
            if (!tabuleiroPlayer[vertical ? linha + i : linha][vertical ? coluna : coluna + i].equals("~")) {
                return false; // Célula ocupada, posição inválida
            }
        }

        // Se passar por todas as verificações, a posição é válida
        return true;
    }

    public boolean isFasePlanejamento() {
        return fasePlanejamento;
    }

    public void setFasePlanejamento(boolean fasePlanejamento) {
        this.fasePlanejamento = fasePlanejamento;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
}
