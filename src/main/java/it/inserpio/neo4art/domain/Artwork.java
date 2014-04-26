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

package it.inserpio.neo4art.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author Lorenzo Speranzoni
 * @since Mar 19, 2014
 */
@NodeEntity
@TypeAlias("ARTWORK")
public class Artwork extends AbstractEntity
{
  public static final String AUTHOR = "AUTHOR";
  
  @Indexed(unique=true)
  private String title;
  
  private String month;
  
  private String year;
  
  private String thumbnail;
  
  private String type;
  
  private String f_order;
  
  private String jh_order;

  @Fetch
  @RelatedTo(type = AUTHOR, direction = Direction.OUTGOING, elementClass = Artist.class)
  private Artist artist;
  
  public Artwork()
  {
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getMonth()
  {
    return month;
  }

  public void setMonth(String month)
  {
    this.month = month;
  }

  public String getYear()
  {
    return year;
  }

  public void setYear(String year)
  {
    this.year = year;
  }

  public String getThumbnail()
  {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail)
  {
    this.thumbnail = thumbnail;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getFOrder()
  {
    return f_order;
  }

  public void setFOrder(String f_order)
  {
    this.f_order = f_order;
  }

  public String getJHOrder()
  {
    return jh_order;
  }

  public void setJHOrder(String jh_order)
  {
    this.jh_order = jh_order;
  }

  public Artist getArtist()
  {
    return artist;
  }

  public void setArtist(Artist artist)
  {
    this.artist = artist;
  }
}
