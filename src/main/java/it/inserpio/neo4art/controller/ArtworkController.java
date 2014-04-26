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

import it.inserpio.neo4art.domain.Artwork;
import it.inserpio.neo4art.repository.ArtworkRepository;

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
 * /artworks
 * /artworks/514
 * /artworks/title/Vincent's%20Chair%20with%20His%20Pipe
 * /artworks/artist/0
 * /artworks/museum/1051
 * /artworks/museum/1051/artist/0
 * 
 * @author Lorenzo Speranzoni
 * @since Mar 19, 2014
 */
@Controller
@RequestMapping("/artworks")
public class ArtworkController
{
  @Autowired
  private ArtworkRepository artworkRepository;
  
  /**
   * Retrieve artwork by id (i.e. nodeId)
   * 
   * @param artworkId
   * @return
   */
  @RequestMapping(value="/{artworkId}", method=RequestMethod.GET, produces={"application/xml", "application/json"})
  public @ResponseBody Artwork getArtwork(@PathVariable long artworkId)
  {
    return this.artworkRepository.findOne(artworkId);
  }
  
  /**
   * Retrieve artwork by title
   * 
   * @param title
   * @return
   */
  @RequestMapping(value="/title/{title}", method=RequestMethod.GET, produces={"application/xml", "application/json"})
  public @ResponseBody Artwork getArtworkByTitle(@PathVariable String title)
  {
    return this.artworkRepository.findArtworkByTitle(title);
  }
  
  /**
   * Retrieve artworks by artist id (i.e. nodeId)
   * 
   * @param artistId
   * @return
   */
  @RequestMapping(value="/artist/{artistId}", method=RequestMethod.GET, produces={"application/xml", "application/json"})
  public @ResponseBody List<Artwork> getArtworksByArtist(@PathVariable long artistId)
  {
    return this.artworkRepository.findArtworksByArtist(artistId);
  }
  
  /**
   * Retrieve artworks by museum id (i.e. nodeId)
   * 
   * @param museumId
   * @param 
   * @return
   */
  @RequestMapping(value="/museum/{museumId}", method=RequestMethod.GET, produces={"application/xml", "application/json"})
  public @ResponseBody List<Artwork> getArtworksByMuseum(@PathVariable long museumId)
  {
    return this.artworkRepository.findArtworkByMuseum(museumId); 
  }

  /**
   * Retrieve artworks by museum name and artist last name
   * 
   * @param museum
   * @param 
   * @return
   */
  @RequestMapping(value="/museum/{museumId}/artist/{artistId}", method=RequestMethod.GET, produces={"application/json", "application/xml"})
  public @ResponseBody List<Artwork> getArtworkByArtistAndMuseum(@PathVariable long museumId, @PathVariable long artistId)
  {
    return this.artworkRepository.findArtworkByArtistAndMuseum(artistId, museumId); 
  }
  
  /**
   * Retrieve all artworks
   * 
   * @return
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(method=RequestMethod.GET, produces={"application/xml", "application/json"})
  public @ResponseBody List<Artwork> getArtworks()
  {
    return this.artworkRepository.findAll().as(List.class);
  }
}
