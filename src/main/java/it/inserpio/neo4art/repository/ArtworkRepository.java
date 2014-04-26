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

import it.inserpio.neo4art.domain.Artwork;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 16.03.2014
 */
public interface ArtworkRepository extends GraphRepository<Artwork>
{
  /**
   * Retrieve an artwork by its title (with an auto-generated query).
   * 
   * @param title
   * @return
   */
  Artwork findArtworkByTitle(String title);

  /**
   * Retrieve artworks through 'AUTHOR' relationship with ARTIST nodes
   *  
   * @param artistId
   * @return
   */
  @Query("MATCH (a:ARTIST)<-[:AUTHOR]-(p:ARTWORK) WHERE id(a)={artistId} RETURN p")
  List<Artwork> findArtworksByArtist(@Param("artistId") long artistId);

  /**
   * Retrieve artworks through 'OFFICIAL_LOCATION' relationship with MUSUEM nodes
   * @param museumId
   * @return
   */
  @Query("MATCH (p:ARTWORK)-[:OFFICIAL_LOCATION]->(m:MUSEUM) WHERE id(m)={museumId} RETURN p")
  List<Artwork> findArtworkByMuseum(@Param("museumId") long museumId);

  /**
   * Retrieve artworks by artist author id and musuem id that hosts it through 'AUTHOR' and 'OFFICIAL_LOCATION' relationships
   * 
   * @param artistId
   * @param museumId
   * @return
   */
  @Query("MATCH (a:ARTIST)<-[:AUTHOR]-(p:ARTWORK)-[:OFFICIAL_LOCATION]->(m:MUSEUM) WHERE id(a)={artistId} AND id(m)={museumId} RETURN p") 
  List<Artwork> findArtworkByArtistAndMuseum(@Param("artistId") long artistId, @Param("museumId") long museumId);
}
