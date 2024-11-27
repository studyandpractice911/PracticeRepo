package com.practice.repo.tests;

import com.practice.repo.BaseTest;
import com.practice.repo.components.fakeRestApi.FakeRESTApi;
import com.practice.repo.components.tutorialsPoint.TutorialsPointAlerts;
import com.practice.repo.components.tutorialsPoint.TutorialsPointRegisterUser;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

public class DummyTest extends BaseTest {

    @Value("${book.id}")
    protected String BOOK_ID;
    @Value("${first.name}")
    protected String FIRST_NAME;
    @Value("${last.name}")
    protected String LAST_NAME;

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
