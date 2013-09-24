Principle Component Analysis (PCA)
==================================

Principle Component Analysis is a way of identifying patterns in data,
and expressing the data in such a way as to highlight their
similarities and differences.  Since patterns in data can be hard to
find in data of high dimension, where the luxury of graphical
representation is not available, PCA is a powerful tool for analyzing
data.

The other main advantage of PCA is that once you have found these
patterns in the data, and you compress the data, ie. by reducing the
number of dimensions, without much loss of information. This technique
used a lot in image compression.

* PCA completely decorrelates the original signal. Formally
  speaking, the transform coefficients are statistically independent
  for a Gaussian signal
* PCA optimizes the repacking of the signal energy, such
  that most of the signal energy is contained in the fewest
  transforms coefficients.
* It minimizes the total entropy of the signal
* For any amount of compression the mean square errir in the
  reconstruction is minimized.

About this code
---------------

The PCA is generic, and will run on a variety of matrices. In my
particular case, I was analyzing time series of power consumption for
various pieces of large equipment (compressors, large fans, etc)
