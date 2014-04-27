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

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Stage entity class
 * 
 * @author Lorenzo Speranzoni
 * @since 11.02.2014
 */
@NodeEntity
@TypeAlias("STAGE")
@XmlRootElement(name="stage")
@JsonAutoDetect
public class Stage extends AbstractEntity
{
  public static final String RELATIONSHIP_IN = "IN";
  public static final String RELATIONSHIP_MOVED_TO = "IN";
  
  private long  from;

  private long  to;

  @Fetch
  @RelatedTo(type = RELATIONSHIP_IN, direction = Direction.OUTGOING, elementClass = City.class)
  private City  in;

  @Fetch
  @RelatedTo(type = RELATIONSHIP_MOVED_TO, direction = Direction.OUTGOING, elementClass = Stage.class)
  private Stage next;

  public Stage(Date from, Date to)
  {
  }

  public long getFrom()
  {
    return from;
  }

  public void setFrom(long from)
  {
    this.from = from;
  }

  public long getTo()
  {
    return to;
  }

  public void setTo(long to)
  {
    this.to = to;
  }

  public City getIn()
  {
    return in;
  }

  public void setIn(City in)
  {
    this.in = in;
  }

  public Stage getNext()
  {
    return next;
  }

  public void setNext(Stage next)
  {
    this.next = next;
  }

  @Override
  public String toString()
  {
    return "Stage [getId()=" + getId() + ", from=" + from + ", to=" + to + ", in=" + in + ", next=" + next + "]";
  }
}
