package com.market.back.services.impl;

import com.market.back.models.categories.*;
import com.market.back.repositories.BookRepository;
import com.market.back.repositories.ClothesRepository;
import com.market.back.repositories.HouseholdRepository;
import com.market.back.repositories.ToyRepository;
import com.market.back.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private ToyRepository toyRepository;

    @Mock
    private HouseholdRepository householdRepository;

    @Mock
    private ClothesRepository clothesRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getAllProduct() {
        MockitoAnnotations.openMocks(this);

        // Создаем тестовые данные
        List<Book> books = List.of(new Book("1", "test", "", "", "", "", 2.0));
        List<Toy> toys = List.of(new Toy("2", "test2", "", "", 3.0));
        List<Household> households = List.of(new Household("3", "test3", "", "", "", 5.0));
        List<Clothes> clothesList = List.of(new Clothes("6", "test4", "", "", "", "", 55.0));

        // Устанавливаем поведение заглушек репозиториев при вызове метода findAll
        when(bookRepository.findAll()).thenReturn(books);
        when(toyRepository.findAll()).thenReturn(toys);
        when(householdRepository.findAll()).thenReturn(households);
        when(clothesRepository.findAll()).thenReturn(clothesList);

        // Вызываем метод, который мы хотим протестировать
        List<Products> result = productService.getAllProduct();

        // Проверяем, что метод findAll вызывался у каждого репозитория
        verify(bookRepository).findAll();
        verify(toyRepository).findAll();
        verify(householdRepository).findAll();
        verify(clothesRepository).findAll();

        // Проверяем, что результат содержит все продукты из репозиториев
        List<Products> expected = new ArrayList<>();
        expected.addAll(books);
        expected.addAll(toys);
        expected.addAll(households);
        expected.addAll(clothesList);

        // Добавьте здесь дополнительные проверки, если необходимо

        // Проверяем, что результат соответствует ожидаемому
        assertEquals(expected, result);
    }

    @Test
    void getCategories() {
    }

    @Test
    void getQuantityOfOneProduct() {
    }

    @Test
    void addIdProductInProductStock() {
    }

    @Test
    void updateQuantityOfProduct() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void deleteProduct() {
    }
}