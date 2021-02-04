package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ShoeItem;

public class ShoeItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("consoleshoes");
	
	public void insertItem(ShoeItem li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List <ShoeItem> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List <ShoeItem> allItems = em.createQuery("SELECT li FROM ShoeItem li").getResultList();
		return allItems;
	}
	
	public void deleteItem(ShoeItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ShoeItem> typedQuery = em.createQuery("SELECT li FROM ShoeItem li WHERE li.brand = :selectedBrand and li.type = :selectedType and li.color = :selectedColor", ShoeItem.class);
		
		//substitute the parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedBrand", toDelete.getBrand());
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedColor", toDelete.getColor());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		ShoeItem result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public List<ShoeItem> searchForItemByBrand(String brandName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ShoeItem> typedQuery = em.createQuery("SELECT li FROM ShoeItem li WHERE li.brand = :selectedBrand", ShoeItem.class);
		typedQuery.setParameter("selectedBrand", brandName);
		
		List<ShoeItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<ShoeItem> searchForItemByType(String typeName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ShoeItem> typedQuery = em.createQuery("SELECT li FROM ShoeItem li WHERE li.type = :selectedType", ShoeItem.class);
		typedQuery.setParameter("selectedType", typeName);
		
		List<ShoeItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<ShoeItem> searchForItemByColor(String colorName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ShoeItem> typedQuery = em.createQuery("SELECT li FROM ShoeItem li WHERE li.color = :selectedColor", ShoeItem.class);
		typedQuery.setParameter("selectedColor", colorName);
		
		List<ShoeItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public ShoeItem searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ShoeItem found = em.find(ShoeItem.class, idToEdit);
		em.close();
		return found;
	}

	public void updateItem(ShoeItem toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
	
	
	
	

	
}


