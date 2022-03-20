package br.com.fiap.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import br.com.fiap.service.ConsumidorService;

public class TesteConsultaDados {

	public static void main(String[] args) {

		List<Object[]> listaConsumidores = new ArrayList<Object[]>();
		listaConsumidores = ConsumidorService.findAll();

		System.out.println("\nLista de consumidores disponiveis:\n");

		for (Object[] obj : listaConsumidores) {
			BigDecimal nrTelefone = (BigDecimal) obj[0];
			String nomeConsumidor = (String) obj[1];
			System.out.println(nrTelefone + " - " + nomeConsumidor + "\n");
		}

		Scanner sc = new Scanner(System.in);

		Boolean numeroEncontrado = false;

		System.out.print("Digite um numero de telefone dentre as opcoes da lista: ");
		String numeroTelefone = sc.nextLine();

		while (!numeroEncontrado) {
			for (Object[] obj : listaConsumidores) {
				BigDecimal nrTelefone = (BigDecimal) obj[0];
				if (numeroTelefone.equals(nrTelefone.toString())) {
					numeroEncontrado = true;
					break;
				}
			}
			if (numeroEncontrado)
				break;
			System.out.print("Telefone nao cadastrado na base de dados. Digite novamente: ");
			numeroTelefone = sc.nextLine();
		}

		String regex = "^[0-3]?[0-9]/[0-1]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
		Pattern pattern = Pattern.compile(regex);

		System.out.print("\nDigite a data inicial (formato: DD/MM/AAAA): ");
		String dataInicio = sc.nextLine();

		Boolean dataInicialValida = pattern.matcher(dataInicio).matches();

		while (!dataInicialValida) {
			System.out.print("Data inicial invalida. Digite-a novamente no formato: DD/MM/AAAA: ");
			dataInicio = sc.nextLine();
			dataInicialValida = pattern.matcher(dataInicio).matches();
		}

		System.out.print("\nDigite a data final (formato: DD/MM/AAAA): ");
		String dataFim = sc.nextLine();

		Boolean dataFinalValida = pattern.matcher(dataFim).matches();

		while (!dataFinalValida) {
			System.out.print("Data final invalida. Digite-a novamente no formato: DD/MM/AAAA: ");
			dataFim = sc.nextLine();
			dataFinalValida = pattern.matcher(dataFim).matches();
		}

		System.out.println("Questao 1: Qual a data da ultima visita ao estabelecimento?\n"
				+ "A data da ultima visita foi em " + ConsumidorService.verificarUltimaVisita(numeroTelefone));

		System.out.println("Questao 2: Qual e a frequencia de visitas?\n"
				+ "A frequencia de visitas entre " + dataInicio + " e " + dataFim + "foi de "
				+ ConsumidorService.verificarFrequenciaVisita(dataInicio, dataFim, numeroTelefone));

		System.out.println("Questao 3: Qual e o ticket medio (valor medio gasto no estabelecimento)?\n"
				+ "O Ticket medio foi de R$" + ConsumidorService.verificarTicketMedio(numeroTelefone));

		List<Object[]> bebidaFavorita = new ArrayList<Object[]>();

		bebidaFavorita = ConsumidorService.verificarEstiloMarcaFavoritos(numeroTelefone);

		System.out.println("Questao 4: Qual e a bebida e o estilo (cervejas IPA, Pilsen etc.) favoritos com base no consumo?");
		for (Object[] bebida : bebidaFavorita) {
			String marca = (String) bebida[1];
			String estilo = (String) bebida[0];
			System.out.println("Marca favorita: " + marca);
			System.out.println("Estilo favorito: " + estilo);
		}

		sc.close();
	}

}
