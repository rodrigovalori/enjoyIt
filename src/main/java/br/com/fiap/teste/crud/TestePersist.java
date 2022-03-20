package br.com.fiap.teste.crud;

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
			
			// ################################################## PERSISTINDO CONSUMIDORES ##################################################

			List<Consumidor> listaConsumidores = new ArrayList<Consumidor>();

			Consumidor consumidor1 = new Consumidor(93995264333l, "Jéssica Larissa Maya Bernardes",
					"jessica_larissa_bernardes@msaengenharia.com.br",
					new GregorianCalendar(1944, Calendar.FEBRUARY, 20), 55498352581l, "Mulher", 68372064,
					"Rua Lucindo Câmara, 11", "Ibiza", "Altamira", "SP");
			listaConsumidores.add(consumidor1);
			
			Consumidor consumidor2 = new Consumidor(61997881321l, "Henrique Fernando Almada",
					"henrique_almada@sabereler.com.br",
					new GregorianCalendar(1954, Calendar.OCTOBER, 13), 70662103521l, "Homem", 72500433,
					"Quadra QR 100 Conjunto X", "Santa Maria", "Brasília", "DF");
			listaConsumidores.add(consumidor2);
			
			Consumidor consumidor3 = new Consumidor(71985769826l, "Letícia Raimunda Teixeira",
					"leticia-teixeira77@clcimoveis.com.br",
					new GregorianCalendar(1997, Calendar.AUGUST, 8), 91682384446L, "Mulher", 40325230,
					"Vila Lapinha, 18", "Liberdade", "Salvador", "BA");
			listaConsumidores.add(consumidor3);
			
			// ################################################## PERSISTINDO COMANDAS ##################################################
			
			List<Comanda> listaComandas = new ArrayList<Comanda>();

			Comanda comanda1 = new Comanda(Calendar.getInstance(), 23.3, listaConsumidores.get(0));
			listaComandas.add(comanda1);
			
			Comanda comanda2 = new Comanda(Calendar.getInstance(), 49.9, listaConsumidores.get(1));
			listaComandas.add(comanda2);
			
			Comanda comanda3 = new Comanda(Calendar.getInstance(), 38.95, listaConsumidores.get(2));
			listaComandas.add(comanda3);
			
			// ################################################## PERSISTINDO CONSUMOS ##################################################

			List<Consumo> listaConsumos = new ArrayList<Consumo>();

			Consumo consumo1 = new Consumo(3.0, 6.1, Estilo.STOUT, Marca.AMSTEL, listaConsumidores.get(0), listaComandas.get(0));
			listaConsumos.add(consumo1);
			
			Consumo consumo2 = new Consumo(0.5, 10.3, Estilo.IPA, Marca.COLORADO, listaConsumidores.get(0), listaComandas.get(0));
			listaConsumos.add(consumo2);
			
			Consumo consumo3 = new Consumo(1.3, 7.8, Estilo.PORTER, Marca.BUDWEISER, listaConsumidores.get(1), listaComandas.get(1));
			listaConsumos.add(consumo3);
			
			Consumo consumo4 = new Consumo(2.8, 14.2, Estilo.STOUT, Marca.BADEN_BADEN, listaConsumidores.get(1), listaComandas.get(1));
			listaConsumos.add(consumo4);
			
			Consumo consumo5 = new Consumo(1.9, 4.3, Estilo.TRIPEL, Marca.DEVASSA, listaConsumidores.get(2), listaComandas.get(2));
			listaConsumos.add(consumo5);
			
			Consumo consumo6 = new Consumo(2.7, 11.4, Estilo.STOUT, Marca.HEINEKEN, listaConsumidores.get(2), listaComandas.get(2));
			listaConsumos.add(consumo6);

			em.getTransaction().begin();

			for (Consumidor consumidor : listaConsumidores) {
				em.persist(consumidor);
			}
			
			for (Comanda comanda : listaComandas) {
				em.persist(comanda);
			}

			for (Consumo consumo : listaConsumos) {
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
