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

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * City entity class
 * 
 * @author Lorenzo Speranzoni
 * @since 11.02.2014
 */
@NodeEntity
@TypeAlias("CITY")
@XmlRootElement(name="city")
@JsonAutoDetect
public class City extends AbstractEntity
{
  @Indexed(unique=true)
  private String      name;

  private String      province;

  private String      region;

  private Set<String> counties;

  private String      state;

  private String      country;

  private Double      latitude;
  
  private Double      longitude;
  
  public City()
  {
  }
  
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getProvince()
  {
    return province;
  }

  public void setProvince(String province)
  {
    this.province = province;
  }

  public String getRegion()
  {
    return region;
  }

  public void setRegion(String region)
  {
    this.region = region;
  }

  public Set<String> getCounties()
  {
    return counties;
  }

  public void setCounties(Set<String> counties)
  {
    this.counties = counties;
  }

  public String getState()
  {
    return state;
  }

  public void setState(String state)
  {
    this.state = state;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public Double getLatitude()
  {
    return latitude;
  }

  public void setLatitude(Double latitude)
  {
    this.latitude = latitude;
  }

  public Double getLongitude()
  {
    return longitude;
  }

  public void setLongitude(Double longitude)
  {
    this.longitude = longitude;
  }

  @Override
  public String toString()
  {
    return "City [name=" + name + ", province=" + province + ", region=" + region + ", counties=" + counties + ", state=" + state + ", country=" + country + ", latitude=" + latitude + ", longitude=" + longitude + "]";
  }
}
