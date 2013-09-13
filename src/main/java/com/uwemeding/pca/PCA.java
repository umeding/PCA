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
 * Principal component analysis core.
 * @author uwe
 */
public class PCA {

	// The incoming matrix
	private final Matrix m;
	// the principal components
	private final Matrix pc;
	// facpr
	private final Matrix facpr;
	// lambda
	private final Matrix lambda;

	public PCA(Matrix x) {

		// Weight and center the matrix
		this.m = x.wcenter();
		// compute the eigenvectors of y'*y using svd
		SVD svd = new SVD(this.m);

		// calculate the lambda
		this.lambda = calculateLambda(svd.getS());
		// get the principle factors
		this.facpr = svd.getV();

		// calculate the principle components
		this.pc = this.m.times(svd.getV());
	}

	private Matrix calculateLambda(Matrix s) {

		Matrix d = s.diag();
		double[][] D = d.getArray();

		int size = d.getNRows();
		for (int i = 0; i < size; i++) {
			D[i][0] = (D[i][0] * D[i][0]) / (size - 1);
		}

		return d;
	}

	public Matrix getPrincipalComponents() {
		return pc;
	}

	public Matrix getLambda() {
		return lambda;
	}

	public Matrix getPrinicipalFactors() {
		return facpr;
	}

}
