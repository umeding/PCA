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
 * Toeplitz matrix
 *
 * @author uwe
 */
public class ToeplitzMatrix extends Matrix {

	/**
	 * Toeplitz matrix styles
	 */
	public static enum Type {

		Triangular, Symmetrical, Circulant
	};

	/**
	 * Create a symmetrical Toeplitz-style matrix from a vector.
	 *
	 * @param v
	 */
	public ToeplitzMatrix(double[] v) {
		this(v, Type.Symmetrical);
	}

	/**
	 * Create a Toeplitz matrix from a vector.
	 *
	 * @param v the vector
	 * @param type the matrix style
	 */
	public ToeplitzMatrix(double[] v, Type type) {
		super(v.length, v.length);
		int n = v.length;
		double[][] arr = getArray();

		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j <= i; j++) {
				int index = i - j;
				arr[i][j] = v[i - j];
				switch (type) {
					default:
					case Triangular:
						// do nothing
						break;
					case Symmetrical:
						arr[j][i] = v[i - j];
						break;
					case Circulant:
						if (j != i) {
							arr[j][i] = v[n - index];
						}
						break;
				}
			}
		}
	}
}
