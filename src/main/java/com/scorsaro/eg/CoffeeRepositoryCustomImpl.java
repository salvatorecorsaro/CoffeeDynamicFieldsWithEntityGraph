package com.scorsaro.eg;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link CoffeeRepositoryCustom} interface.
 * This class provides custom repository methods for the {@link Coffee} entity.
 */
public class CoffeeRepositoryCustomImpl implements CoffeeRepositoryCustom {

    /**
     * The {@link EntityManager} used to interact with the database.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds all {@link Coffee} entities with the specified fields.
     *
     * @param fields the list of fields to select
     * @return a list of {@link Coffee} entities with the selected fields
     */
    @Override
    public List<Coffee> findAllWithSelectedFields(List<String> fields) {
        // Create a CriteriaBuilder and CriteriaQuery for Tuple
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Coffee> root = cq.from(Coffee.class);

        // Select the specified fields using multiselect and map them to the root entity
        cq.multiselect(fields.stream().map(root::get).collect(Collectors.toList()));

        // Execute the query and retrieve the results as a list of Tuples
        List<Tuple> results = entityManager.createQuery(cq).getResultList();

        // Map the Tuples to Coffee entities
        return results.stream().map(tuple -> {
            Coffee coffee = new Coffee();
            for (int i = 0; i < fields.size(); i++) {
                // Set the value of each field in the Coffee entity
                setCoffeeField(coffee, fields.get(i), tuple.get(i));
            }
            return coffee;
        }).toList();
    }

    /**
     * Sets the value of a specific field in a {@link Coffee} entity.
     *
     * @param coffee the {@link Coffee} entity to set the field value
     * @param field  the name of the field to set
     * @param value  the value to set for the field
     */
    private void setCoffeeField(Coffee coffee, String field, Object value) {
        switch (field) {
            case "id" -> coffee.setId((Long) value);
            case "name" -> coffee.setName((String) value);
            case "origin" -> coffee.setOrigin((String) value);
            case "roast" -> coffee.setRoast((String) value);
            case "flavor" -> coffee.setFlavor((String) value);
            case "price" -> coffee.setPrice((Double) value);
        }
    }
}