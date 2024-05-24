package com.jiabin.neo4j.practice.service;

import com.jiabin.neo4j.practice.entity.Node;
import com.jiabin.neo4j.practice.entity.Relation;
import edu.stanford.nlp.trees.TreeGraphNode;

import java.util.List;

public interface NodeService {
    public Node save(Node node);
    public void bind(String name1, String name2, String relationName);
    public List<Relation> parseAndBind(String sentence);
}
