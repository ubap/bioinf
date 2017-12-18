//package pl.edu.pg.eti.bioinf;
//
//import org.biojava.nbio.alignment.NeedlemanWunsch;
//import org.biojava.nbio.alignment.SimpleGapPenalty;
//import org.biojava.nbio.alignment.template.GapPenalty;
//import org.biojava.nbio.core.alignment.matrices.SimpleSubstitutionMatrix;
//import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
//import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
//import org.biojava.nbio.core.sequence.DNASequence;
//import org.biojava.nbio.core.sequence.MultipleSequenceAlignment;
//import org.biojava.nbio.core.sequence.ProteinSequence;
//import org.biojava.nbio.core.sequence.compound.DNACompoundSet;
//
//public class Starter {
//
//    public static void main(String[] args) throws CompoundNotFoundException {
//        ProteinSequence proteinSequence = new ProteinSequence("ARNDCEQGHILKMFPSTWYVBZJX");
//        DNASequence dnaSequence = new DNASequence("ATCG");
//
//        MultipleSequenceAlignment multipleSequenceAlignment = new MultipleSequenceAlignment();
//        multipleSequenceAlignment.addAlignedSequence(new ProteinSequence("ABCA"));
//        multipleSequenceAlignment.addAlignedSequence(new ProteinSequence("ABAB"));
//        multipleSequenceAlignment.addAlignedSequence(new ProteinSequence("CBBC"));
//
//        GapPenalty gapPenalty = new SimpleGapPenalty(1 , 2);
//        SubstitutionMatrix substitutionMatrix = new SimpleSubstitutionMatrix(new DNACompoundSet(),
//                (short) 1, (short) 2);
//
//        NeedlemanWunsch needlemanWunsch = new NeedlemanWunsch();
//        needlemanWunsch.setQuery(new ProteinSequence("ABCA"));
//        needlemanWunsch.setTarget(new ProteinSequence("CBBC"));
//        needlemanWunsch.setGapPenalty(gapPenalty);
//        needlemanWunsch.setSubstitutionMatrix(substitutionMatrix);
//
//
//        System.out.println(multipleSequenceAlignment.toString());
//    }
//
//}
