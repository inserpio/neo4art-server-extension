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
 * Artist entity class
 * 
 * @author Lorenzo Speranzoni
 * @since 11.02.2014
 */
@NodeEntity
@TypeAlias("ARTIST")
public class Artist extends AbstractEntity
{
  private String first_name;

  private String second_name;

  @Indexed(unique=true)
  private String last_name;

  private long   born;

  private long   died;

  @Fetch
  @RelatedTo(type = "BORN_IN", direction = Direction.OUTGOING, elementClass = City.class)
  private City   born_in;

  @Fetch
  @RelatedTo(type = "DIED_IN", direction = Direction.OUTGOING, elementClass = City.class)
  private City   died_in;

  public Artist()
  {
  }

  public String getFirstName()
  {
    return first_name;
  }

  public void setFirstName(String firstName)
  {
    this.first_name = firstName;
  }

  public String getSecondName()
  {
    return second_name;
  }

  public void setSecondName(String secondName)
  {
    this.second_name = secondName;
  }

  public String getLastName()
  {
    return last_name;
  }

  public void setLastName(String lastName)
  {
    this.last_name = lastName;
  }

  public long getBorn()
  {
    return born;
  }

  public void setBorn(long born)
  {
    this.born = born;
  }

  public long getDied()
  {
    return died;
  }

  public void setDied(long died)
  {
    this.died = died;
  }

  public City getBornIn()
  {
    return born_in;
  }

  public void setBornIn(City bornIn)
  {
    this.born_in = bornIn;
  }

  public City getDiedIn()
  {
    return died_in;
  }

  public void setDiedIn(City diedIn)
  {
    this.died_in = diedIn;
  }

  @Override
  public String toString()
  {
    return "Artist [getId()=" + getId() + ", born=" + born + ", bornIn=" + born_in + ", died=" + died + ", diedIn=" + died_in + ", firstName=" + first_name + ", lastName=" + last_name + ", secondName=" + second_name + "]";
  }
}
