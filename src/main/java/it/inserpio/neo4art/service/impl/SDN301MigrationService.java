/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.inserpio.neo4art.service.impl;

import it.inserpio.neo4art.exception.MigrationException;
import it.inserpio.neo4art.service.SDNMigrationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.gis.spatial.indexprovider.LayerNodeIndex;
import org.neo4j.gis.spatial.indexprovider.SpatialIndexProvider;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.helpers.collection.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.typerepresentation.LabelBasedNodeTypeRepresentationStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since Mar 19, 2014
 */
@Service
public class SDN301MigrationService implements SDNMigrationService
{
  public static final Logger logger = LoggerFactory.getLogger(SDN301MigrationService.class);
  
  public static final String CYPHER_ADD_SDN_LABEL_TO_NODE = "match (n:`%s`) set n:`" + LabelBasedNodeTypeRepresentationStrategy.LABELSTRATEGY_PREFIX + "%s`";

  @Autowired
  private Neo4jTemplate neo4jTemplate;

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public boolean migrate() throws MigrationException
  {
    Collection<String> labels = this.neo4jTemplate.getGraphDatabase().getAllLabelNames();
    
    for (String label : labels)
    {
      if (!label.equals(LabelBasedNodeTypeRepresentationStrategy.SDN_LABEL_STRATEGY) && !label.startsWith(LabelBasedNodeTypeRepresentationStrategy.LABELSTRATEGY_PREFIX))
      {
        String addSdnLabelToNodeStatement = String.format(CYPHER_ADD_SDN_LABEL_TO_NODE , label, label);
        
        logger.debug("Adding label `" + LabelBasedNodeTypeRepresentationStrategy.LABELSTRATEGY_PREFIX + label + "` for Spring Data compatibility to nodes with label `" + label + "`.");

        this.neo4jTemplate.query(addSdnLabelToNodeStatement, null);
      }
    }

    return true;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public boolean addMuseumsToSpatialIndex() throws MigrationException
  {
    GraphDatabaseService graphDatabaseService = this.neo4jTemplate.getGraphDatabaseService();
    
    IndexManager indexManager = graphDatabaseService.index();

    Map<String, String> config = Collections.unmodifiableMap(
        MapUtil.stringMap(SpatialIndexProvider.GEOMETRY_TYPE, LayerNodeIndex.POINT_GEOMETRY_TYPE,
                          IndexManager.PROVIDER, SpatialIndexProvider.SERVICE_NAME,
                          LayerNodeIndex.WKT_PROPERTY_KEY, "wkt") );
    
    Index<Node> index = indexManager.forNodes("museumLocation", config);
     
    Iterator<Node> museums = this.neo4jTemplate.query("MATCH (m:MUSEUM) RETURN m", null).to(Node.class).iterator();

    while (museums.hasNext())
    {
      Node museum = museums.next();
      
      if (museum.hasProperty("wkt"))
      {
        System.out.println("ADDING " + museum.getProperty("name") + " to museumLocation index...");
      
        index.add(museum, "dummy", "value");
      }
      else
      {
        System.out.println(museum.getProperty("name") + " NOT ADDED to museumLocation index...");
      }
    }
    
    return true;
  }
}
