set table './compressor_per_day_kwh_lambda.data'

set xrange [0:170]
set yrange [0:25]
plot "../../test/compressor_per_day_kwh_lambda.data" u 1 w l
