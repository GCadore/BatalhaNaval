package batalhanaval2;

import java.util.Random;

public class BatalhaNaval2 {

    boolean fasePlanejamento = true;
    int pecas = 9;
    int pecasC = 9;
    int tamanho = 1;
    int pontosP = 0;
    int pontosC = 0;
    boolean vertical = false;
    boolean devView = false;
    
    String[][] tabuleiroPlayer = new String[5][5];
    String[][] tabuleiroComputadorDev = new String[5][5];
    String[][] tabuleiroComputador = new String[5][5];
    
    private Random random = new Random();
    private TelaBatalha telaBatalha;

    public static void main(String[] args) {
        BatalhaNaval2 jogo = new BatalhaNaval2();
        jogo.iniciarJogo();
    }

    private void iniciaTabuleiro(String[][] tabuleiro) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tabuleiro[i][j] = "~";
            }
        }
    }

    public void iniciarJogo() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                iniciaTabuleiro(tabuleiroPlayer);
                iniciaTabuleiro(tabuleiroComputador);
                 iniciaTabuleiro(tabuleiroComputadorDev);
                telaBatalha = new TelaBatalha(BatalhaNaval2.this);
                telaBatalha.setVisible(true);

            }
        });
    }

    public void posicionarNavioC(int linha, int coluna, int tamanhoNavio, boolean vertical) {
        if (vertical) {
            for (int i = 0; i < tamanhoNavio; i++) {
                tabuleiroComputadorDev[linha + i][coluna] = "N";
            }
        } else {
            for (int i = 0; i < tamanhoNavio; i++) {
                tabuleiroComputadorDev[linha][coluna + i] = "N";
            }
        }
    }

    public boolean posicaoValidaC(int linha, int coluna, int tamanhoNavio, boolean vertical) {
        if (linha < 0 || linha >= 5 || coluna < 0 || coluna >= 5) {
            return false;
        }

        if (vertical) {
            if (linha + tamanhoNavio > 5) {
                return false;
            }
        } else {
            if (coluna + tamanhoNavio > 5) {
                return false;
            }
        }

        for (int i = 0; i < tamanhoNavio; i++) {
            if (!tabuleiroComputadorDev[vertical ? linha + i : linha][vertical ? coluna : coluna + i].equals("~")) {
                return false;
            }
        }

        return true;
    }

    public void computadorJoga() {
        int[] tamanhosNavios = {1, 1, 2, 2, 3};
        for (int tamanhoNavio : tamanhosNavios) {
            boolean navioPosicionado = false;
            while (!navioPosicionado) {
                int linha = random.nextInt(5);
                int coluna = random.nextInt(5);
                boolean vertical = random.nextBoolean();

                if (posicaoValidaC(linha, coluna, tamanhoNavio, vertical)) {
                    posicionarNavioC(linha, coluna, tamanhoNavio, vertical);
                    navioPosicionado = true;
                }
            }
        }
    }

    public void posicionarNavio(int linha, int coluna, int tamanhoNavio, boolean vertical) {
        if (vertical) {
            for (int i = 0; i < tamanhoNavio; i++) {
                tabuleiroPlayer[linha + i][coluna] = "N";
                pecas--;
            }
        } else {
            for (int i = 0; i < tamanhoNavio; i++) {
                tabuleiroPlayer[linha][coluna + i] = "N";
                pecas--;
            }
        }
    }

    public boolean posicaoValida(int linha, int coluna, int tamanhoNavio, String[][] tab) {
        if (linha < 0 || linha >= 5 || coluna < 0 || coluna >= 5) {
            return false;
        }

        if (!vertical) {
            if (coluna + tamanhoNavio > 5) {
                System.out.println("maior que coluna");
                return false;
            }
        } else {
            if (linha + tamanhoNavio > 5) {
                System.out.println("maior que linha");
                return false;
            }
        }

        for (int i = 0; i < tamanhoNavio; i++) {
            if (!tab[vertical ? linha + i : linha][vertical ? coluna : coluna + i].equals("~")) {
                return false;
            }
        }

        return true;
    }

    public void verificarAtaque(int linha, int coluna) {
        // Verifica se a linha e a coluna estão dentro dos limites da matriz
        if (linha < 0 || linha >= 5 || coluna < 0 || coluna >= 5) {
            System.out.println("Posição fora dos limites!");
            return;
        }

        // Verifica o valor na posição especificada do tabuleiro do computador
        if (tabuleiroComputadorDev[linha][coluna].equals("N")) {
            tabuleiroComputador[linha][coluna] = "X";
            tabuleiroComputadorDev[linha][coluna] = "X";// Acerto
            pontosP++;
            System.out.println("Acertou um navio!");
            
        } else if (tabuleiroComputadorDev[linha][coluna].equals("~")) {
            tabuleiroComputador[linha][coluna] = "O"; // Erro
            tabuleiroComputadorDev[linha][coluna] = "O";
            System.out.println("Água!");
        } else {
            System.out.println("Posição já atacada!");
        }
    }
    public void verificarAtaqueComp() {
         int linha;
        int coluna;

        do {
            linha = random.nextInt(5); // Gera um número aleatório de 0 a 4 (inclusive)
            coluna = random.nextInt(5); // Gera um número aleatório de 0 a 4 (inclusive)
        } while (tabuleiroPlayer[linha][coluna].equals("X") || tabuleiroPlayer[linha][coluna].equals("O")); // Repete enquanto a posição já foi atacada

        // Simula o ataque na posição gerada aleatoriamente
        if (tabuleiroPlayer[linha][coluna].equals("N")) {
            tabuleiroPlayer[linha][coluna] = "X"; // Navio atingido
            pontosC++;
        } else {
            tabuleiroPlayer[linha][coluna] = "O"; // Água atingida
        }

        // Exibe mensagem do resultado do ataque
        System.out.println("O computador atacou em (" + linha + "," + coluna + ") e " +
                (tabuleiroPlayer[linha][coluna].equals("X") ? "atingiu um navio!" : "acertou água."));
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
    public void setDevView(boolean devView) {
        this.devView = devView;
    }
}
