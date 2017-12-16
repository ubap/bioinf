package pl.edu.pg.eti.bioinf;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;

public class Starter {

    public static void main(String[] args) throws CompoundNotFoundException {
        ProteinSequence proteinSequence = new ProteinSequence("ARNDCEQGHILKMFPSTWYVBZJX");
        DNASequence dnaSequence = new DNASequence("ATCG");

        System.out.println("hello worlds");
    }
}
