// camel-k: language=java
// camel-k: dependency=camel:camel-quarkus-activemq
// camel-k: dependency=camel:camel-quarkus-timer
//// camel-k: property=period=5000
// camel-k: dependency=mvn:com.github.javafaker:javafaker:1.0.2
// camel-k: dependency=mvn:org.slf4j:slf4j-simple:1.7.32
// camel-k: dependency=mvn:com.ibm.mq:com.ibm.mq.allclient:9.2.4.0
//// camel-k: dependency=mvn:com.ibm.mqcom.ibm.mq.jakarta.client
/*
 * The above statements provide information required for running the example. This includes
 * the metadata informing the language used by this code and the dependencies used by
 * Camel K to run this example.
 * As for the dependencies, these are:
 * - camel-quarkus-jms and camel-quarkus-timer, which are from Camel, thus resolved
 * automatically (hence the prefix notation "camel:")
 * - The fully qualified Maven name of JavaFaker dependency, used to generate fake data
 */
import org.apache.camel.builder.RouteBuilder;
import com.github.javafaker.Faker;
import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.apache.camel.component.activemq.ActiveMQComponent;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
//import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.camel.BindToRegistry;
import org.apache.camel.PropertyInject;
import org.apache.camel.CamelContext;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.ibm.mq.jms.MQQueueConnectionFactory;
//import com.ibm.msg.client.jakarta.wmq.WMQConstants;
import com.ibm.msg.client.wmq.WMQConstants;
import java.util.Optional;



import com.ibm.mq.jms.JMSC;
public class ColaIbmMq extends RouteBuilder {
  @Inject
  PooledConnectionFactory pooledConnectionFactory;

  @Inject
  CamelContext camelContext;

  @PropertyInject("ibm.mq.host")
   private String ibm_mq_host;
   @PropertyInject("ibm.mq.port")
   private int ibm_mq_port;
   @PropertyInject("ibm.mq.channel")
   private String ibm_mq_channel;
   @PropertyInject("ibm.mq.queue-manager-name")
   private String ibm_mq_queueManagerName;
   @PropertyInject("ibm.mq.user")
   private String ibm_mq_user;
   @PropertyInject("ibm.mq.password")
   private String ibm_mq_password;

//variales de conexion
  //Logger logger = LoggerFactory.getLogger(ColaIbmMq.class.getName());
  @Override
  public void configure() throws Exception {
    /*
     * Explanation, method by method:
     *
     * - from("timer:{{period}}") Generate time-based events are a regular interval
     * defined by "period". The default period is 1 second - configured above - but
     * can be overriden using the --property flag (i.e.: --property
     * period=newPeriodValue)
     *
     * - bean(this, "generateFakePerson()") The code call the method
     * generateFakerPerson on this' object instance in order to generate a fake
     * person and a fake address.
     *
     * - to("log:info") Log the generated fake person name and address to the logger
     * using the info level
     *
     * This generates a log message that looks like this: (redacted...)
     * ExchangePattern: InOnly, BodyType: String, Body: So Effertz I lives on 967
     * Richie Ports
     *
     * - to("jms:{{jms.destinationType}}:{{jms.destinationName}}") This sends the
     * fake person data to the destination configured on the configuration file
     */
    from("timer:foo?fixedRate=true&period={{period}}")
    .bean(this, "generateFakePerson()")
    .to("log:info")
    .to("jms:queue:{{ibm.mq.queue}}?connectionFactory=#sslConnectionFactory");
  }
  public String generateFakePerson() {
    Faker faker = new Faker();
    return faker.name().fullName() + " lives on " + faker.address().streetAddress();
  }

  @BindToRegistry
  public MQQueueConnectionFactory sslConnectionFactory() throws Exception {


     MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
            mqQueueConnectionFactory.setHostName(ibm_mq_host);
            mqQueueConnectionFactory.setChannel(ibm_mq_channel);//communications link
            //mqQueueConnectionFactory.setPort(Integer.parseInt(ibm_mq_port));
            mqQueueConnectionFactory.setPort(ibm_mq_port);
            mqQueueConnectionFactory.setQueueManager(ibm_mq_queueManagerName);//service provider
            mqQueueConnectionFactory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
           // mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            mqQueueConnectionFactory.setStringProperty(WMQConstants.USERID, ibm_mq_user);
            mqQueueConnectionFactory.setStringProperty(WMQConstants.PASSWORD, ibm_mq_password);

		  // mqQueueConnectionFactory.setTransportType(1);
    return mqQueueConnectionFactory;

  }
}