
package  com.example.arquestacion;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

@RestController

public class ApiController {
    @Autowired
    private ExternalService externalService;
    @GetMapping("/test-circuit-breaker")
    public String testCircuitBreaker() {
        return externalService.callExternalService();
    }
}