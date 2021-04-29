package mc322.lab05;

public class AppDama {
	//P e B são damas
	public static void executaJogo(String caminhoCSV) { //precisa retornar string
		String tabuleiro[] = {"-p-p-p-p", "p-p-p-p-", "-p-p-p-p", "--------", 
							  "--------", "b-b-b-b-", "-b-b-b-b", "b-b-b-b-"};
		Dama tabuleiroDama[][] = new Dama[8][8];
		Peao tabuleiroPeao[][] = new Peao[8][8];
		
		mostrarTabuleiro(tabuleiro);
		preencherMatrizes(tabuleiro, tabuleiroDama, tabuleiroPeao);
		
		//chamando comandos
	    CSVReader csv = new CSVReader();
		csv.setDataSource(caminhoCSV);
		String comandos[] = csv.requestCommands(); 
		
		//executando comandos
		for(int i = 0; i < comandos.length; i++) {
			System.out.println("Comando: " + comandos[i]);
			atualizarTabuleiro(tabuleiro, tabuleiroDama, tabuleiroPeao, comandos[i]);
		}
	}
	
	public static void atualizarTabuleiro(String[] tabuleiro, Dama[][] matrizDama, Peao[][] matrizPeao, String comando){
		int linhaInicial = 56 - (int)comando.charAt(1);
		int colunaInicial = (int)comando.charAt(0) - 97;
		int linhaFinal = 56 - (int)comando.charAt(4);
		int colunaFinal = (int)comando.charAt(3) - 97;
		boolean movimentar;
		
		if(tabuleiro[linhaInicial].charAt(colunaInicial) == 'p' || tabuleiro[linhaInicial].charAt(colunaInicial) == 'b') {	//movimentar o peão		
			movimentar = matrizPeao[linhaInicial][colunaInicial].movimento(matrizPeao[linhaInicial][colunaInicial], tabuleiro, linhaFinal, colunaFinal);
			if(movimentar) {
				tabuleiro[linhaInicial] = tabuleiro[linhaInicial].substring(0, colunaInicial)+"-"+tabuleiro[linhaInicial].substring(colunaInicial+1);
				
				int i = 0;
				if(linhaFinal - linhaInicial < 0)
					i = -1;
				else
					i=1;
				
				if(colunaFinal - colunaInicial == -2) {
					tabuleiro[linhaInicial+i] = tabuleiro[linhaInicial+i].substring(0, colunaInicial-1)+"-"+tabuleiro[linhaInicial+i].substring(colunaInicial);
				}
				else if(colunaFinal - colunaInicial == 2){
					tabuleiro[linhaInicial+i] = tabuleiro[linhaInicial+i].substring(0, colunaInicial+1)+"-"+tabuleiro[linhaInicial+i].substring(colunaInicial+2);					
				}
				
				if(matrizPeao[linhaInicial][colunaInicial].getCor() == "p" && linhaFinal == 7)
					tabuleiro[linhaFinal] = tabuleiro[linhaFinal].substring(0, colunaFinal)+"P"+tabuleiro[linhaFinal].substring(colunaFinal+1);
				else if(matrizPeao[linhaInicial][colunaInicial].getCor() == "b" && linhaFinal == 0)
					tabuleiro[linhaFinal] = tabuleiro[linhaFinal].substring(0, colunaFinal)+"B"+tabuleiro[linhaFinal].substring(colunaFinal+1);
				else
					tabuleiro[linhaFinal] = tabuleiro[linhaFinal].substring(0, colunaFinal)+matrizPeao[linhaInicial][colunaInicial].getCor()+tabuleiro[linhaFinal].substring(colunaFinal+1);
			}
		}
		else if(tabuleiro[linhaInicial].charAt(colunaInicial) == 'B' || tabuleiro[linhaInicial].charAt(colunaInicial) == 'P') { //movimentar a dama
			movimentar = matrizDama[linhaInicial][colunaInicial].movimento(matrizDama[linhaInicial][colunaInicial], tabuleiro, linhaFinal, colunaFinal);
			if(movimentar) {
				tabuleiro[linhaInicial] = tabuleiro[linhaInicial].substring(0, colunaInicial)+"-"+tabuleiro[linhaInicial].substring(colunaInicial+1);

				int i = 0;
				if(linhaFinal - linhaInicial < 0)
					i = -1;
				else
					i=1;
				
				if(Math.abs(colunaFinal - colunaInicial) <= 2) {
					if(colunaFinal - colunaInicial == -2) {
						tabuleiro[linhaInicial+i] = tabuleiro[linhaInicial+i].substring(0, colunaInicial-1)+"-"+tabuleiro[linhaInicial+i].substring(colunaInicial);
					}
					else if(colunaFinal - colunaInicial == 2){
						tabuleiro[linhaInicial+i] = tabuleiro[linhaInicial+i].substring(0, colunaInicial+1)+"-"+tabuleiro[linhaInicial+i].substring(colunaInicial+2);					
					}
				}
				
				tabuleiro[linhaFinal] = tabuleiro[linhaFinal].substring(0, colunaFinal)+matrizDama[linhaInicial][colunaInicial].getCor()+tabuleiro[linhaFinal].substring(colunaFinal+1);
			}
		}
		
		preencherMatrizes(tabuleiro, matrizDama, matrizPeao);
		mostrarTabuleiro(tabuleiro);
	}
	
	public static void mostrarTabuleiro(String[] tabuleiro) {
		for(int i = 0, linha = 8; i < tabuleiro.length; i++, linha--) {
			System.out.println(linha + " " + tabuleiro[i]);
		}
		System.out.println("  abcdefgh");
	}
	
	public static void preencherMatrizes(String[] tabuleiro, Dama[][] tabuleiroDama, Peao[][] tabuleiroPeao) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				switch(tabuleiro[i].charAt(j)) {
					case 'p':
						tabuleiroPeao[i][j] = new Peao(i, j, "p");
						break;
					case 'b':
						tabuleiroPeao[i][j] = new Peao(i, j, "b");
						break;
					case 'P':
						tabuleiroDama[i][j] = new Dama(i, j, "P");
						break;
					case 'B':
						tabuleiroDama[i][j] = new Dama(i, j, "B");
						break;
					default:
						tabuleiroDama[i][j] = null;
						tabuleiroPeao[i][j] = null;
						break;
				}
			}
		}
	}
}
