set table './compressor_per_day_kwh_cumcon.data'

set xrange [0:170]
set yrange [0:1]
plot "../../test/compressor_per_day_kwh_cumcon.data" u 1 w l
