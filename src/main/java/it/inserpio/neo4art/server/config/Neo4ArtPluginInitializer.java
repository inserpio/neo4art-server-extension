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

package it.inserpio.neo4art.server.config;

import it.inserpio.neo4art.service.MuseumService;

import java.util.Collection;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.server.plugins.Injectable;
import org.springframework.data.neo4j.server.SpringPluginInitializer;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.util.Assert;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since Apr 26, 2014
 */
public class Neo4ArtPluginInitializer extends SpringPluginInitializer
{
  private static final Logger logger = Logger.getLogger(Neo4ArtPluginInitializer.class.getName());

  @SuppressWarnings("unchecked")
  public Neo4ArtPluginInitializer()
  {
    super(new String[] { "META-INF/spring/application-context.xml" },
          expose("neo4jTemplate", Neo4jTemplate.class),
          expose("MuseumService", MuseumService.class));

    System.out.println("******* Spring context configured *******");

    logger.info("Spring context configured.");
  }

  @Override
  public Collection<Injectable<?>> start(GraphDatabaseService graphDatabaseService, Configuration config)
  {
    System.out.println("******* Starting spring context *******");
    
    logger.info("Starting spring context...");
    Collection<Injectable<?>> injectableCollection = super.start(graphDatabaseService, config);
    logger.info("Spring context started.");

    try
    {
      logger.info("Loading neo4jTemplate...");
      Neo4jTemplate neo4jTemplate = ctx.getBean(Neo4jTemplate.class);
      Assert.notNull(neo4jTemplate, "Spring Data Neo4j failed to initialize!");
      logger.info("Successfully loaded neo4jTemplate.");

      logger.info("Loading museumService...");
      MuseumService museumService = ctx.getBean(MuseumService.class);
      Assert.notNull(museumService, "Spring Data Neo4j failed to initialize!");
      logger.info("Successfully loaded userRepository.");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      logger.severe(e.getMessage());
    }

    System.out.println("******* Finished spring context *******");

    return injectableCollection;
  }

}