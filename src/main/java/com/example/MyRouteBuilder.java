package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.LoggingLevel;
import org.springframework.stereotype.Component;

/**
 * A Camel Java DSL Router
 */

@Component
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() throws Exception {

        from("jetty://http://localhost:8888/greeting")
                .log("Received a request")
                .setBody(simple("Hello, world!"))
                .to("jetty://https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions?bridgeEndpoint=true&amp;throwExceptionOnFailure=false")
                .log("Sent a request")
                .to("jolt:spec.json?inputType=JsonString&outputType=JsonString");

//        restConfiguration()
//                .component("servlet")
//                .bindingMode(RestBindingMode.json);
//
//        rest().get("/hello")
//                .to("direct:hello");
//
//        from("direct:hello")
//                .routeId("myRouteHello")
//                .log(LoggingLevel.INFO, "Hello World")
//                .transform().simple("Hello World");
    }

}
