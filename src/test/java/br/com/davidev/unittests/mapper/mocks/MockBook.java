package br.com.davidev.unittests.mapper.mocks;

import br.com.davidev.data.vo.v1.BookVO;
import br.com.davidev.model.Book;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> bookVOS = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            bookVOS.add(mockVO(i));
        }
        return bookVOS;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Author Test" + number);
        Date launchDate;
        try {
            launchDate = dateFormat.parse("2023-12-31");
            book.setLaunchDate(launchDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setPrice(BigDecimal.valueOf(number));
        book.setTitle("Title Test" + number);
        book.setId(number.longValue());

        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setAuthor("Author Test" + number);
        Date launchDate;
        try {
            launchDate = dateFormat.parse("2023-12-31");
            book.setLaunchDate(launchDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setPrice(BigDecimal.valueOf(number));
        book.setTitle("Title Test" + number);
        book.setKey(number.longValue());

        return book;
    }

}
