package com.uwemeding.pca;

/**
 * Math utility function.
 * 
 * @author uwe
 */
public class KL {

	private static final boolean KL_DEBUG = true;

	/**
	 * sqrt(a^2 + b^2) without under/overflow.
	 * @param a a
	 * @param b b
	 * @return hypotenuse
	 */
	public static double hypot(double a, double b) {
		double r;
		if (Math.abs(a) > Math.abs(b)) {
			r = b / a;
			r = Math.abs(a) * Math.sqrt(1 + r * r);
		} else if (b != 0) {
			r = a / b;
			r = Math.abs(b) * Math.sqrt(1 + r * r);
		} else {
			r = 0.0;
		}
		return r;
	}

	/**
	 * Calculate the mean values in a matrix
	 * @param x is the incoming matrix
	 * @return a matrix containing the means
	 */
	public static Matrix calculateMean(Matrix x) {
		int n = x.getColumnDimension();
		int m = x.getRowDimension();
		double[][] meanD = new double[m][1];
		Matrix mean = new Matrix(meanD);
		for (int i = 0; i < m; i++) {
			double avg = 0.0;
			for (int j = 0; j < n; j++) {
				avg += x.get(i, j);
			}
			mean.set(i, 0, avg / n);
		}
		return (mean);
	}
	
	public static Matrix covariance(Matrix x) {
		int nrows = x.getRowDimension();
		int ncols = x.getColumnDimension();
		double[][] xm = x.getArray();

		Matrix covm = new Matrix(nrows, ncols, 0);
		double[][] cm = covm.getArray();

		double factor = 1./(double)(ncols - 1);
		for(int i = 0; i < nrows; i++) {
			for(int j = 0; j < ncols; j++) {
				double cov = 0.0;
				for(int p = 0; p < ncols; p++) {
					cov += xm[p][i]*xm[p][j];
				}
				cm[i][j] = factor * cov;
			}
		}
		return(covm);
	}

	/**
	 * Perform the K-L transform and return the
	 * principal components
	 * @param x is the source matrix
	 * @return the princpal components
	 */
	public static Matrix klTransform(Matrix x) {

		int ncols = x.getColumnDimension();

		// find means of each row
		Matrix mean = KL.calculateMean(x);
		if (KL_DEBUG) {
			log("Mean", mean);
		}

		Matrix ones = new Matrix(1, ncols, 1);

		// center the data
		Matrix xm = x.minus(mean.times(ones));
		if (KL_DEBUG) {
			log("Centered", xm);
		}

		// find the covariance matrix of x
		Matrix cov = covariance(xm);
		if(KL_DEBUG)
			log("my cov", cov);

		/*
		cov = xm.times(xm.transpose());
		if (KL_DEBUG) {
			log("Covariance", cov);
		}
		 */

		// find the eigen vectors
		EigenvalueDecomposition eig = cov.eig();
		Matrix eigenValues = eig.getD();
		if (KL_DEBUG) {
			log("Eigen values", eigenValues);
		}

		Matrix eigenVectors = eig.getV();
		if (KL_DEBUG) {
			log("Eigen vectors", eigenVectors);
		}

		dumpContributingFactors(x, eigenVectors, 3);

		// extract the principal component
		Matrix pc = eigenVectors.transpose().times(xm);
		if (KL_DEBUG) {
			log("Principal component", pc);
		}

		return (pc);

	}

	private static void dumpContributingFactors(Matrix org, Matrix matrix, int nfactors) {
		System.out.println("Contributing factors");
		double[][] m = matrix.getArray();
		double[][] o = org.getArray();
		int ncols = matrix.getColumnDimension();
		for(int i=0;i<matrix.getRowDimension();i++) {
			System.out.print(i+" "+o[i][0]+" ");
			for(int j = 0; j < nfactors; j++) {
			    System.out.print(m[i][ncols-1-j]+" ");
			}
			System.out.println();
		}
	}

	// Log some stuff
	private static void log(String name, Matrix m) {
		System.out.println(name+":");
		m.print(m.getRowDimension(), 2);
	}
}
