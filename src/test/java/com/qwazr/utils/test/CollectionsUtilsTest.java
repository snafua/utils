/*
 * Copyright 2016-2017 Emmanuel Keller / QWAZR
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

import com.qwazr.utils.CollectionsUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CollectionsUtilsTest {

	@Test
	public void collColl() {

		final Collection<Integer> collSameLeft = Arrays.asList(1, 2, 3);
		final Collection<Integer> collSameRight = Arrays.asList(1, 2, 3);
		final Collection<Integer> collOtherSameSize = Arrays.asList(3, 2, 1);
		final Collection<Integer> collOtherDifferentSize = Arrays.asList(1, 2);
		final Collection<Integer> collNull = null;

		// Same null
		Assert.assertTrue(CollectionsUtils.equals(null, collNull));
		// Same reference
		Assert.assertTrue(CollectionsUtils.equals(collSameLeft, collSameLeft));
		// Same content
		Assert.assertTrue(CollectionsUtils.equals(collSameLeft, collSameRight));
		// Null pos2
		Assert.assertFalse(CollectionsUtils.equals(collSameLeft, collNull));
		// Null pos1
		Assert.assertFalse(CollectionsUtils.equals(collNull, collSameRight));
		// Not same content
		Assert.assertFalse(CollectionsUtils.equals(collSameLeft, collOtherSameSize));
		// Not same content size
		Assert.assertFalse(CollectionsUtils.equals(collSameLeft, collOtherDifferentSize));
	}

	@Test
	public void collArray() {

		final Collection<Integer> coll = Arrays.asList(1, 2, 3);
		final Integer[] array = new Integer[] { 1, 2, 3 };
		final Integer[] arraySameSize = new Integer[] { 3, 2, 1 };
		final Integer[] arrayDifferentSize = new Integer[] { 1, 2 };
		final Integer[] arrayNull = null;

		// Same null
		Assert.assertTrue(CollectionsUtils.equals(null, arrayNull));
		// Same content
		Assert.assertTrue(CollectionsUtils.equals(coll, array));
		// Null pos2
		Assert.assertFalse(CollectionsUtils.equals(coll, arrayNull));
		// Null pos1
		Assert.assertFalse(CollectionsUtils.equals(null, array));
		// Not same content
		Assert.assertFalse(CollectionsUtils.equals(coll, arraySameSize));
		// Not same content size
		Assert.assertFalse(CollectionsUtils.equals(coll, arrayDifferentSize));
	}

	@Test
	public void mapTests() {
		final Map<Integer, Integer> mapLeft = new HashMap<>();
		final Map<Integer, Integer> mapRight = new TreeMap<>();
		final Map<Integer, Integer> mapSameSize = new HashMap<>();
		final Map<Integer, Integer> mapDifferentSize = new TreeMap<>();
		final Map<Integer, Integer> nullMap = null;

		for (int i = 0; i < 5; i++) {
			int value = RandomUtils.nextInt();
			mapLeft.put(value, value);
			mapRight.put(value, value);
			mapSameSize.put(RandomUtils.nextInt(), RandomUtils.nextInt());
			mapDifferentSize.put(value, value);
		}
		mapDifferentSize.put(RandomUtils.nextInt(), RandomUtils.nextInt());

		// Same null
		Assert.assertTrue(CollectionsUtils.equals(nullMap, nullMap));
		// Same reference
		Assert.assertTrue(CollectionsUtils.equals(mapLeft, mapLeft));
		// Same content
		Assert.assertTrue(CollectionsUtils.equals(mapLeft, mapRight));
		// Null pos2
		Assert.assertFalse(CollectionsUtils.equals(mapLeft, nullMap));
		// Null pos1
		Assert.assertFalse(CollectionsUtils.equals(nullMap, mapRight));
		// Not same content
		Assert.assertFalse(CollectionsUtils.equals(mapLeft, mapSameSize));
		// Not same content size
		Assert.assertFalse(CollectionsUtils.equals(mapLeft, mapDifferentSize));
	}

	@Test
	public void multilinePrint() {
		Assert.assertEquals(String.format("one%ntwo%n%nthree"),
				CollectionsUtils.multiline(Arrays.asList("one", "two", null, "three")));
	}

	@Test
	public void copyIfNotEmpty() {
		final List<Integer> list = Arrays.asList(1, 2, 3, 4);
		final ArrayList<Integer> list2 = CollectionsUtils.copyIfNotEmpty(list, ArrayList::new);
		Assert.assertArrayEquals(list.toArray(), list2.toArray());
	}
}


