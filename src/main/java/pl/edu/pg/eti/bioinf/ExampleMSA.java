package pl.edu.pg.eti.bioinf;

import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.core.alignment.template.Profile;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
import org.biojava.nbio.core.util.ConcurrencyTools;

import java.net.URL;
import java.util.ArrayList;
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
        System.out.println(gapPenalty.getType());

        // also GLOBAL_CONSENSUS, GLOBAL_LINEAR_SPACE, LOCAL, LOCAL_CONSENSUS, LOCAL_LINEAR_SPACE
        Alignments.ProfileProfileAlignerType ppat = Alignments.ProfileProfileAlignerType.GLOBAL;

        // ----------------->

        Profile<ProteinSequence, AminoAcidCompound> profile = Alignments.getMultipleSequenceAlignment(lst, psst, gapPenalty, ppat);
        System.out.printf("Clustalw:%n%s%n", profile);
        ConcurrencyTools.shutdown();
    }

    private static ProteinSequence getSequenceForId(String uniProtId) throws Exception {
        URL uniprotFasta = new URL(String.format("http://www.uniprot.org/uniprot/%s.fasta", uniProtId));
        ProteinSequence seq = FastaReaderHelper.readFastaProteinSequence(uniprotFasta.openStream()).get(uniProtId);
        System.out.printf("id : %s %s%n%s%n", uniProtId, seq, seq.getOriginalHeader());
        return seq;
    }
}
