package mc322.lab04;
import java.lang.StringBuilder;

public class AppRestaUm {
	
	public static void executaJogo(String caminhoCSV){
	    StringBuilder tabuleiro = new StringBuilder();
	    tabuleiro.append("  PPP  \n  PPP  \nPPPPPPP\nPPP-PPP\nPPPPPPP\n  PPP  \n  PPP  \n");
	    
	    //tabuleiro inicial
	    String vetorTabuleiro[] = devolverVetorTabuleiro(tabuleiro.toString());
	    imprimirTabuleiro(vetorTabuleiro, "Tabuleiro inicial: ");
	    
	    //chamando comandos
	    CSVReader csv = new CSVReader();
		csv.setDataSource(caminhoCSV);
		String comandos[] = csv.requestCommands(); 
		
		//executando comandos
		for(int i = 0; i < comandos.length; i++) {
			realizaComando(comandos, vetorTabuleiro, i);
		}
	}
	
	public static void realizaComando(String[] comandos, String[] vetorTabuleiro, int i) {
		int linhaInicial = 55 - (int)comandos[i].charAt(1);
		int colunaInicial = (int)comandos[i].charAt(0) - 97;
		int linhaFinal = 55 - (int)comandos[i].charAt(4);
		int colunaFinal = (int)comandos[i].charAt(3) - 97;
		String title = "Source: " + comandos[i].substring(0, 2) + "\n" + "Target: " + comandos[i].substring(3);
		
		if (vetorTabuleiro[linhaInicial].charAt(colunaInicial) == 'P') {
			if (vetorTabuleiro[linhaFinal].charAt(colunaFinal) == '-') {
				if (colunaInicial == colunaFinal) {
					if (linhaInicial - linhaFinal == 2) {
						if (vetorTabuleiro[linhaInicial - 1].charAt(colunaFinal) == 'P') {
							//altera o tabuleiro
							vetorTabuleiro[(linhaInicial + linhaFinal) / 2] = vetorTabuleiro[(linhaInicial + linhaFinal) / 2].substring(0, colunaFinal) + '-' + vetorTabuleiro[(linhaInicial + linhaFinal) / 2].substring(colunaFinal + 1);
							vetorTabuleiro[linhaInicial] = vetorTabuleiro[linhaInicial].substring(0, colunaFinal) + '-' + vetorTabuleiro[linhaInicial].substring(colunaFinal + 1);
							vetorTabuleiro[linhaFinal] = vetorTabuleiro[linhaFinal].substring(0, colunaFinal) + 'P' + vetorTabuleiro[linhaFinal].substring(colunaFinal + 1);
							imprimirTabuleiro(vetorTabuleiro, title);
						}
					} else if (linhaInicial - linhaFinal == -2) {
						if (vetorTabuleiro[linhaInicial + 1].charAt(colunaFinal) == 'P') {
							//altera o tabuleiro
							vetorTabuleiro[(linhaInicial + linhaFinal) / 2] = vetorTabuleiro[(linhaInicial + linhaFinal) / 2].substring(0, colunaFinal) + '-' + vetorTabuleiro[(linhaInicial + linhaFinal) / 2].substring(colunaFinal + 1);
							vetorTabuleiro[linhaInicial] = vetorTabuleiro[linhaInicial].substring(0, colunaFinal) + '-' + vetorTabuleiro[linhaInicial].substring(colunaFinal + 1);
							vetorTabuleiro[linhaFinal] = vetorTabuleiro[linhaFinal].substring(0, colunaFinal) + 'P' + vetorTabuleiro[linhaFinal].substring(colunaFinal + 1);
							imprimirTabuleiro(vetorTabuleiro, title);
						}
					}
				} else if (linhaInicial == linhaFinal) {
					if (colunaInicial - colunaFinal == 2) {
						if (vetorTabuleiro[linhaFinal].charAt(colunaInicial + 1) == 'P') {
							//altera o tabuleiro
							vetorTabuleiro[linhaFinal] = vetorTabuleiro[linhaFinal].substring(0, (colunaInicial + colunaFinal) / 2) + '-' + vetorTabuleiro[linhaFinal].substring(((colunaInicial + colunaFinal) / 2) + 1);
							vetorTabuleiro[linhaFinal] = vetorTabuleiro[linhaFinal].substring(0, colunaInicial) + '-' + vetorTabuleiro[linhaFinal].substring(colunaInicial + 1);
							vetorTabuleiro[linhaFinal] = vetorTabuleiro[linhaFinal].substring(0, colunaFinal) + 'P' + vetorTabuleiro[linhaFinal].substring(colunaFinal + 1);
							imprimirTabuleiro(vetorTabuleiro, title);
						}
					} else if (colunaInicial - colunaFinal == -2) {
						if (vetorTabuleiro[linhaFinal].charAt(colunaInicial - 1) == 'P') {
							//altera o tabuleiro
							vetorTabuleiro[linhaFinal] = vetorTabuleiro[linhaFinal].substring(0, (colunaInicial + colunaFinal) / 2) + '-' + vetorTabuleiro[linhaFinal].substring(((colunaInicial + colunaFinal) / 2) + 1);
							vetorTabuleiro[linhaFinal] = vetorTabuleiro[linhaFinal].substring(0, colunaInicial) + '-' + vetorTabuleiro[linhaFinal].substring(colunaInicial + 1);
							vetorTabuleiro[linhaFinal] = vetorTabuleiro[linhaFinal].substring(0, colunaFinal) + 'P' + vetorTabuleiro[linhaFinal].substring(colunaFinal + 1);
							imprimirTabuleiro(vetorTabuleiro, title);
						}
					}
				}
			}
		}
	}
	
	public static String[] devolverVetorTabuleiro(String tabuleiro) {
		int linha = 0;
		String vetorTabuleiro[] = new String[8];
		while (linha < 7){
		   vetorTabuleiro[linha] = tabuleiro.substring(linha * 8, (linha+1)*8);
		   linha++;
		}
		
		return vetorTabuleiro;
	}
	
	public static void imprimirTabuleiro(String[] tabuleiro, String mensagem) {
		System.out.println(mensagem);
		for(int i = 0; i < tabuleiro.length-1; i++) {
			System.out.print(7 - i);
			System.out.print(tabuleiro[i]);
		}
		System.out.println(" abcdefg");
	}
}
