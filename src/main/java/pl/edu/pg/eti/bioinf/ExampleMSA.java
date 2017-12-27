package pl.edu.pg.eti.bioinf;

import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.core.alignment.SimpleAlignedSequence;
import org.biojava.nbio.core.alignment.template.AlignedSequence;
import org.biojava.nbio.core.alignment.template.MutableProfile;
import org.biojava.nbio.core.alignment.template.MutableProfilePair;
import org.biojava.nbio.core.alignment.template.Profile;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
import org.biojava.nbio.core.sequence.template.Sequence;
import org.biojava.nbio.core.util.ConcurrencyTools;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExampleMSA {
    public static void main(String[] args) {
        String[] ids = new String[] {"Q21691", "A8WS47", "O48771"};
        try {
            multipleSequenceAlignment(ids);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void multipleSequenceAlignment(String[] ids) throws Exception {
        List<ProteinSequence> lst = new ArrayList<ProteinSequence>();
        for (String id : ids) {
            lst.add(getSequenceForId(id));
        }

        // <--------- additional settings

        // also GLOBAL, GLOBAL_SIMILARITIES, KMERS, LOCAL, LOCAL_IDENTITIES, LOCAL_SIMILARITIES, WU_MANBER
        Alignments.PairwiseSequenceScorerType psst = Alignments.PairwiseSequenceScorerType.GLOBAL_IDENTITIES;

        GapPenalty gapPenalty = new SimpleGapPenalty(1 , 3);
        // System.out.println(gapPenalty.getType());

        // also GLOBAL_CONSENSUS, GLOBAL_LINEAR_SPACE, LOCAL, LOCAL_CONSENSUS, LOCAL_LINEAR_SPACE
        Alignments.ProfileProfileAlignerType ppat = Alignments.ProfileProfileAlignerType.GLOBAL;

        // ----------------->

        Profile<ProteinSequence, AminoAcidCompound> profile = Alignments.getMultipleSequenceAlignment(lst, psst, gapPenalty, ppat);
       // MutableProfile<ProteinSequence, AminoAcidCompound> mProfile = (MutableProfilePair<ProteinSequence, AminoAcidCompound>)Alignments.getMultipleSequenceAlignment(lst, psst, gapPenalty, ppat);
        int idx = 2;
        List<AlignedSequence<ProteinSequence, AminoAcidCompound>> sequences = profile.getAlignedSequences();
        System.out.println(sequences.get(idx)+" length: "+sequences.size());
        List<AlignedSequence<ProteinSequence, AminoAcidCompound>> sequencesCp = new ArrayList<AlignedSequence<ProteinSequence, AminoAcidCompound>>();
        for (AlignedSequence<ProteinSequence, AminoAcidCompound> seq : sequences) {
            sequencesCp.add(seq);
        }
        AlignedSequence<ProteinSequence, AminoAcidCompound> newSeq, prev;
        List<AlignedSequence.Step> steps;
        prev = sequencesCp.get(idx);
        steps = new ArrayList<AlignedSequence.Step>(Collections.nCopies(prev.getLength(), AlignedSequence.Step.COMPOUND));
        steps.add(5, AlignedSequence.Step.GAP);
        newSeq = new SimpleAlignedSequence<ProteinSequence, AminoAcidCompound>(prev, steps);
        sequencesCp.set(idx, newSeq);
        System.out.println(sequencesCp.get(idx)+" length: "+sequencesCp.size());

        // System.out.printf("Clustalw:%n%s%n", profile);
        ConcurrencyTools.shutdown();
    }

    private static ProteinSequence getSequenceForId(String uniProtId) throws Exception {
        URL uniprotFasta = new URL(String.format("http://www.uniprot.org/uniprot/%s.fasta", uniProtId));
        ProteinSequence seq = FastaReaderHelper.readFastaProteinSequence(uniprotFasta.openStream()).get(uniProtId);
        // System.out.printf("id : %s %s%n%s%n", uniProtId, seq, seq.getOriginalHeader());
        return seq;
    }
}
