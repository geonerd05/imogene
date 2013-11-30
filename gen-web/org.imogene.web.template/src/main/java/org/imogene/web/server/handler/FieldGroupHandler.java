package org.imogene.web.server.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.imogene.lib.common.constants.CommonConstants;
import org.imogene.lib.common.constants.CriteriaConstants;
import org.imogene.lib.common.criteria.BasicCriteria;
import org.imogene.lib.common.criteria.ImogConjunction;
import org.imogene.lib.common.criteria.ImogJunction;
import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.model.FieldGroup;
import org.imogene.lib.common.model.FieldGroupDao;
import org.imogene.lib.common.profile.FieldGroupProfile;
import org.imogene.lib.common.profile.FieldGroupProfileDao;
import org.imogene.web.server.security.ImogBeanFilterHandler;
import org.imogene.web.server.util.HttpSessionUtil;
import org.imogene.web.server.util.ServerConstants;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data handler for the FieldGroup beans
 * 
 * @author Medes-IMPS
 */
public class FieldGroupHandler {

	private FieldGroupDao dao;
	/* FieldGroupProfileDao for Foreign Key Deletion */
	private FieldGroupProfileDao fieldGroupProfileFieldGroupDao;

	/**
	 * Loads the entity with the specified id
	 * 
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional
	public FieldGroup findById(String entityId) {
		return dao.load(entityId);
	}

	/**
	 * Loads the entity with the specified id
	 * 
	 * @param entityId the entity id
	 * @return the entity or null
	 */
	@Transactional(readOnly = true)
	public FieldGroup getById(String entityId) {
		return dao.getById(entityId);
	}

