package uk.ac.mmu.sda.webmvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
class ApiController {

    static class GetResponse {
        private final String message;
        private final LocalDateTime timestamp;

        GetResponse(String message, LocalDateTime timestamp) {
            this.message = message;
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }

    static class PostResponse extends GetResponse {
        private final Map<String, Object> payload;

        PostResponse(String message,  LocalDateTime timestamp, Map<String, Object> payload) {
            super(message,timestamp);
            this.payload = payload;
        }

        public Map<String, Object> getPayload() {
            return payload;
        }
    }


    @GetMapping("**")
    public GetResponse getApi() {
        return new GetResponse("GET request received", LocalDateTime.now());
    }



    @PostMapping("**")
    public PostResponse postApi(@RequestBody Map<String, Object> payload) {
        return new PostResponse("POST request received",   LocalDateTime.now(), payload);
    }

}
