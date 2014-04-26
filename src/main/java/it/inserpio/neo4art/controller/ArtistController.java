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
package it.inserpio.neo4art.controller;

import it.inserpio.neo4art.domain.Artist;
import it.inserpio.neo4art.repository.ArtistRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST API:
 * 
 * /artists
 * /artists/0
 * /artists/lastname/Van%20Gogh
 * /
 * 
 * @author Lorenzo Speranzoni
 * @since Mar 19, 2014
 */
@Controller
@RequestMapping("/artists")
public class ArtistController
{
  @Autowired
  private ArtistRepository artistRepository;
  
  /**
   * Retrieve artists by id (i.e. nodeId)
   * 
   * @param artistId
   * @return
   */
  @RequestMapping(value="/{artistId}", method=RequestMethod.GET, produces={"application/xml", "application/json"})
  public @ResponseBody Artist getArtist(@PathVariable long artistId)
  {
    return this.artistRepository.findOne(artistId);
  }
  
  /**
   * Retrieve artists by last name
   * 
   * @param lastName
   * @return
   */
  @RequestMapping(value="/lastname/{lastName}", method=RequestMethod.GET, produces={"application/xml", "application/json"})
  public @ResponseBody Artist getArtist(@PathVariable String lastName)
  {
    return this.artistRepository.findArtistByLastName(lastName);
  }
  
  /**
   * Retrieve all artists
   * 
   * @return
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(method=RequestMethod.GET, produces={"application/xml", "application/json"})
  public @ResponseBody List<Artist> getArtists()
  {
    return this.artistRepository.findAll().as(List.class);
  }
}
