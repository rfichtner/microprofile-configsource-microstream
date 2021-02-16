package one.fichtner.microprofile.config;

import java.util.HashMap;

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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

public class MicroStreamConfigSource implements ConfigSource, AutoCloseable {

	private final Map<String, String> properties = new HashMap<>();
	private final EmbeddedStorageManager storageManager;

	public MicroStreamConfigSource() {
		this.storageManager = EmbeddedStorage.start(this.properties);
	}

	@Override
	public String getValue(final String key) {
		if (this.properties.containsKey(key)) {
			return this.properties.get(key);
		}
		return null;
	}

	@Override
	public String getName() {
		return this.getClass().getCanonicalName();
	}

	@Override
	public Set<String> getPropertyNames() {
		return new HashSet<>(this.properties.keySet());
	}

	public void setProperty(final String key, final String value) {
		this.properties.put(key, value);
		this.storageManager.storeRoot();
	}

	@Override
	public void close() {
		this.storageManager.shutdown();

	}

}
