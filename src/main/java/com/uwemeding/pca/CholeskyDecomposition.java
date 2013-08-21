package com.uwemeding.pca;

/**
 * Cholesky Decomposition.
 *
 * <p>For a symmetric, positive definite matrix A, the Cholesky decomposition
 * is an lower triangular matrix L so that A = L*L'.</p>
 *
 * <p>If the matrix is not symmetric or positive definite, the constructor
 * returns a partial decomposition and sets an internal flag that may
 * be queried by the isSPD() method.</p>
 *
 */
public class CholeskyDecomposition implements java.io.Serializable {

	// Array for internal storage of decomposition.
	private double[][] L;
	// Row and column dimension (square matrix).
	private int n;
	// Symmetric and positive definite flag.
	private boolean isspd;

	/**
	 * Cholesky algorithm for symmetric and positive definite matrix.
	 * @param  A   Square, symmetric matrix.
	 * @return     Structure to access L and isspd flag.
	 */
	public CholeskyDecomposition(Matrix Arg) {

		// Initialize.
		double[][] A = Arg.getArray();
		n = Arg.getRowDimension();
		L = new double[n][n];
		isspd = (Arg.getColumnDimension() == n);
		// Main loop.
		for (int j = 0; j < n; j++) {
			double[] Lrowj = L[j];
			double d = 0.0;
			for (int k = 0; k < j; k++) {
				double[] Lrowk = L[k];
				double s = 0.0;
				for (int i = 0; i < k; i++) {
					s += Lrowk[i] * Lrowj[i];
				}
				Lrowj[k] = s = (A[j][k] - s) / L[k][k];
				d = d + s * s;
				isspd = isspd & (A[k][j] == A[j][k]);
			}
			d = A[j][j] - d;
			isspd = isspd & (d > 0.0);
			L[j][j] = Math.sqrt(Math.max(d, 0.0));
			for (int k = j + 1; k < n; k++) {
				L[j][k] = 0.0;
			}
		}
	}

	/**
	 * Is the matrix symmetric and positive definite?
	 * @return     true if A is symmetric and positive definite.
	 */
	public boolean isSPD() {
		return isspd;
	}

	/**
	 * Return triangular factor.
	 * @return     L
	 */
	public Matrix getL() {
		return new Matrix(L, n, n);
	}

	/**
	 * Solve A*X = B
	 *
	 * @param  B   A Matrix with as many rows as A and any number of columns.
	 * @return     X so that L*L'*X = B
	 * @exception  MathException  Matrix row dimensions must agree.
	 * @exception  MathException  Matrix is not symmetric positive definite.
	 */
	public Matrix solve(Matrix B) {
		if (B.getRowDimension() != n) {
			throw new MathException("Matrix row dimensions must agree.");
		}
		if (!isspd) {
			throw new MathException("Matrix is not symmetric positive definite.");
		}

		// Copy right hand side.
		double[][] X = B.getArrayCopy();
		int nx = B.getColumnDimension();

		// Solve L*Y = B;
		for (int k = 0; k < n; k++) {
			for (int j = 0; j < nx; j++) {
				for (int i = 0; i < k; i++) {
					X[k][j] -= X[i][j] * L[k][i];
				}
				X[k][j] /= L[k][k];
			}
		}

		// Solve L'*X = Y;
		for (int k = n - 1; k >= 0; k--) {
			for (int j = 0; j < nx; j++) {
				for (int i = k + 1; i < n; i++) {
					X[k][j] -= X[i][j] * L[i][k];
				}
				X[k][j] /= L[k][k];
			}
		}

		return new Matrix(X, n, nx);
	}
}

