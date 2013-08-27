/*
 * Copyright (c) 2013 Meding Software Technik -- All Rights Reserved.
 */
package com.uwemeding.pca;

/**
 *
 * @author uwe
 */
public class Main {

	public static void main(String... av) throws Exception {

		double[] data = new double[]{1, 2, 3, 4, 5, 6}; //Data.ts_days;
		System.out.println("data len=" + data.length);
		Matrix m = new ToeplitzMatrix(data);

		PCA pca = new PCA(m);

		log("Principle Components", pca.getPrincipalComponents());
		log("lambda", pca.getLambda());
		log("facpr", pca.getPrinicipalFactors());
	}

	private static void log(String name, Matrix m) {
		System.out.println(name + ":");
		m.print(1, 4);
	}
}
