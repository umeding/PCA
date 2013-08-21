package com.uwemeding.pca;

/**
 *
 * @author uwe
 */
public class ToeplitzMatrix extends Matrix {

	public static enum Type {

		Triangular, Symmetrical, Circulant
	};

	public ToeplitzMatrix(double[] v, Type type) {
		super(v.length, v.length);
		int n = v.length;
		double[][] arr = getArray();
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				arr[i][j] = Math.random();
//			}
//		}

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
