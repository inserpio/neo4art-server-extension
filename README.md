Neo4Art Server Extension
========================

Neo4j Server Extension implemented with Spring Data Neo4j 3.0.1.

Build & Deploy
--------------

To build and deploy jar artifacts simply run:

	mvn clean package


The process of building will automatically build neo4art-server-extension-0.0.1.jar and copy dependencies to target/lib.
All jars must be copied into neo4j plugins directory.



REST Configuration
------------------
In neo4j-server.properties set:

	org.neo4j.server.thirdparty_jaxrs_classes=it.inserpio.neo4art=/neo4art



Test REST Endpoints
-------------------

Get museums within distance:

	curl -v -X GET http://localhost:7474/neo4art/museums/lon/-0.1283/lat/51.5086/distanceInKm/10.0

Get artworks by museum:

	curl -v -X GET http://localhost:7474/neo4art/artworks/museum/1051

