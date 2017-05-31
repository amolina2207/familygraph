package com.neoworks.interviewtests.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.neoworks.interviewtests.graph.models.Person;
import com.neoworks.interviewtests.graph.models.Relationships;
import com.neoworks.interviewtests.graph.utils.FileUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

/**
 * Created by amolina on 7/05/17.
 */
public class FamilyTest {

    private FileUtils fu;
    private String aFilenamePeople = "people.csv";
    private String aFilenameRels = "relationships.csv";
    List<Person> people;
    List<Relationships> rels;

    public void loadFiles(){

        // in order to parse every bean accordingly with its fields
        Person person = new Person();
        Relationships relation = new Relationships();

        fu = new FileUtils<Person>();
        // files to list of strings in this case
        people = fu.<Person>loadFile(aFilenamePeople, "", person);

        fu = new FileUtils<Relationships>();
        rels = fu.loadFile(aFilenameRels, "", relation);

        // we order both collection in order to look for elements easier
        Collections.sort(people);
        Collections.sort(rels);

    }

    @Test
    public void validateFiles(){

        loadFiles();

        // test of size (white lines does not add)
        assertThat(people, hasSize(12));

        assertThat(rels, hasSize(16));

    }

    @Test
    public void validateElementsLoaded(){

        loadFiles();

        assertEquals(countTimesWordIsRelatedTo(getEmailByName("bob")), 4);

        assertEquals(countTimesWordIsRelatedTo(getEmailByName("jenny")), 3);

        assertEquals(countTimesWordIsRelatedTo(getEmailByName("nigel")), 2);

        assertEquals(countTimesWordIsRelatedTo(getEmailByName("alan")), 0);

    }

    @Test
    public void calculateSizeOfFamily(){

        loadFiles();

        MutableGraph<String> graph = GraphBuilder.directed().build();

        // First of all nodes of people in the file
        for(Person person : people){
            graph.addNode(person.getEmail());
        }

        // Second we have to create relations between them using their email
        for(Relationships relation : rels){
            // we only add family to the graph
            if(relation.getType().equals("FAMILY")) {
                graph.putEdge(relation.getEmailA(), relation.getEmailB());
            }
        }

        Set<String> familyOfBop = new HashSet<String>();

        familyOfBop = getAdjacents(familyOfBop, graph, "bob@bob.com");

        Set<String> familyOfJenny = new HashSet<String>();

        familyOfJenny = getAdjacents(familyOfJenny, graph, "jenny@toys.com");


        assertThat(familyOfBop, hasSize(4));

        assertThat(familyOfJenny, hasSize(4));

    }
    // The purpose of this method is to find all the adjacent nodes until the end of the familiar tree
    // using a recursive call
    // checking if they are already on the result list or not
    // and including himself on the list

    public Set<String> getAdjacents(Set<String> listOfAdjs, Graph graph, String node){
        listOfAdjs.add(node);
        Set<String> result = graph.adjacentNodes(node);
        for(String adjs : result){
            if(!listOfAdjs.contains(adjs)) {
                listOfAdjs.addAll(getAdjacents(listOfAdjs,graph, adjs));
            }
        }
        listOfAdjs.addAll(result);
        return listOfAdjs;
    }

    public String getEmailByName(String aName){
        String tmpResult = "";
        for(Person person : people){
            if(person.getName().equalsIgnoreCase(aName)){
                tmpResult = person.getEmail();
                break;
            }
        }
        return tmpResult;
    }

    public int countTimesWordIsRelatedTo(String aName){
        int tmpResult = 0;
        for(Relationships relation : rels)
            if(relation.getEmailA().equals(aName) || relation.getEmailB().equals(aName))
                tmpResult++;
        return tmpResult;
    }



}


