package br.com.fiap.teste.custom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteConsultaDados {

	private static List<Object[]> findAll() {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery("SELECT nr_telefone, nm_consumidor FROM tb_consumidor");

			List<Object[]> listaConsumidores = query.getResultList();	
			
			return listaConsumidores;

		} catch (Exception e) {
			e.printStackTrace();

			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();

			}
		}
		return null;

	}

	public static void main(String[] args) {

		List<Object[]> listaConsumidores = new ArrayList<Object[]>();
		listaConsumidores = findAll();
		
		System.out.println("\nLista de consumidores dispon�veis:\n");
		
		for (Object[] obj : listaConsumidores) {
			BigDecimal nrTelefone = (BigDecimal) obj[0];
			String nomeConsumidor = (String) obj[1];
			System.out.println(nrTelefone + " - " + nomeConsumidor + "\n");
		}

		Scanner sc = new Scanner(System.in);
		
		Boolean numeroEncontrado = false;
		
		String numTelefoneDigitado = null;
		
		do {
			System.out.println("\nDigite um n�mero de telefone dentre as op��es da lista:\n");
			numTelefoneDigitado = sc.nextLine();
			for (Object[] obj : listaConsumidores) {
				BigDecimal nrTelefone = (BigDecimal) obj[0];
				if (numTelefoneDigitado.equals(nrTelefone.toString())) {
					System.out.println("Telefone v�lido");
					numeroEncontrado = true;
				} else {
					System.out.println("Telefone inv�lido");	
				}
			}
			
		} while (!numeroEncontrado);
		
		System.out.println(TesteUltimaVisita.main(numTelefoneDigitado));
		
		sc.close();
	}

}
