package com.scorsaro.eg;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

import java.util.ArrayList;
import java.util.List;

public class CoffeeRepositoryCustomImpl implements CoffeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Coffee> findAllWithSelectedFields(List<String> fields) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Coffee> root = cq.from(Coffee.class);

        List<Selection<?>> selections = new ArrayList<>();
        for (String field : fields) {
            selections.add(root.get(field));
        }
        cq.multiselect(selections);

        List<Tuple> results = entityManager.createQuery(cq).getResultList();

        List<Coffee> coffees = new ArrayList<>();
        for (Tuple tuple : results) {
            Coffee coffee = new Coffee();
            int index = 0;
            for (String field : fields) {
                switch (field) {
                    case "id":
                        coffee.setId((Long) tuple.get(index));
                        break;
                    case "name":
                        coffee.setName((String) tuple.get(index));
                        break;
                    case "origin":
                        coffee.setOrigin((String) tuple.get(index));
                        break;
                    case "roast":
                        coffee.setRoast((String) tuple.get(index));
                        break;
                    case "flavor":
                        coffee.setFlavor((String) tuple.get(index));
                        break;
                    case "price":
                        coffee.setPrice((Double) tuple.get(index));
                        break;
                }
                index++;
            }
            coffees.add(coffee);
        }
        return coffees;
    }
}
