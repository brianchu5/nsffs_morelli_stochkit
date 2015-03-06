#!/bin/bash

#modelname = $1
#simtime = $2
#realizations = $3
#interval = $4
#outdir = $5
#outfile = $6
#A = $7
#AA = $8
#B = $9
#OAA = $10
#BB = $11
#OBB= $12
#O = $13
#R = $14

~/par_nonstationary2/StochKit2.0.11/ssa -m ${1} -t ${2} -r ${3} -i ${4} --keep-trajectories -f --no-stats --out-dir ~/data1/${5} --no-recompile

#./ssa -m ${1} -t ${2} -r ${3} -i ${4} --keep-trajectories -f --no-stats --out-dir ~/data1/${5} --no-recompile


mv ~/data1/${5}/trajectories/trajectory0.txt ${6}

#awk '{print $1,($2+($3*2)+($5*2)) - ($4+($6*2)+($7*2)),$9}' ${6} > lambda.txt

awk '{print $2}' ${6}> ${7}

awk '{print $3}' ${6}> ${8}

awk '{print $4}' ${6}> ${9}


awk '{print $5}' ${6}> ${10}

awk '{print $6}' ${6}> ${11}

awk '{print $7}' ${6}> ${12}


awk '{print $8}' ${6}> ${13}

awk '{print $9}' ${6}> ${14}







