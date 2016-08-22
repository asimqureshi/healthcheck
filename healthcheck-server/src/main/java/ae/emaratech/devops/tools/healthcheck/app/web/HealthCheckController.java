package ae.emaratech.devops.tools.healthcheck.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ae.emaratech.devops.tools.healthcheck.app.service.HealthCheckService;

@RestController
public class HealthCheckController {

  @Autowired
  HealthCheckService service;

  @RequestMapping("/")
  public String index() throws ExecutionException, InterruptedException, TimeoutException {

    return "index";
  }

  @MessageMapping("/hello")
//  @SendTo("/topic/greetings")
  @RequestMapping("/hc")
  public String healthCheck(@RequestParam(value="apps", required=false, defaultValue="ALL") String[] apps) throws ExecutionException, InterruptedException, TimeoutException {

    Future<String> message = service.submitHealthCheckRequest(apps);

    return message.get(1, TimeUnit.SECONDS);
  }





}
