## Requirments
You should have [docker](https://www.docker.com/get-started) istalled.

You should have [ActiveMQ](https://activemq.apache.org/components/classic/download/) istalled and  running on port:61616. 

If you have ActiveMQ running on another port you need to change the port number in the variable SERVER_CONNECTION in file:  parallelComputingPart3/src/main/java/Config.java. If you would like to not have to run on docker but in the idea you should change this variable to "tcp://localhost:PORT_NUMBER".

## Build and run producer
To build the producer image you will have to open a cmd/terminal in the directory: parallelComputingPart3/classes/artifacts/Producer_jar
When opened you need to run the command:
```bash
docker build -f Dockerfile -t mqproducer .
```

To run the docker image you need to peform the command in a cmd/terminal:
```bash
docker run -i -t mqproducer
```

When you run the producer it will ask for  the amount of elements you want to sort and how many consumers you are running. Make sure the consumers that you are running and the number in the producer match to get acurate results

## Build and run consumer
To build the producer image you will have to open a cmd/terminal in the directory: parallelComputingPart3/classes/artifacts/Consumer_jar
When opened you need to run the command:
```bash
docker build -f Dockerfile -t mqconsumer .
```

To run the docker image you need to peform the command in a cmd/terminal:
```bash
docker run -i -t mqconsumer
```
you can run these images as much times as you want consumers.
