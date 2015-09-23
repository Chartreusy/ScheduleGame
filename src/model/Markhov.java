package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
* Created by Chartreusy on 27/07/15.
*/

public class Markhov{
    Map<String, Node> graph; // full graph
    public Markhov(String path) throws Exception{
        graph = new HashMap<String, Node>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String trainer = "";

        System.out.println("Loading Input");
        for(String s = br.readLine(); s != null; s = br.readLine()){
            fillGraph(s, 1);

        }
    }

    // go by words instead of length
    public void fillGraphWord(String s){
        //s.substring(0, s.substring(0, s.indexOf(" ", 0);

        int space = s.indexOf(" ", 0);
        String next = s.substring(0, space);
        graph.put(next, new Node(next));
        Node cur;
        int prev = 0;
        int here = space;
        while(space != -1){
            space = s.indexOf(" ", space+1);

            next = s.substring(here, space);
            if(!graph.containsKey(next)){
                cur = new Node(next);
                graph.put(next, cur);
            } else{
                cur = graph.get(next);
            }
            Node par = graph.get(s.substring(prev, here));
            prev = here;
            here = space;
            par.add(cur);
        }
    }
    public void fillGraph(String s, int nodeSize){
        Node cur;

        graph.put(s.substring(0, nodeSize), new Node(s.substring(0,nodeSize)));

        for(int i = nodeSize; i< s.length()-nodeSize; i += nodeSize){
            String next = s.substring(i, i+nodeSize);

            if(!graph.containsKey(next)){
                cur = new Node(next);
                graph.put(next, cur);
            } else{
                cur = graph.get(next);
            }
            Node prev = graph.get(s.substring(i-nodeSize, i));
            prev.add(cur);
            System.out.print(((float)i/(float)s.length())*100+"%  \r");
        }
    }
    public Node getRandom(int seed){
        // get a random key
        String[] letters = new String[graph.keySet().size()];
        graph.keySet().toArray(letters);
        return graph.get(letters[seed%letters.length]);
    }
    public Node genNext(Node parent){
        int num = (int)(parent.divisor*Math.random());
        int ind = 0;
        for(int i = 0; i< parent.followers.size(); i++){
            num -= parent.incidence.get(i);
            if(num < 0){
                return parent.followers.get(i);
            }
        }
        return null;
    }
    public String generate(int length){
        System.out.println(length);
        Node n = getRandom((int)(100*Math.random()));
        String s = n.value;
        for(int i = 0; i< length; i++){
            n = genNext(n);
            s = s + n.value;
        }
        return s;
    }

    @Override
    public String toString(){ //prints the markhov graph
        String s = "";
        for(String key : graph.keySet()){
            s = s + graph.get(key) + "\n";
        }
        s = s + "\n";
        return s;
    }
    private static class Node {
        Map<String, Integer> nameMap;
        List<Node> followers;
        List<Integer> incidence;
        int divisor;
        String value;
        Node(String value){
            this.value = value;
            nameMap = new HashMap<String, Integer>();
            followers = new ArrayList<Node>();
            incidence = new ArrayList<Integer>();
            divisor = 0;
        }

        public Node add(Node n){
            divisor++;
            Integer index = nameMap.get(n.value);
            if(index == null){
                index = followers.size();
                nameMap.put(n.value, index); //append to list
                followers.add(n);
                incidence.add(1);
            } else{
                incidence.set(index, incidence.get(index)+1); //increment incidences
            }
            return followers.get(index);
        }
        @Override
        public String toString(){
            String s = "";
            s = value + " : [ ";
            for(int i = 0; i< followers.size(); i++){
                s = s + followers.get(i).value + "("+ incidence.get(i) +"/"+ divisor+") , ";
            }
            s = s + " ]";
            return s;
        }
    }
}



