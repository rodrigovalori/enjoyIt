package br.com.fiap.teste.custom;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteTicketMedio {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery(
					"SELECT ROUND(SUM(TB_CONSUMO.nr_litros_consumidos * TB_CONSUMO.nr_preco_por_litro)/COUNT(TB_COMANDA.id_comanda), 2)\n"
							+ "FROM TB_COMANDA\n" + "         JOIN TB_CONSUMO\n"
							+ "              ON TB_COMANDA.id_comanda = TB_CONSUMO.id_comanda\n"
							+ "WHERE TB_COMANDA.nr_telefone = 1123456789\n" + "  AND TB_CONSUMO.id_comanda = 1");

			BigDecimal result = (BigDecimal) query.getSingleResult();

			System.out.println(result);

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
	}
}
