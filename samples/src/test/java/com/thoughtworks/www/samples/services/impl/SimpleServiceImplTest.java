package com.thoughtworks.www.samples.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleServiceImplTest {
    @InjectMocks
    SimpleServiceImpl simpleService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testHelloWorld() throws Exception {
        assertThat(simpleService.helloWorld(), is("Hello earth!"));
    }
}