


```
oc create configmap jms-ibm-mq-sink-config --from-file configs/application.properties
```

To run the project you can use:

```
kamel run --config configmap:jms-ibm-mq-sink-config ColaIbmMq.java
```