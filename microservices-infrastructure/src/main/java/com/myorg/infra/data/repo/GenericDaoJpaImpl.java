package com.myorg.infra.data.repo;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public class GenericDaoJpaImpl<T, PK extends Serializable> {

	@PersistenceContext
	protected EntityManager entityManager;

	public T create(T t) {
		this.entityManager.persist(t);
		return t;
	}

	public T update(T t) {
		return this.entityManager.merge(t);
	}
	@Transactional
	public void delete(T t) {
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}
}