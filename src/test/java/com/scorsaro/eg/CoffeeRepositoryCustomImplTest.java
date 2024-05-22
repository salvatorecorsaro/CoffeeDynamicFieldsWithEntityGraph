package com.scorsaro.eg;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CoffeeRepositoryCustomImplTest {

    @InjectMocks
    private CoffeeRepositoryCustomImpl coffeeRepositoryCustom;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Tuple> criteriaQuery;

    @Mock
    private Root<Coffee> root;

    @Mock
    private TypedQuery<Tuple> query;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllWithSelectedFields_WithValidFields_ReturnsCoffeeList() {
        // Arrange
        List<String> fields = List.of("id", "name", "price");
        List<Tuple> tuples = new ArrayList<>();
        Tuple tuple1 = mock(Tuple.class);
        when(tuple1.get(0)).thenReturn(1L);
        when(tuple1.get(1)).thenReturn("Coffee 1");
        when(tuple1.get(2)).thenReturn(10.0);
        tuples.add(tuple1);
        Tuple tuple2 = mock(Tuple.class);
        when(tuple2.get(0)).thenReturn(2L);
        when(tuple2.get(1)).thenReturn("Coffee 2");
        when(tuple2.get(2)).thenReturn(20.0);
        tuples.add(tuple2);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createTupleQuery()).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Coffee.class)).thenReturn(root);
        when(criteriaQuery.multiselect((Selection<?>) any())).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(tuples);

        // Act
        List<Coffee> coffees = coffeeRepositoryCustom.findAllWithSelectedFields(fields);

        // Assert
        assertEquals(2, coffees.size());
        Coffee coffee1 = coffees.get(0);
        assertEquals(1L, coffee1.getId());
        assertEquals("Coffee 1", coffee1.getName());
        assertEquals(10.0, coffee1.getPrice());
        Coffee coffee2 = coffees.get(1);
        assertEquals(2L, coffee2.getId());
        assertEquals("Coffee 2", coffee2.getName());
        assertEquals(20.0, coffee2.getPrice());
        verify(entityManager).getCriteriaBuilder();
        verify(criteriaBuilder).createTupleQuery();
        verify(criteriaQuery).from(Coffee.class);
        verify(entityManager).createQuery(criteriaQuery);
        verify(query).getResultList();
    }

    @Test
    void findAllWithSelectedFields_WithDifferentFields_ReturnsCoffeeList() {
        // Arrange
        List<String> fields = List.of("id", "name", "origin", "roast");
        List<Tuple> tuples = new ArrayList<>();
        Tuple tuple1 = mock(Tuple.class);
        when(tuple1.get(0)).thenReturn(1L);
        when(tuple1.get(1)).thenReturn("Coffee 1");
        when(tuple1.get(2)).thenReturn("Colombia");
        when(tuple1.get(3)).thenReturn("Medium");
        tuples.add(tuple1);
        Tuple tuple2 = mock(Tuple.class);
        when(tuple2.get(0)).thenReturn(2L);
        when(tuple2.get(1)).thenReturn("Coffee 2");
        when(tuple2.get(2)).thenReturn("Ethiopia");
        when(tuple2.get(3)).thenReturn("Light");
        tuples.add(tuple2);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createTupleQuery()).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Coffee.class)).thenReturn(root);
        when(criteriaQuery.multiselect((Selection<?>) any())).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(tuples);

        // Act
        List<Coffee> coffees = coffeeRepositoryCustom.findAllWithSelectedFields(fields);

        // Assert
        assertEquals(2, coffees.size());
        Coffee coffee1 = coffees.get(0);
        assertEquals(1L, coffee1.getId());
        assertEquals("Coffee 1", coffee1.getName());
        assertEquals("Colombia", coffee1.getOrigin());
        assertEquals("Medium", coffee1.getRoast());
        Coffee coffee2 = coffees.get(1);
        assertEquals(2L, coffee2.getId());
        assertEquals("Coffee 2", coffee2.getName());
        assertEquals("Ethiopia", coffee2.getOrigin());
        assertEquals("Light", coffee2.getRoast());
    }

    @Test
    void findAllWithSelectedFields_WithEmptyFields_ReturnsEmptyCoffeeList() {
        // Arrange
        List<String> fields = new ArrayList<>();
        List<Tuple> tuples = new ArrayList<>();

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createTupleQuery()).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Coffee.class)).thenReturn(root);
        when(criteriaQuery.multiselect((Selection<?>) any())).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(tuples);

        // Act
        List<Coffee> coffees = coffeeRepositoryCustom.findAllWithSelectedFields(fields);

        // Assert
        assertEquals(0, coffees.size());

    }

    @Test
    void findAllWithSelectedFields_WithSingleField_ReturnsCoffeeList() {
        // Arrange
        List<String> fields = List.of("name");
        List<Tuple> tuples = new ArrayList<>();
        Tuple tuple1 = mock(Tuple.class);
        when(tuple1.get(0)).thenReturn("Coffee 1");
        tuples.add(tuple1);
        Tuple tuple2 = mock(Tuple.class);
        when(tuple2.get(0)).thenReturn("Coffee 2");
        tuples.add(tuple2);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createTupleQuery()).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Coffee.class)).thenReturn(root);
        when(criteriaQuery.multiselect((Selection<?>) any())).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(tuples);

        // Act
        List<Coffee> coffees = coffeeRepositoryCustom.findAllWithSelectedFields(fields);

        // Assert
        assertEquals(2, coffees.size());
        Coffee coffee1 = coffees.get(0);
        assertEquals("Coffee 1", coffee1.getName());
        Coffee coffee2 = coffees.get(1);
        assertEquals("Coffee 2", coffee2.getName());
    }
}