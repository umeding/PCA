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

/**
 * Wraps PCA invocations
 * @author uwe
 */
public class PCAHandler {

	public PCAHandler() {
	}

	/**
	 * Run a principal component analysis from a simple time series vector. We 
	 * are converting the data into a Toeplitz style matrix before running the
	 * PCA.
	 */
	public PCA fromSimpleTimeSeries(double[] data) {
		Matrix m = new ToeplitzMatrix(data);
		PCA pca = new PCA(m);
		return pca;
	}
}
