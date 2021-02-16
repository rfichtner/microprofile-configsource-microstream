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

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;

class ConfigConfigSourceTest {

	@Test
	void configure() {

		final Map<String, String> given = new HashMap<>();
		given.put("my.prop", "1234");

		final SmallRyeConfig config = new SmallRyeConfigBuilder().addDefaultSources().addDefaultInterceptors()
				.withSources(new InMemoryConfigSource(given)).build();

		assertEquals("1234", config.getRawValue("my.prop"));

	}

}
