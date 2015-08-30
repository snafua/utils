/**
 * Copyright 2014-2015 Emmanuel Keller / QWAZR
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qwazr.utils.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * Represents a row from a ResultSet. A convenient way to retrieve data from
 * ResultSet if you don't want to use POJO. *
 */
public class Row {

	private final Map<String, Integer> columnMap;

	private final Object[] columns;

	Row(Map<String, Integer> columnMap, int columnCount) {
		this.columnMap = columnMap;
		this.columns = new Object[columnCount];
	}

	Row(Map<String, Integer> columnMap, int columnCount, ResultSet rs) throws SQLException {
		this(columnMap, columnCount);
		for (Map.Entry<String, Integer> entry : columnMap.entrySet()) {
			int columnIndex = entry.getValue();
			Object object = rs.getObject(columnIndex + 1);
			columns[columnIndex] = object;
		}
	}

	/**
	 * @param columnNumber the number of the column
	 * @return the value for the give column
	 */
	final public Object get(int columnNumber) {
		Object col = columns[columnNumber];
		if (col == null)
			return null;
		return col;
	}

	/**
	 * @param label the label of the column
	 * @return the value for the given column label
	 */
	final public Object get(String label) {
		Integer colNumber = columnMap.get(label);
		if (colNumber == null)
			return null;
		if (colNumber > columns.length)
			return null;
		return columns[colNumber];
	}

	final public int size() {
		return columns == null ? 0 : columns.length;
	}

	final public Set<String> getColumns() {
		return columnMap.keySet();
	}
}
