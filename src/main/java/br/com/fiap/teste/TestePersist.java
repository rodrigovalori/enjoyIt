package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.fiap.entity.Comanda;
import br.com.fiap.entity.Consumidor;
import br.com.fiap.entity.Consumo;
import br.com.fiap.entity.enums.Estilo;
import br.com.fiap.entity.enums.Marca;

public class TestePersist {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Consumidor consumidor = new Consumidor(1123456789, "Ciclano", "ciclano@gmail.com",
					new GregorianCalendar(2027, Calendar.DECEMBER, 1), 1234567, "Homem", 19876, "Rua Teste, 10",
					"Bairro", "Cidade", "Estado");
			
			Comanda comanda = new Comanda(Calendar.getInstance(), 257.10, consumidor);
			
			List<Consumo> consumos = new ArrayList<Consumo>();
			
			Consumo consumo1 = new Consumo(2.3, 1.5, Estilo.LAGER, Marca.AMSTEL, consumidor, comanda);
			consumos.add(consumo1);
			Consumo consumo2 = new Consumo(2.6, 2.0, Estilo.IPA, Marca.BRAHMA, consumidor, comanda);
			consumos.add(consumo2);
			Consumo consumo3 = new Consumo(1.0, 1.5, Estilo.LAGER, Marca.AMSTEL, consumidor, comanda);
			consumos.add(consumo3);

			em.getTransaction().begin();

			em.persist(consumidor);
			em.persist(comanda);
			
			for (Consumo consumo : consumos) {
				em.persist(consumo);
			}
			
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
