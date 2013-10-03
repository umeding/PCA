set xrange [0:1500]
set table './compressor_per_day_kwh_pc_1.data'
plot "../../test/compressor_per_day_kwh_pcomps.data" u 1 

set table './compressor_per_day_kwh_pc_2.data'
plot "../../test/compressor_per_day_kwh_pcomps.data" u 2 

set table './compressor_per_day_kwh_pc_3.data'
plot "../../test/compressor_per_day_kwh_pcomps.data" u 3

set table './compressor_per_day_kwh_pc_4.data'
plot "../../test/compressor_per_day_kwh_pcomps.data" u 4

set table './compressor_per_day_kwh_pc_sum.data'
plot "../../test/compressor_per_day_kwh_pcomps.data" u ($1+$2+$3+$4) 
