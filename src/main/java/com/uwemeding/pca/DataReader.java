/*
 * Copyright (c) 2013 Uwe B. Meding <uwe@uwemeding.com>
 *
 * This file is part of UM/PCA
 * This PCA software is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Final Term is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UM/PCA.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.uwemeding.pca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple vector data reader
 *
 * @author uwe
 */
public class DataReader extends BufferedReader {

	public DataReader(Reader in, int sz) {
		super(in, sz);
	}

	public DataReader(Reader in) {
		super(in);
	}

	/**
	 * Get the (vector) data contained in the file. The data is stored one value
	 * per line. Empty lines are ignored.
	 *
	 * @return the data
	 */
	public double[] getData() throws IOException {
		List<Double> dataList = new ArrayList<>();
		String line;
		while ((line = readLine()) != null) {
			line = line.trim();
			if (line.isEmpty()) {
				continue;
			}
			dataList.add(Double.valueOf(line));
		}

		double[] vector = new double[dataList.size()];
		int i = 0;
		for (Double d : dataList) {
			vector[i++] = d;
		}
		return vector;
	}
}
