package com.neoworks.interviewtests.graph.utils;

import com.neoworks.interviewtests.graph.models.Parseable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolina on 5/05/17.
 */
public class FileUtils<T extends Parseable> {

    private static final String DEFAULT_SEPARATOR = ",";

    public List<Parseable> loadFile(String aFilename, String aSeparator, T aModeler) {
        List<Parseable> tmpResult = new ArrayList<Parseable>();
        if(aSeparator==null || aSeparator.equals("")) aSeparator = DEFAULT_SEPARATOR;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FileUtils.class.getClassLoader().getResource(aFilename).getFile()));
            String line;
            while ((line = br.readLine()) != null) {
                if(line !=null && line.length()>0){
                    tmpResult.add(aModeler.parse(line.split(aSeparator)));
                }
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(br != null){
                    br.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return tmpResult;
    }
}