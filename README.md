# Mnemosyne API

## Starting ActiveMQ

Run the docker file first with
`docker run -it -p 61616:61616 -p 8161:8161 -p 6572:6572 -e ACTIVEMQ_DISALLOW_WEBCONSOLE=false -e ACTIVEMQ_USERNAME=myactivemquser -e ACTIVEMQ_PASSWORD=myactivemquserpass -e ACTIVEMQ_WEBADMIN_USERNAME=root -e ACTIVEMQ_WEBADMIN_PASSWORD=pass symptoma/activemq:latest`
