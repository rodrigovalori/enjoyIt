package br.com.fiap.teste;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TesteFrequenciaVisitas {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			String jpql = "select COUNT(c.dataComanda) from Comanda c, Consumidor s where c.dataComanda between '01/03/2022 23:51:35,545000000' and '31/03/2022 23:51:35,545000000' and s.numeroTelefone = 1123456789";

			TypedQuery<Long> typedQuery = em.createQuery(jpql, Long.class);

			Long comanda = typedQuery.getSingleResult();

			System.out.println(comanda);

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
