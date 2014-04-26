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

import it.inserpio.neo4art.domain.Artist;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 16.03.2014
 */
public interface ArtistRepository extends GraphRepository<Artist>
{
  @Query("MATCH (a:ARTIST {last_name: {last_name}}) RETURN a")
  Artist findArtistByLastName(@Param("last_name") String lastName);
}
