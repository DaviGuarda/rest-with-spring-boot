package br.com.davidev.unittests.mockito.services;


import br.com.davidev.data.vo.v1.BookVO;
import br.com.davidev.exceptions.RequiredObjectIsNullException;
import br.com.davidev.model.Book;
import br.com.davidev.repositories.BookRepository;
import br.com.davidev.services.BookServices;
import br.com.davidev.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesTest {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    MockBook input;

    @Mock
    BookRepository repository;

    @InjectMocks
    BookServices services;

    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = services.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("2023-12-31", dateFormat.format(result.getLaunchDate()));
        assertEquals("1", result.getPrice().toString());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> services.create(null));

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> services.update(null));

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testFindAll(){
        List<Book> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var books = services.findAll();
        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());
        assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1", bookOne.getAuthor());
        assertEquals("2023-12-31", dateFormat.format(bookOne.getLaunchDate()));
        assertEquals("1", bookOne.getPrice().toString());
        assertEquals("Title Test1", bookOne.getTitle());

        var bookFour = books.get(4);
        assertNotNull(bookFour);
        assertNotNull(bookFour.getKey());
        assertNotNull(bookFour.getLinks());
        assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
        assertEquals("Author Test4", bookFour.getAuthor());
        assertEquals("2023-12-31", dateFormat.format(bookFour.getLaunchDate()));
        assertEquals("4", bookFour.getPrice().toString());
        assertEquals("Title Test4", bookFour.getTitle());

        var bookSeven = books.get(7);
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());
        assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
        assertEquals("Author Test7", bookSeven.getAuthor());
        assertEquals("2023-12-31", dateFormat.format(bookSeven.getLaunchDate()));
        assertEquals("7", bookSeven.getPrice().toString());
        assertEquals("Title Test7", bookSeven.getTitle());
    }

    @Test
    void testCreate() {
        Book entity = input.mockEntity(1);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);
        entity.setId(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = services.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("2023-12-31", dateFormat.format(result.getLaunchDate()));
        assertEquals("1", result.getPrice().toString());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void testUpdate() {
        Book entity = input.mockEntity(1);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = services.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("2023-12-31", dateFormat.format(result.getLaunchDate()));
        assertEquals("1", result.getPrice().toString());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void testDelete() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        services.delete(1L);
    }

}
