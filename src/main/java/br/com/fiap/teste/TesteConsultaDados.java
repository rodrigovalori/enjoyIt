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

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------\n"
						+ "Questao 1: Qual a data da ultima visita ao estabelecimento?\n"
						+ "A data da ultima visita foi em " + ConsumidorService.verificarUltimaVisita(numeroTelefone));
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------\n"
						+ "Questao 2: Qual e o ticket medio (valor medio gasto no estabelecimento)?\n"
						+ "O Ticket medio foi de R$" + ConsumidorService.verificarTicketMedio(numeroTelefone));
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------");

		List<Object[]> bebidaFavorita = new ArrayList<Object[]>();

		bebidaFavorita = ConsumidorService.verificarEstiloMarcaFavoritos(numeroTelefone);

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------\n"
						+ "Questao 3: Qual e a bebida e o estilo (cervejas IPA, Pilsen etc.) favoritos com base no consumo?");
		for (Object[] bebida : bebidaFavorita) {
			String marca = (String) bebida[1];
			String estilo = (String) bebida[0];
			System.out.println("Marca favorita: " + marca);
			System.out.println("Estilo favorito: " + estilo);
		}
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------\n");

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("Questao 4: Informe abaixo o intervalo de datas para consultar a frequencia de visitas:");

		String regex = "^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$";
		Pattern pattern = Pattern.compile(regex);

		System.out.print("\nDigite a data inicial (formato: DD/MM/AAAA): ");
		String dataInicio = sc.nextLine();

		Boolean dataInicialValida = pattern.matcher(dataInicio).matches();

		while (!dataInicialValida) {
			System.out.print("Data inicial invalida. Digite-a novamente no formato: DD/MM/AAAA: ");
			dataInicio = sc.nextLine();
			dataInicialValida = pattern.matcher(dataInicio).matches();
		}

		System.out.print("Digite a data final (formato: DD/MM/AAAA): ");
		String dataFim = sc.nextLine();

		Boolean dataFinalValida = pattern.matcher(dataFim).matches();

		while (!dataFinalValida) {
			System.out.print("Data final invalida. Digite-a novamente no formato: DD/MM/AAAA: ");
			dataFim = sc.nextLine();
			dataFinalValida = pattern.matcher(dataFim).matches();
		}

		System.out.println("\nA frequencia de visitas entre " + dataInicio + " e " + dataFim + " foi de "
				+ ConsumidorService.verificarFrequenciaVisita(dataInicio, dataFim, numeroTelefone));
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------");

		sc.close();
	}

}
