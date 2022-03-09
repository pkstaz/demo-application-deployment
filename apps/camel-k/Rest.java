import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;

public class Rest extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest()
            .get("/hello")
            .to("direct:hello");

        from("direct:hello")
            .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
            .log("Hello World")
            .transform().simple("Hello World");
    }
}