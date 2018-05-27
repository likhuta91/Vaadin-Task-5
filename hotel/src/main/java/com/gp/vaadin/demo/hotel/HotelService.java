package com.gp.vaadin.demo.hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

public class HotelService {

	private static HotelService instance;
	private static final Logger LOGGER = Logger.getLogger(HotelService.class.getName());
	private Session session = null;

	private HotelService() {
	}

	public static HotelService getInstance() {

		if (instance == null) {
			instance = new HotelService();
		}
		return instance;
	}

	public synchronized List<Hotel> findAll() {
		return findAll(null, null);
	}

	public synchronized List<Hotel> findAll(String nameFilterValue, String addressFilterValue) {

		try {
			session = HibernateSessionFactory.getHibernateSession();
			ArrayList<Hotel> arrayList = new ArrayList<>();
			List<Hotel> allHotel = null;

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Hotel> criteria = builder.createQuery(Hotel.class);
			criteria.distinct(true);
			Root<Hotel> rootHotel = criteria.from(Hotel.class);
			Predicate predicate1 = builder.like(rootHotel.get("name"), "%" + nameFilterValue.toLowerCase() + "%");
			Predicate predicate2 = builder.like(rootHotel.get("address"), "%" + addressFilterValue.toLowerCase() + "%");
			criteria.select(rootHotel);
			if (!nameFilterValue.isEmpty() && !addressFilterValue.isEmpty()) {

				criteria.where(predicate1, predicate2);
			} else if (!nameFilterValue.isEmpty() && addressFilterValue.isEmpty()) {

				criteria.where(predicate1);
			} else if (nameFilterValue.isEmpty() && !addressFilterValue.isEmpty()) {

				criteria.where(predicate2);
			}

			criteria.from(Hotel.class);
			TypedQuery<Hotel> q = session.createQuery(criteria);

			allHotel = q.getResultList();

			for (Hotel hotel : allHotel) {

				try {
					if (hotel.getCategory() != null) {
						hotel.setCategory(hotel.getCategory().clone());
					}
					arrayList.add(hotel.clone());

				} catch (CloneNotSupportedException ex) {
					LOGGER.log(Level.SEVERE, null, ex);
				}

			}

			Collections.sort(arrayList, new Comparator<Hotel>() {

				@Override
				public int compare(Hotel o1, Hotel o2) {
					return (int) (o2.getId() - o1.getId());
				}
			});
			return arrayList;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	public synchronized void delete(Set<Hotel> value) {

		try {
			session = HibernateSessionFactory.getHibernateSession();

			for (Hotel hotel : value) {
				session.beginTransaction();
				Hotel h = session.get(Hotel.class, hotel.getId());
				session.delete(h);
				session.flush();
				session.getTransaction().commit();
			}

		} catch (Exception e) {
			session.getTransaction().rollback();
			LOGGER.log(Level.SEVERE, "Hotel is not delete." + e.getMessage());

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public synchronized void save(Hotel entry) {

		try {

			session = HibernateSessionFactory.getHibernateSession();

			if (entry == null) {
				LOGGER.log(Level.SEVERE, "Hotel is null.");
				return;
			}

			session.beginTransaction();
			session.merge(entry);
			session.flush();
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			LOGGER.log(Level.SEVERE, "Hotel is not save." + e.getMessage());

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

}
