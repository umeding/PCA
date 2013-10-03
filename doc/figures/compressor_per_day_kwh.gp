set table './compressor_per_day_kwh.data'

set xrange [0:170]
set yrange [40:100]
plot "../../test/compressor_per_day_kwh.data" u 1 w l
