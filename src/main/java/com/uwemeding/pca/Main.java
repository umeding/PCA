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

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Invoke the PCA from the command line
 *
 * @author uwe
 */
public class Main {

	/**
	 * Run a PCA on vector data
	 *
	 * @param av are file references containing vector data
	 * @throws Exception
	 */
	public static void main(String... av) throws Exception {

		if (av.length == 0) {
			throw new IllegalArgumentException("Usage: pca FILES...");
		}

		for (String filename : av) {
			try (DataReader dr = new DataReader(new FileReader(filename + ".data"))) {
				double[] data = dr.getData();
				System.out.println(filename + ": vector length = " + data.length);

				PCAHandler handler = new PCAHandler();
				PCA pca = handler.fromSimpleTimeSeries(data);

				log(filename + "_pcomps.data", filename + ": principle components", pca.getPrincipalComponents());
				log(filename + "_lambda.data", filename + ": lambda", pca.getLambda());
				log(filename + "_pfacs.data", filename + ": principle factors", pca.getPrinicipalFactors());

				Matrix cc = handler.correlationCircle(pca);
				log(filename + "_cc.data", filename + ": correlation circle", cc);

				Matrix cumcon = handler.cumulativeContribution(pca);
				log(filename + "_cumcon.data", filename + ": cumulative contributions", cumcon);
			}
		}
	}

	private static void log(String filename, String tag, Matrix m) throws IOException {
		try (PrintWriter fp = new PrintWriter(new FileWriter(filename))) {
			System.out.println(tag + ":");
			MatrixHelper.print(m, fp, 1, 4);
		}
	}
}
