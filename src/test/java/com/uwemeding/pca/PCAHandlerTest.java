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

import junit.framework.TestCase;

/**
 * Test the PCA handler with a simple test
 *
 * @author uwe
 */
public class PCAHandlerTest extends TestCase {

	public PCAHandlerTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	// expected results
	int SIZE = 6;
	double[] data = new double[]{1, 2, 3, 4, 5, 6};
	double[][] pcomps = new double[][]{
		{-2.4530, -1.4869, -0.2693, -0.1327, -0.1141, -0.0000},
		{-1.9344, 0.1112, 0.1731, 0.3003, 0.2126, 0.0000},
		{-0.7959, 1.3757, 0.4091, -0.1677, -0.1651, -0.0000},
		{0.7959, 1.3757, -0.4091, -0.1677, 0.1651, -0.0000},
		{1.9344, 0.1112, -0.1731, 0.3003, -0.2126, 0.0000},
		{2.4530, -1.4869, 0.2693, -0.1327, 0.1141, 0.0000}
	};
	double[] plambda = new double[]{4.1572, 1.6463, 0.1080, 0.0544, 0.0342, 0.0000};
	double[][] pfacs = {
		{0.4851, -0.0000, 0.4138, 0.0000, 0.3056, 0.7071},
		{0.4562, -0.2454, -0.1519, -0.6631, -0.5185, -0.0000},
		{0.2378, -0.6631, -0.5529, 0.2454, 0.3712, -0.0000},
		{-0.2378, -0.6631, 0.5529, 0.2454, -0.3712, 0.0000},
		{-0.4562, -0.2454, 0.1519, -0.6631, 0.5185, -0.0000},
		{-0.4851, -0.0000, -0.4138, -0.0000, -0.3056, 0.7071}
	};

	/**
	 * Test of fromSimpleTimeSeries method, of class PCAHandler.
	 */
	public void testFromSimpleTimeSeries() {
		PCAHandler instance = new PCAHandler();
		PCA result = instance.fromSimpleTimeSeries(data);

		// compare the principal components
		double[][] res_pcomp = result.getPrincipalComponents().getArray();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				assertEquals(pcomps[i][j], res_pcomp[i][j], 0.001);
			}
		}

		// compare the lambdas
		double[] res_plambda = result.getLambda().transpose().getArray()[0];
		for (int i = 0; i < SIZE; i++) {
			assertEquals(plambda[i], res_plambda[i], 0.001);
		}

		// compare the principle factors
		double[][] res_pfacs = result.getPrinicipalFactors().getArray();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				assertEquals(pfacs[i][j], res_pfacs[i][j], 0.001);
			}
		}
	}
}
