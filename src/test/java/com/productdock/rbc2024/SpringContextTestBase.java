package com.productdock.rbc2024;

import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    classes = Rbc2024Application.class)
public abstract class SpringContextTestBase {

}
