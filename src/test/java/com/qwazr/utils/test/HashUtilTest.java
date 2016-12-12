/**
 * Copyright 2016 Emmanuel Keller / QWAZR
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qwazr.utils.test;

import com.qwazr.utils.HashUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.UUID;

/**
 * Created by ekeller on 12/12/2016.
 */
public class HashUtilTest {

	@Test
	public void timeBasedUuid() {
		HashSet<UUID> set = new HashSet<>();
		for (int i = 0; i < 100; i++) {
			final UUID uuid = HashUtils.newTimeBasedUUID();
			if (!set.add(uuid))
				Assert.fail("The UUID is not unique");
		}
	}
}
