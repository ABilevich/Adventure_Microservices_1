package com.microservices.orderservice;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import com.microservices.orderservice.stubs.InventoryClientStub;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock(@ConfigureWireMock(port = 0))
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
				    "skuCode": "iphone_15",
				    "price": 1000,
				    "quantity": 1
				}
				""";

		InventoryClientStub.stubInventoryCall("iphone_15", 1);
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
