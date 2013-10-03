
%.data : %.gp ; gnuplot -e "load '$<'"

.PRECIOUS: *.gp
.PHONY: all clean

all:  \
compressor_per_day_kwh_cc.data \
compressor_per_day_kwh.data \
compressor_per_day_kwh_pc_1.data \
compressor_per_day_kwh_pc_2.data \
compressor_per_day_kwh_pc_3.data \
compressor_per_day_kwh_pc.data \
compressor_per_day_kwh_pc_sum.data \
compressor_per_day_kwh_lambda.data \
compressor_per_day_kwh_cumcon.data


clean:;	rm -f *.data
