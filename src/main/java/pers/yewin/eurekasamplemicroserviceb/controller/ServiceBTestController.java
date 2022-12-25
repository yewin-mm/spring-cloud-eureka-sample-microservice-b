package pers.yewin.eurekasamplemicroserviceb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.yewin.eurekasamplemicroserviceb.service.ServiceBTestService;

/**
 * @author: Ye Win
 * @created: 25/09/2022
 * @project: eureka-sample-microservice-b
 * @package: pers.yewin.eurekasamplemicroserviceb.controller
 */

@RestController
@Slf4j // for logging
public class ServiceBTestController {

    @Autowired
    ServiceBTestService bTestService;

    @GetMapping("/serviceBDemoAPI")
    public ResponseEntity<String> serviceBDemoAPI(){
        try {
            log.info("Enter serviceBDemoAPI api of Microservice B");
            String result = bTestService.serviceBProcess();
            log.info("response: {}", result);
            log.info("Exit serviceBDemoAPI api");
            return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/callServiceAFromServiceBDirectAPICall")
    public ResponseEntity<String> callServiceAFromServiceBDirectAPICall(){
        try {
            log.info("Enter callServiceAFromServiceBDirectAPICall api");
            String result = bTestService.callServiceAFromServiceBDirectAPICall();
            log.info("response: {}", result);
            log.info("Exit callServiceAFromServiceBDirectAPICall api");
            return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    /**
     * This below api will call all instances of service-a,
     * Whether service-a has many instances or not, whatever, it can call.
     * Because we used Load Balancer to call api
     * And that Load Balancer will find as per Service Name which our application registered in Eureka Server as per application name in yml file.
     * Load Balancer will call as Round Robin rule (you can find more about load balancer in google)
     */
    @GetMapping("/callServiceAFromServiceBThroughEureka")
    public ResponseEntity<String> callServiceAFromServiceBThroughEureka(){
        try {
            log.info("Enter callServiceAFromServiceBThroughEureka api");
            String result = bTestService.callServiceAFromServiceBThroughEureka();
            log.info("response: {}", result);
            log.info("Exit callServiceAFromServiceBThroughEureka api");
            return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
