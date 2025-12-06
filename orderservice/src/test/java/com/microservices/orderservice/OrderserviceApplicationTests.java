package com.microservices.orderservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import io.restassured.RestAssured;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderserviceApplicationTests {

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateOrder() {
		String orderRequest = """
				{
				    "skuCode": "iphone_13",
				    "price": 999.99,
				    "quantity": 1
				}
				""";
		var responseBodyString = RestAssured.given()
			.header("Content-Type", "application/json")
			.body(orderRequest)
			.when()
			.post("/api/orders")
			.then()
			.statusCode(201)
			.extract()
			.body().asString();

		assertThat(responseBodyString, Matchers.equalTo("Order Placed Successfully"));

		
	}

}
