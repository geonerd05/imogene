package org.imogene.lib.common.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.profile.Profile;

public abstract class ImogActorDaoImpl<T extends ImogActor> extends ImogBeanDaoImpl<T> implements ImogActorDao<T> {

	protected ImogActorDaoImpl(Class<T> clazz) {
		super(clazz);
	}

	@Override
	public List<T> loadFromLogin(String login) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);
		Root<T> root = query.from(clazz);
		query.select(root);
		query.where(builder.equal(root.<String> get("login"), login));
		return em.createQuery(query).getResultList();
	}

	@Override
	public Set<Profile> loadProfiles(ImogActor parent) {
		if (parent == null) {
			return new HashSet<Profile>();
		}
		return parent.getProfiles();
	}

}
