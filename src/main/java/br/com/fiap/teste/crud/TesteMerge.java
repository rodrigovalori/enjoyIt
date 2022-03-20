package br.com.fiap.teste.crud;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.fiap.entity.Consumidor;

public class TesteMerge {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Consumidor consumidor = em.find(Consumidor.class, 71985769826l);

			consumidor.setNumeroTelefone(18922108418l);

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
