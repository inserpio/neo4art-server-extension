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

package it.inserpio.neo4art.rest;

import it.inserpio.neo4art.domain.Museum;
import it.inserpio.neo4art.service.MuseumService;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lorenzo Speranzoni
 * @since Apr 26, 2014
 */

@Controller
@Path("/museums")
public class MuseumRestController
{
  private static final Logger logger = Logger.getLogger(MuseumRestController.class.getName());
  
  @Context
  private MuseumService museumService;
  
  @GET
  @Path("/lon/{lon}/lat/{lat}/distanceInKm/{distanceInKm}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Transactional
  public Response getMuseumsWithinDistance(@PathParam("lon") double longitude, @PathParam("lat") double latitude, @PathParam("distanceInKm") double distanceInKm)
  {
    logger.info(String.format("GET /museums/lon/%s/lat/%s/distanceInKm/%s", longitude, latitude, distanceInKm));
  
      List<Museum> museumList = this.museumService.getMuseumsWithinDistance(longitude, latitude, distanceInKm);
    
      logger.info("Number of museums found: " + ((museumList != null) ? museumList.size() : "null"));
    
      GenericEntity<List<Museum>> entity = new GenericEntity<List<Museum>>(museumList) {};
    
      return Response.ok(entity).build();
  }
}
