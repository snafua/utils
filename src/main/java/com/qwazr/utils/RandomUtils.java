/*
 * Copyright 2017 Emmanuel Keller / QWAZR
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
package com.qwazr.utils;

import org.apache.commons.text.CharacterPredicate;
import org.apache.commons.text.RandomStringGenerator;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

	public enum Predicates implements CharacterPredicate {

		ALPHABETIC {
			@Override
			public boolean test(int codePoint) {
				return Character.isAlphabetic(codePoint);
			}
		}
	}

	final public static RandomStringGenerator ALPHABETIC = new RandomStringGenerator.Builder().filteredBy(
			Predicates.ALPHABETIC).build();

	public static String alphanumeric(int length) {
		return ALPHABETIC.generate(length);
	}
}
