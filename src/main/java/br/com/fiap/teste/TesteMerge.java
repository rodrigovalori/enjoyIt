package br.com.fiap.teste;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.fiap.entity.Consumidor;

public class TesteMerge {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Consumidor consumidor = em.find(Consumidor.class, 119876543);

			consumidor.setNomeConsumidor("Fulano");

			em.getTransaction().begin();

			em.merge(consumidor);

			em.getTransaction().commit();

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
