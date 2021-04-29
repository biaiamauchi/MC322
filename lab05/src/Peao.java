package mc322.lab05;

public class Peao {
	private int posicaoLinha;
	private int posicaoColuna;
	private String cor;
	
	Peao(int posicaoLinha, int posicaoColuna, String cor){
		this.cor = cor;
		this.posicaoColuna = posicaoColuna;
		this.posicaoLinha = posicaoLinha;
	}
	
	public boolean movimento(Peao peao, String[] tabuleiro, int linhaFinal, int colunaFinal) {
		//movimento de peão
		if(linhaFinal <= 7 && linhaFinal >= 0 && colunaFinal <= 7 && colunaFinal >= 0) { //se o target estiver dentro do tabuleiro
			if((linhaFinal == peao.posicaoLinha - 1 || linhaFinal == peao.posicaoLinha + 1) && (colunaFinal == peao.posicaoColuna - 1 || colunaFinal == peao.posicaoColuna + 1) && tabuleiro[linhaFinal].charAt(colunaFinal) == '-') { //se for caminho vazio
				return true;
			}
			else if(linhaFinal == peao.posicaoLinha + 2 && (colunaFinal == peao.posicaoColuna - 2 || colunaFinal == peao.posicaoColuna + 2) && tabuleiro[linhaFinal].charAt(colunaFinal) == '-') {
				if(tabuleiro[linhaFinal-1].charAt(colunaFinal-1) != peao.getCor().charAt(0))
					return true;
			}
		}
		
		return false;
	}
	
	public String getCor() {
		return this.cor;
	}
}
