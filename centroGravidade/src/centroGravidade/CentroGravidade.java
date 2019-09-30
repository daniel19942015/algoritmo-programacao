package centroGravidade;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.*;
import java.text.DecimalFormat;

public class CentroGravidade {
	
	//Instanciando os objetos, de forma globa, para leitura de arquivos
	public static FileReader arquivo;
	public static BufferedReader lerArquivo;
	
	//Função para responsavel por retornar os indices da matriz.
	public static int[] montarIndice() throws IOException {
		//Instanciando objeto filereader para ler o diretorio do arquivo
		arquivo = new FileReader("src/centroGravidade/arquivo.txt");
		lerArquivo =  new BufferedReader(arquivo);
		
		String[] indices = lerArquivo.readLine().split(" ");
		int[] novoIndice = new int[indices.length];	
		
		for(int i = 0; i < novoIndice.length; i++) {
			novoIndice[i] = Integer.parseInt(indices[i]);
		}
		
		lerArquivo.close();
		return novoIndice;
	}

	public static double[][] montarVetor(int indice[]) throws IOException{
		try {
			int ignoraLinha = 0;
			arquivo = new FileReader("src/centroGravidade/arquivo.txt");
			lerArquivo =  new BufferedReader(arquivo);
			String[] vetorLinha = new String[indice[0]];
			double[][] novoVetor = new double[indice[0]][indice[1]];
			
			String linha;
			for(int count = 0; (linha = lerArquivo.readLine()) != null; count++) {
				if(count != ignoraLinha) {
					vetorLinha[count - 1] = linha;
				}
			}
			for(int i = 0; i < vetorLinha.length; i++) {
				for(int j = 0; j < vetorLinha[i].split(" ").length; j++) {
					novoVetor[i][j] = Double.parseDouble(vetorLinha[i].split(" ")[j]);
				}
			}
			
			lerArquivo.close();
			return novoVetor;
			
		}catch(Exception e) {
			lerArquivo.close();
			return null;
		}
	}
	
	public static double diferencaLinha(double[][] matriz) {
		DecimalFormat format = new DecimalFormat("0.##");
		
		int linhaCimaMax = 2, indiceBaixo = matriz.length - 1;
		double somaLinhaCima = 0, somaLinhaBaixo = 0, somaTotal = 0;		
		
		for(int indiceCima = 0; indiceCima < linhaCimaMax; indiceCima++) {
			for(int i = 0; i < matriz[indiceCima].length; i++) {
				somaLinhaCima += matriz[indiceCima][i];
			}
		}
		
		for(int j = (matriz.length - 2); j <= indiceBaixo; j++) {
			for(int k = 0; k < matriz[j].length; k++) {
				somaLinhaBaixo += matriz[j][k];
			}
		}
		
		if(somaLinhaCima >= somaLinhaBaixo) {
			somaTotal = somaLinhaCima - somaLinhaBaixo;
			somaTotal = Double.parseDouble(format.format(somaTotal).replace(",", "."));
		}else {
			somaTotal = somaLinhaBaixo - somaLinhaCima;
			somaTotal = Double.parseDouble(format.format(somaTotal).replace(",", "."));
		}
		
		return somaTotal;
	}
	
	public static double diferencaColuna(double[][] matriz) {
		DecimalFormat format = new DecimalFormat("0.##");
		int colunaCimaMax = 2;
		double somaColunaCima = 0, somaColunaBaixo = 0, somaTotal = 0;
		
		
		for(int i = 0; i < colunaCimaMax; i++) {
			for(int j = 0; j < matriz.length; j++) {
				somaColunaCima += matriz[j][i];
			}
		}
		
		for(int l = matriz[0].length - 2; l <= matriz[0].length - 1; l++) {
			for(int k = 0; k < matriz.length; k++) {
				somaColunaBaixo += matriz[k][l];
			}
		}
		
		if(somaColunaCima >= somaColunaBaixo) {
			somaTotal = somaColunaCima - somaColunaBaixo;
			somaTotal = Double.parseDouble(format.format(somaTotal).replace(",", "."));
		}else {
			somaTotal = somaColunaBaixo - somaColunaCima;
			somaTotal = Double.parseDouble(format.format(somaTotal).replace(",", "."));
		}
		
		return somaTotal;
	}
	
	
	public static double[] retornaIndice(double linha, double coluna, double[][] matriz) {
		
		int indiceLinha = 0, 
		linhaInicio = 1, 
		colunaInicio = 1,
		indiceColuna = 0;
		
		double[] indices = new double[2];
		
		for(int i = linhaInicio; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				if(linha == matriz[i][j]) {
					indiceLinha = i + 1;
					break;
				}else {
					continue;
				}	
			}
		}
		
		for(int k = colunaInicio; k < matriz[0].length; k++) {
			for(int l = 0; l < matriz.length; l++) {
				if(coluna == matriz[l][k]) {
					indiceColuna = l + 1;
					break;
				}else {
					continue;
				}	
			}
		}
		
		indices[0] = indiceLinha;
		indices[1] = indiceColuna;
		
		return indices;
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		int[] indice = montarIndice();
		double[][] matriz = montarVetor(indice);
		double linha = diferencaLinha(matriz);
		double coluna = diferencaColuna(matriz);
		
		
		for(int i = 0; i < montarVetor(indice).length; i++) {
			for(int j = 0; j < montarVetor(indice)[i].length; j++) {
				System.out.print(montarVetor(indice)[i][j] + "|  |");
			}
			System.out.print("\n");
		}
		
		System.out.println(diferencaLinha(montarVetor(indice)));
		System.out.println(diferencaColuna(montarVetor(indice)));
		System.out.println(retornaIndice(linha, coluna, matriz)[0]);
		System.out.println(retornaIndice(linha, coluna, matriz)[1]);
	
	}
}