package br.com.fiap.teste.custom;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TesteUltimaVisita {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			String jpql = "select MAX(c.dataComanda) from Comanda c, Consumidor s where s.numeroTelefone = 1123456789";

			TypedQuery<Calendar> typedQuery = em.createQuery(jpql, Calendar.class);

			Calendar comanda = typedQuery.getSingleResult();

			System.out.println(comanda.getTime());

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
