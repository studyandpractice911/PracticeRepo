package com.practice.repo.tests;

import com.practice.repo.BaseTest;
import com.practice.repo.components.fakeRestApi.FakeRESTApi;
import com.practice.repo.components.tutorialsPoint.TutorialsPointAlerts;
import com.practice.repo.components.tutorialsPoint.TutorialsPointRegisterUser;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DummyTest extends BaseTest {

    @Value("${book.id}")
    protected String BOOK_ID;
    @Value("${first.name}")
    protected String FIRST_NAME;
    @Value("${last.name}")
    protected String LAST_NAME;

    @Test
    public void fib(){
        Stream.iterate(List.of(0,1), f -> List.of(f.getLast(), f.getFirst()+f.getLast()))
                .limit(20)
                .map(List::getFirst)
                .forEach(System.out::println);
    }

    @Test
    public void prime(){
        IntStream.rangeClosed(0, 20)
                .filter(dividend -> dividend > 1)
                .filter(dividend -> IntStream.range(2, dividend).noneMatch(divisor -> dividend % divisor == 0))
                .forEach(System.out::println);
    }

    @Test
    public void testA() {
        componentManager.getComponent(TutorialsPointAlerts.class).checkAlert();
        componentManager.getComponent(TutorialsPointRegisterUser.class).registerUser(FIRST_NAME, LAST_NAME);
    }

    @Test
    public void testB() {
        componentManager.getComponent(FakeRESTApi.class).getBook(BOOK_ID);
    }

}
