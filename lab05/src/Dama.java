package mc322.lab05;

public class Dama {
	private int posicaoLinha;
	private int posicaoColuna;
	private String cor;
	
	Dama(int posicaoLinha, int posicaoColuna, String cor){
		this.posicaoLinha = posicaoLinha;
		this.posicaoColuna = posicaoColuna;
		this.cor = cor;
	}
	
	public String getCor() {
		return this.cor;
	}
	
	public boolean movimento(Dama dama, String[] tabuleiro, int linhaFinal, int colunaFinal) {
		if(linhaFinal <= 7 && linhaFinal >= 0 && colunaFinal <= 7 && colunaFinal >= 0) { //se o target estiver dentro do tabuleiro
			if((linhaFinal == dama.posicaoLinha - 1 || linhaFinal == dama.posicaoLinha + 1) && (colunaFinal == dama.posicaoColuna - 1 || colunaFinal == dama.posicaoColuna + 1) && tabuleiro[linhaFinal].charAt(colunaFinal) == '-') { //se for caminho vazio
				return true;
			}
			else if(linhaFinal == dama.posicaoLinha + 2 && (colunaFinal == dama.posicaoColuna - 2 || colunaFinal == dama.posicaoColuna + 2) && tabuleiro[linhaFinal].charAt(colunaFinal) == '-') {
				if(tabuleiro[linhaFinal-1].charAt(colunaFinal-1) != dama.getCor().charAt(0))
					return true;
			}
			else if(linhaFinal == dama.posicaoLinha - 2 && (colunaFinal == dama.posicaoColuna - 2 || colunaFinal == dama.posicaoColuna + 2) && tabuleiro[linhaFinal].charAt(colunaFinal) == '-') {
				if(tabuleiro[linhaFinal-1].charAt(colunaFinal-1) != dama.getCor().charAt(0))
					return true;
			}
			else if(linhaFinal > dama.posicaoLinha + 2 && colunaFinal > dama.posicaoColuna + 2 && tabuleiro[linhaFinal].charAt(colunaFinal) == '-') {
				int aux = Math.abs(linhaFinal - dama.posicaoLinha), i = 1;
				while(aux != 0) {
					if(tabuleiro[dama.posicaoLinha+i].charAt(dama.posicaoColuna+i) != '-')
						return false;
					aux--;
					i++;
				}
				return true;
			}
			else if(linhaFinal < dama.posicaoLinha + 2 && colunaFinal < dama.posicaoColuna + 2 && tabuleiro[linhaFinal].charAt(colunaFinal) == '-') {
				int aux = Math.abs(linhaFinal - dama.posicaoLinha), i = 1;
				while(aux != 0) {
					if(tabuleiro[dama.posicaoLinha-i].charAt(dama.posicaoColuna-i) != '-')
						return false;
					aux--;
					i++;
				}
				return true;
			}
		}
		return false;
	}
}