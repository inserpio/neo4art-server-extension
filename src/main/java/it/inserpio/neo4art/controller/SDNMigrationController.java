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

import it.inserpio.neo4art.exception.MigrationException;
import it.inserpio.neo4art.service.SDNMigrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * REST API:
 * 
 * /migrate/toSDN301
 * /migrate/addMuseumsToSpatialIndex
 * 
 * @author Lorenzo Speranzoni
 * @since Mar 19, 2014
 */
@Controller
@RequestMapping("/migrate")
public class SDNMigrationController
{
  @Autowired
  @Qualifier("SDN301MigrationService")
  private SDNMigrationService migrationService;
  
  @RequestMapping(value="/toSDN301", method=RequestMethod.POST, produces={"application/xml", "application/json"})
  public ResponseEntity<String> toSDN301()
  {
    try
    {
      migrationService.migrate();
      
      return new ResponseEntity<String>("Migration to SDN 3.0.1 successful.", HttpStatus.OK);
    }
    catch (MigrationException me)
    {
      return new ResponseEntity<String>("Migration to SDN 3.0.1 failed: " + me.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @RequestMapping(value="/addMuseumsToSpatialIndex", method=RequestMethod.POST, produces={"application/xml", "application/json"})
  public ResponseEntity<String> addMuseumsToSpatialIndex()
  {
    try
    {
      migrationService.addMuseumsToSpatialIndex();
      
      return new ResponseEntity<String>("Museums added to spatial index.", HttpStatus.OK);
    }
    catch (MigrationException me)
    {
      return new ResponseEntity<String>("Error adding museums to spatial index: " + me.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
