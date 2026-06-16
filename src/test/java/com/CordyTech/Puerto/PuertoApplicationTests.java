package com.CordyTech.Puerto;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Requiere PostgreSQL corriendo en localhost:5432")
class PuertoApplicationTests {

	@Test
	void contextLoads() {
	}

}
