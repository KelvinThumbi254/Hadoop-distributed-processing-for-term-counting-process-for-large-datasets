# Introduction
The application processes genomic-related text files using Hadoop to perform distributed 
computation. Specifically, the task is to count occurrences of specific terms, “GO:0030420” term 
within source data files. 
The application utilises Hadoop's distributed processing capabilities to scale the term-counting 
process for large datasets, which is essential in bioinformatics for analysing genome-wide data 
files. 7 files used were from different bacteria as the name of file suggests and were in .gaf 
format.  
1. Bacillus_amyloliquefaciens_FZB42-326423 
2. Bacillus_licheniformis_ATCC_14580-27901 
3. Bacillus_megaterium_DSM_319-592022 
4. Bacillus_subtilis_168-224308 
5. Escherichia_coli_K-12_ecocyc_83333 
6. Geobacillus_kaustophilus_HTA426-235909 
7. Geobacillus_thermodenitrificans_NG80_2-420246 
The output file has the count of the genome of GO:0030420, If a bacterium has more copies or 
number of the gene associated with GO:0030420; establishment of competence for 
transformation, it means that the bacterium has an increased ability to take up exogenous DNA 
from its environment and the results are as follows;
:~ $ bin/Hadoop fs -cat output/part-r-00000
Bacillus_amyloliquefaciens_FZB42-326423 8
Bacillus_licheniformis_ATCC_14580-27901 7 
Bacillus_megaterium_DSM_319-592022 8
Bacillus_subtilis_168-224308 56
Escherichia_coli_K-12_ecocyc_83333 2
Geobacillus_kaustophilus_HTA426-235909 9
Geobacillus_thermodenitrificans_NG80_2-420246 8
   
Bacteria Bacillus_subtilis_168-224308 has more GO:0030420 than any other Bacteria meaning it 
has an increased ability to take up exogenous DNA from its environment than any of the other 6 
bacteria. 
## Critically evaluate design aspects and findings related to your solution 
To assess the design aspects of using Hadoop as a big data solution, I will evaluate its strengths 
and weaknesses 
## Strengths: 
1. Scalability - Using Hadoop allows processing of large genomic datasets efficiently, which is 
critical in bioinformatics. 
2. Modularity - The system is modular, with clear separation between input files, processing 
logic in this case the Java program, and output. 
3. Flexibility - Can handle various bioinformatics datasets by simply changing the input files or 
modifying the term-counting logic. 
## Weaknesses: 
1. Setup Complexity - Setting up Hadoop, Java, and configuring environment variables can be 
challenging for users without prior experience. 
2. Limited Input Formats - Assumes input is in a simple text format; additional preprocessing 
might be required for more complex formats like FASTA, FASTQ, or GFF. 
3. Error Handling - No explicit mention of error handling in the Java program or the workflow 
for invalid files or failed jobs.
