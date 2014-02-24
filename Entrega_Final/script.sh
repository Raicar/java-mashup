#!/bin/bash

rm /home/raicar/Software/apache-tomcat-7.0.35/webapps/*.war
rm -rf /home/raicar/Software/apache-tomcat-7.0.35/webapps/internalprovider-service
rm -rf /home/raicar/Software/apache-tomcat-7.0.35/webapps/mashup-ui-3.0.2
rm -rf /home/raicar/Software/apache-tomcat-7.0.35/webapps/productnews

cp internalprovider/target/internalprovider-service.war /home/raicar/Software/apache-tomcat-7.0.35/webapps
cp productnews/target/productnews.war /home/raicar/Software/apache-tomcat-7.0.35/webapps
cp ui/target/mashup-ui-3.0.2.war /home/raicar/Software/apache-tomcat-7.0.35/webapps
