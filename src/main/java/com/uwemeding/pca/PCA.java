package com.uwemeding.pca;

/**
 *
 * @author uwe
 */
public class PCA {

	private double calcMean(double[] v) {
		double sum = 0;
		for (double d : v) {
			sum += d;
		}
		return sum / v.length;
	}

	private double[] centerVector(double[] data, double center) {
		double[] result = new double[data.length];
		for (int i = 0; i < data.length; i++) {
			result[i] = data[i] - center;
		}
		return result;
	}

	public void execute() {

		double[] data = Data.small_vector;
		System.out.println("data len=" + data.length);

		double mean = calcMean(data);
		System.out.println("mean: " + mean);

		double[] v = centerVector(data, mean);


		Matrix m = new ToeplitzMatrix(data, ToeplitzMatrix.Type.Symmetrical);
		
//		m = m.transpose();
		log("Toeplitz matrix", m);
//		Matrix m = new TrajectoryMatrix(data, 4);
//		log("Trajectory matrix", m);

		m = m.center();
		log("Centered", m);
		
		SingularValueDecomposition svd = new SingularValueDecomposition(m);
	log("SVD S", svd.getS());	
	log("SVD U", svd.getU());	
	log("SVD V", svd.getV());	


		Matrix cov = KL.covariance(m.transpose());
		log("Covariance", cov);

		EigenvalueDecomposition eig = cov.eig();
		Matrix eigenValues = eig.getD();
		log("Eigen values", eigenValues);

		Matrix eigenVectors = eig.getV();
		log("Eigen vectors", eigenVectors);

//		dumpContributingFactors(m, eigenVectors, 3);

		// extract the principal component
		Matrix pc = eigenVectors.transpose().times(m);
		log("Principal component", pc);
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
		System.out.println(name + ":");
		m.print(1, 2);
	}

	public static void main(String[] av) throws Exception {
		new PCA().execute();
	}
}
