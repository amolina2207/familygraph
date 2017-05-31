package com.neoworks.interviewtests.graph.models;

/**
 * Created by amolina on 6/05/17.
 */

// This interface is necessary for all the beans to be mapped from a csv file

public interface Parseable{
    Parseable parse(String[] aElements);
}
