# Simple tex makefile

MAIN = pca

$(MAIN).dvi: 
	pdflatex -shell-escape -interaction=nonstopmode -halt-on-error -output-format=pdf $(MAIN)
	pdflatex -shell-escape -interaction=nonstopmode -halt-on-error -output-format=pdf $(MAIN)

.PHONY:	clean pdf

clean:;	rubber --clean $(MAIN)

veryclean:;	
		rm -rf $(MAIN) $(MAIN).pdf *~ *backup *bak
		
