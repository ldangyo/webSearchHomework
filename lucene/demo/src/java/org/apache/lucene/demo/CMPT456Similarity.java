package org.apache.lucene.demo;

import org.apache.lucene.search.similarities.ClassicSimilarity;

public final class CMPT456Similarity extends ClassicSimilarity {
    public CMPT456Similarity() {}

    /** Implemented as <code>sqrt(freq)</code>. */
    @Override
    public float tf(float freq) {
        return (float)Math.sqrt(1 + freq);
    }

    /** Implemented as <code>log((docCount+1)/(docFreq+1)) + 1</code>. */
    @Override
    public float idf(long docFreq, long docCount) {
        return (float)(Math.log((docCount+2)/(double)(docFreq+2)) + 1.0);
    }
}