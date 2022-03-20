package br.com.fiap.teste;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.fiap.entity.Consumidor;

public class TesteUpdate {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Consumidor consumidor = em.find(Consumidor.class, 93995264333l);

			em.getTransaction().begin();

			consumidor.setEmail("jessica_larissa@yahoo.com.br");

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
