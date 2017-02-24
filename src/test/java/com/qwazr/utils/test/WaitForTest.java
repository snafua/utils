/**
 * Copyright 2015-2016 Emmanuel Keller / QWAZR
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

import com.qwazr.utils.FunctionUtils;
import com.qwazr.utils.WaitFor;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitForTest {

	static class Counter implements FunctionUtils.CallableEx<Boolean, InterruptedException> {

		final AtomicInteger count = new AtomicInteger();
		final boolean returnedValue;
		final long startTime = System.currentTimeMillis();

		Counter(boolean returnedValue) {
			this.returnedValue = returnedValue;
		}

		@Override
		public Boolean call() throws InterruptedException {
			count.incrementAndGet();
			return returnedValue;
		}
	}

	private void checkWait(WaitFor.Builder builder) throws InterruptedException {
		final Counter counter = new Counter(true);
		builder.wait(counter);
		Assert.assertEquals(1, counter.count.get());
	}

	@Test
	public void testWaitFullParams() throws InterruptedException {
		checkWait(WaitFor.of().timeOut(TimeUnit.SECONDS, 10).pauseTime(TimeUnit.MILLISECONDS, 200));
	}

	@Test
	public void testWaitDefaultParams() throws InterruptedException {
		checkWait(WaitFor.of());
	}

	private void checkTimeOut(WaitFor.Builder builder, long msWait) {
		final Counter counter = new Counter(false);
		try {
			builder.wait(counter);
			Assert.fail("The InterruptedException has not be thrown");
		} catch (InterruptedException e) {
			Assert.assertTrue(counter.startTime + msWait <= System.currentTimeMillis());
			Assert.assertTrue(counter.count.get() > 1);
		}
	}

	@Test
	public void testTimeOutFullParams() {
		checkTimeOut(WaitFor.of().timeOut(TimeUnit.SECONDS, 2).pauseTime(TimeUnit.MILLISECONDS, 200), 2000);
	}

	@Test
	public void testTimeOutHalfParams() {
		checkTimeOut(WaitFor.of(), 1000);
	}
}
