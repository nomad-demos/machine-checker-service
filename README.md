# Machine Status Checker Through the JFrog Connect API


This is a simple spring boot app that is intended to be run as a Spring Boot Scheduled task. There are webservices enabled just for the health checks 
in Kube.

Currently, it uses the JFrog Connect[https://connect.jfrog.io] API to query for all the devices_details for all the devices in the customerModeling Connect group.  
It queries every 5 seconds but should be 15 in production, to match the Connect update frequency.

After it retrieves the array of devices_details and parses them into an Array of Machine objects. We iterate through that Array
and send the current CPU and RAM usage for the device to the cache. The key will be the value from the Connect "device_id" with the 
addition of either "-CPU" or "-RAM" on the end. The value will be the value for the machines current percentage of CPU and RAM utilization. 


It requires environment variables to run properly:
1. `connect_user_token` set to your Connect user token . It is recommended that you set this up through a configmap/secrets in the kube namespace running the service. When running locally, put in an environment variable
2. It also need the locator host and port for the gemfire cache 
```
locator_host=

locator_port=
```

TODO 
2. Get it to send the results as an update to our Gemfire instance
3. We need to have a postgres instance that stores all the different currencies to check and use Gemfire to cache the current list so we are not always hitting the DB every minutes.
   
Number 5 actually doesn't matter in this service - just the todo's above. 
5. The DB needs to store a mapping of users to currencies and their buy and sell prices (this is a many to many table with the currency, user, buy, sell, and time stamp). This table really only matters to the Rabbit notifier instance and the web app to show users in the web interface the latest prices of their currencies of interest.



## Starting the cache locally
```commandline
gfsh 

start locator --name=connectlocator --port=10336 --bind-address=127.0.0.1

start server --name=connectserver --server-port=40405 --server-bind-address=127.0.0.1 --bind-address=127.0.0.1

create region --name=machine-metrics --type=local
```

reattaching to the locator

```commandline
gfsh

connect --locator=127.0.0.1[10336]
```

Add a class org.molw.ValueChangeWriter to the machine-metrics region from a JAR file. The JAR file is in the 
same directory where you start gfsh
````commandline
gfsh

deploy --jar=.\gemfire-trigger-0.1-SNAPSHOT.jar

alter region --name=machine-metrics --cache-writer=org.molw.ValueChangeWriter
````




## General Follow up on using the operator - 


### we may need to create a custom CRD for the operator to see the log messages.
https://docs.vmware.com/en/VMware-Tanzu-GemFire-for-Kubernetes/1.0/tgf-k8s/GUID-crd.html#member-configuration 

and under spec.locators overrides.gemfireProperties we would write something like this

```yaml
overrides:
  gemfireProperties:
    redirect-output: "true"
```

## Uploading the Jar file
I should use kubectl cp to get the Jar file on the locator pod. And then shell in to the pod and deploy the jar. Then alter to the region to add the right class file. 


To get this to run I need to add
```text
--add-exports jdk.management/com.sun.management.internal=ALL-UNNAMED
```
to the compilations options for Java in the project 
AND
I had to uncheck --release 