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
package it.inserpio.neo4art.repository;

import it.inserpio.neo4art.domain.Museum;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.SpatialRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 16.03.2014
 */
@Transactional
public interface MuseumRepository extends GraphRepository<Museum>, SpatialRepository<Museum>
{
  public static final String MUSEUM_GEOSPATIAL_INDEX = "museumLocation";
  
  @Query("MATCH (m:MUSEUM) WHERE m.name={name} RETURN distinct(m) as museum") 
  Museum findMuseumByName(@Param("name") String name);
  
  @Query("MATCH (a:ARTIST)<-[:AUTHOR]-(p:ARTWORK)-[:OFFICIAL_LOCATION]->(m:MUSEUM) WHERE id(a)={artistId} RETURN distinct(m) as museum") 
  List<Museum> findMuseumByArtist(@Param("artistId") long artistId);
  
  @Query("START museum=node:" + MuseumRepository.MUSEUM_GEOSPATIAL_INDEX + "({withinDistance}) RETURN museum") 
  List<Museum> findMuseumWithinDistance(@Param("withinDistance") String withinDistance);
}
