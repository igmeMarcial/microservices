
package  com.example.arquestacion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {
    private final RestTemplate restTemplate = new RestTemplate();
    @CircuitBreaker(name = "externalService", fallbackMethod = "fallbackMethod")
   
    public String callExternalService() {
        try {
            // Llamadas a microservicios
            String cliente = getForEntity("http://localhost:8081/cliente");
            String producto = getForEntity("http://localhost:8082/producto");
            String recomm = getForEntity("http://localhost:8083/recommend");

            // Combinar las respuestas en una sola cadena
            return cliente + " | " + producto + " | " + recomm;

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("Producto fuera de servicio.");
            }
            throw e; // Relanzar cualquier otro error HTTP
        }
    }
    private String getForEntity(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new RuntimeException("Servicio no encontrado en: " + url);
        }
        return response.getBody();
    }
    public String fallbackMethod(Exception ex) {
        return "El servicio no esta disponible en este momento. Por favor, intentelo mas tarde";
    }
}