	/**
	 * Saves or updates the entity
	 * 
	 * @param entity the entity to be saved or updated
	 * @param isNew true if it is a new entity added for the first time.
	 */
	@Transactional
	public void save(FieldGroup entity, boolean isNew) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);

		if (entity != null) {

			if (isNew) {
				entity.setCreated(new Date(System.currentTimeMillis()));
				entity.setCreatedBy(actor.getLogin());
			}

			entity.setModified(new Date(System.currentTimeMillis()));
			entity.setModifiedBy(actor.getLogin());
			entity.setModifiedFrom(CommonConstants.IS_WEB);

			dao.saveOrUpdate(entity, isNew);
		}
	}

	/**
	 * Lists the entities of type FieldGroup
	 * 
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of fieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listFieldGroup(String sortProperty, boolean sortOrder) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);

		List<FieldGroup> beans = dao.load(sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type FieldGroup
	 * 
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @return list of fieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listFieldGroup(int i, int j, String sortProperty, boolean sortOrder) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);

		List<FieldGroup> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type FieldGroup
	 * 
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria
	 * @return list of fieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listFieldGroup(int i, int j, String sortProperty, boolean sortOrder, ImogJunction criterions) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<FieldGroup> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the entities of type FieldGroup
	 * 
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterions request criteria
	 * @return list of fieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listFieldGroup(int i, int j, String sortProperty, boolean sortOrder,
			List<BasicCriteria> criterions) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);

		ImogJunction junctionForCrit = new ImogConjunction();
		if (criterions != null) {
			for (BasicCriteria crit : criterions)
				junctionForCrit.add(crit);
		}
		junction.add(junctionForCrit);

		List<FieldGroup> beans = dao.load(i, j, sortProperty, sortOrder, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type FieldGroup
	 * 
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param criterion request criteria
	 * @param property the property which is not affected
	 * @return list of fieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listNonAffectedFieldGroup(int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<FieldGroup> beans = dao.loadNonAffected(i, j, sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Lists the non affected entities of type FieldGroup
	 * 
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of fieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listNonAffectedFieldGroup(int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedFieldGroup(i, j, sortProperty, sortOrder, null, property);
	}

	/**
	 * Used when FieldGroup is involved in a Relation 1 <-> 1 Association and is the ReverseRelationField of the
	 * Relation Return all instance of FieldGroup non affected regarding specified property.
	 * 
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param ImogJunction request criteria
	 * @param property the property which is not affected
	 * @return list of fieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listNonAffectedFieldGroupReverse(int i, int j, String sortProperty, boolean sortOrder,
			ImogJunction criterions, String property) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		List<FieldGroup> beans = dao.loadNonAffectedReverse(i, j, sortProperty, sortOrder, property, junction);

		return beans;
	}

	/**
	 * Used when FieldGroup is involved in a Relation 1 <-> 1 Association and is the ReverseRelationField of the
	 * Relation Return all instance of FieldGroup non affected regarding specified property.
	 * 
	 * @param i first index to retrieve
	 * @param j nb of items to retrieve
	 * @param sortProperty the property used to sort the collection
	 * @param sortOrder true for an ascendant sort
	 * @param property the property which is not affected
	 * @return list of fieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listNonAffectedFieldGroupReverse(int i, int j, String sortProperty, boolean sortOrder,
			String property) {
		return listNonAffectedFieldGroupReverse(i, j, sortProperty, sortOrder, null, property);
	}

	/**
	 * Gets an empty list of FieldGroup
	 * 
	 * @return an empty list of FieldGroup
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> getFieldGroupEmptyList() {
		return new ArrayList<FieldGroup>();
	}

	/**
	 * Counts the number of fieldGroup in the database
	 * 
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countFieldGroup() {
		return countFieldGroup(null);
	}

	/**
	 * Counts the number of fieldGroup in the database, that match the criteria
	 * 
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countFieldGroup(ImogJunction criterions) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.count(junction);
	}

	/**
	 * Counts the number of non affected fieldGroup entities in the database
	 * 
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedFieldGroup(String property, ImogJunction criterions) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);

		return dao.countNonAffected(property, junction);
	}

	/**
	 * Counts the number of non affected fieldGroup entities in the database
	 * 
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedFieldGroup(String property) {
		return countNonAffectedFieldGroup(property, null);
	}

	/**
	 * Counts the number of non affected fieldGroup entities in the database
	 * 
	 * @param property the property which is not affected
	 * @param criterion request criteria
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedFieldGroupReverse(String property, ImogJunction criterions) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);
		if (criterions != null)
			junction.add(criterions);
		return dao.countNonAffectedReverse(property, junction);
	}

	/**
	 * Counts the number of non affected fieldGroup entities in the database
	 * 
	 * @param property the property which is not affected
	 * @return the count
	 */
	@Transactional(readOnly = true)
	public Long countNonAffectedFieldGroupReverse(String property) {
		return countNonAffectedFieldGroupReverse(property, null);
	}

	/**
	 * Deletes a group of entities identified by their IDs
	 * 
	 * @param ids Entities to delete IDs
	 * @return The list of deleted entities IDs
	 */
	@Transactional
	public void delete(Set<FieldGroup> entities) {
		if (entities != null) {
			for (FieldGroup entity : entities)
				delete(entity);
		}
	}

	/**
	 * Removes the specified entity from the database
	 * 
	 * @param entity The entity to be deleted
	 */
	@Transactional
	public void delete(FieldGroup entity) {

		// Delete foreign key reference for field FieldGroup of entity FieldGroupProfile

		ImogJunction searchCriterionsForFieldGroupProfileFieldGroup = new ImogConjunction();
		BasicCriteria criteriaFieldGroupProfileFieldGroup = new BasicCriteria();
		criteriaFieldGroupProfileFieldGroup.setOperation(CriteriaConstants.RELATIONFIELD_OPERATOR_EQUAL);
		criteriaFieldGroupProfileFieldGroup.setValue(entity.getId());
		criteriaFieldGroupProfileFieldGroup.setField("fieldGroup.id");
		searchCriterionsForFieldGroupProfileFieldGroup.add(criteriaFieldGroupProfileFieldGroup);

		List<FieldGroupProfile> resultForFieldGroupProfileFieldGroup = fieldGroupProfileFieldGroupDao.load("modified",
				false, searchCriterionsForFieldGroupProfileFieldGroup);
		if (resultForFieldGroupProfileFieldGroup != null) {
			for (FieldGroupProfile foreignEntity : resultForFieldGroupProfileFieldGroup) {
				foreignEntity.setModified(new Date());
				foreignEntity.setFieldGroup(null);
				fieldGroupProfileFieldGroupDao.saveOrUpdate(foreignEntity, false);
			}
		}

		dao.delete(entity);
	}

	/**
	 * Lists the entities of type FieldGroup for the CSV export
	 */
	@Transactional(readOnly = true)
	public List<FieldGroup> listForCsv(String sortProperty, boolean sortOrder, String name) {

		ImogActor actor = (ImogActor) HttpSessionUtil.getHttpSession().getAttribute(ServerConstants.SESSION_USER);
		ImogJunction junction = createFilterJuntion(actor);

		if (name != null && !name.isEmpty()) {
			BasicCriteria criteria = new BasicCriteria();
			criteria.setOperation(CriteriaConstants.STRING_OPERATOR_CONTAINS);
			criteria.setField("name");
			criteria.setValue(name);
			junction.add(criteria);
		}

		List<FieldGroup> beans = dao.load(sortProperty, sortOrder, junction);
		List<FieldGroup> securedBeans = ImogBeanFilterHandler.getInstance().getFilter()
				.<FieldGroup> toSecure(beans, actor);
		return securedBeans;
	}

	/**
	 * Creates a junction based on the filter field declarations, for the current actor.
	 * 
	 * @param actor the current actor
	 */
	private ImogJunction createFilterJuntion(ImogActor actor) {
		ImogConjunction filterConjunction = new ImogConjunction();
		return filterConjunction;
	}

	/**
	 * Setter for bean injection
	 * 
	 * @param dao the FieldGroup Dao
	 */
	public void setDao(FieldGroupDao dao) {
		this.dao = dao;
	}

	/**
	 * Setter for bean injection
	 * 
	 * @param fieldGroupProfileFieldGroupDao the FieldGroupProfile Dao
	 */
	public void setFieldGroupProfileFieldGroupDao(FieldGroupProfileDao fieldGroupProfileFieldGroupDao) {
		this.fieldGroupProfileFieldGroupDao = fieldGroupProfileFieldGroupDao;
	}

}
