package one.fichtner.microprofile.config;

/*-
 * #%L
 * microprofile-configsource-microstream
 * %%
 * Copyright (C) 2021 Richard Fichtner
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;

@TestMethodOrder(OrderAnnotation.class)
class MicroStreamConfigSourceTest {

	@Order(1)
	@Test
	void configure() {

		final MicroStreamConfigSource MSconfigSource = new MicroStreamConfigSource();
		MSconfigSource.setProperty("my.prop", "1234");

		final SmallRyeConfig config = new SmallRyeConfigBuilder().addDefaultSources().addDefaultInterceptors()
				.withSources(MSconfigSource).build();

		assertEquals("1234", config.getRawValue("my.prop"));

		MSconfigSource.close();

	}

	@Order(2)
	@Test
	void readFromStore() {

		final MicroStreamConfigSource MSconfigSource = new MicroStreamConfigSource();

		final SmallRyeConfig config = new SmallRyeConfigBuilder().addDefaultSources().addDefaultInterceptors()
				.withSources(MSconfigSource).build();

		assertEquals("1234", config.getRawValue("my.prop"));

		MSconfigSource.close();

	}

}
