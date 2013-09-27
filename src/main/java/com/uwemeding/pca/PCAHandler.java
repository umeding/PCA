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
 *
 * @author uwe
 */
public class PCAHandler {

	public PCAHandler() {
	}

	/**
	 * Run a principal component analysis of a matrix.
	 *
	 * @param m the matrix
	 * @return the principle components
	 */
	public PCA fromMatrix(Matrix m) {
		return new PCA(m);
	}

	/**
	 * Run a principal component analysis from a simple time series vector. We
	 * are converting the data into a Toeplitz style matrix before running the
	 * PCA.
	 *
	 * @param data the time series vector
	 * @return the principle components
	 */
	public PCA fromSimpleTimeSeries(double[] data) {
		Matrix m = new ToeplitzMatrix(data);
		PCA pca = new PCA(m);
		return pca;
	}

	/**
	 * Calculate the correlations circle for two components. This is quick and
	 * dirty we are not doing any validity checks to make sure the PCA has
	 * completed successfully.
	 *
	 * @param pca the PCA
	 * @param compare the principal factor columns to compare
	 * @return the correlations circle
	 */
	public Matrix correlationCircle(PCA pca, int[] compare) {
		double[][] F = pca.getPrinicipalFactors().getArray();
		double[][] L = pca.getLambda().getArray();

		// calculate the correlation circle
		Matrix cc = new Matrix(F.length, compare.length);
		double[][] CC = cc.getArray();

		for (int n = 0; n < compare.length; n++) {
			int index = compare[n];
			double s = Math.sqrt(L[index][0]);
			for (int m = 0; m < F.length; m++) {
				double f = F[m][index];

				CC[m][n] = s * f;
			}
		}
		return cc;
	}

	/**
	 * Calculate the correlations circle for the two largest eigenvalues.
	 *
	 * @param pca the pca
	 * @return the correlations circle
	 */
	public Matrix correlationCircle(PCA pca) {
		return correlationCircle(pca, new int[]{0, 1});
	}

	/**
	 * Normalize the eigenvalues so we can create a scree plot.
	 *
	 * @param pca the pca
	 * @return the normalized eigenvalues;
	 */
	public Matrix normalizeLambda(PCA pca) {

		double[][] L = pca.getLambda().getArrayCopy();
		Matrix nl = new Matrix(L);
		double sum = 0;
		for (int n = 0; n < L.length; n++) {
			sum += L[n][0];
		}
		for (int n = 0; n < L.length; n++) {
			L[n][0] = L[n][0] / sum;
		}
		return nl;
	}

	/**
	 * Calculate the cumulative contribution of the eigenvectors
	 *
	 * @param pca is the pca
	 * @return the cumulative contributions of the eigenvectors
	 */
	public Matrix cumulativeContribution(PCA pca) {
		Matrix nl = normalizeLambda(pca);
		double[][] CC = nl.getArrayCopy();
		Matrix cc = new Matrix(CC);
		double cum = 0;
		for (int n = 0; n < CC.length; n++) {
			cum = CC[n][0] = CC[n][0] + cum;
		}
		return cc;
	}
}
