package com.rtt.luxurycar.api.booking;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingServiceClient {

    private final RestTemplate restTemplate;
    private final String bookingBaseUrl;

    public BookingServiceClient(RestTemplate restTemplate,
                                @Value("${services.booking.base-url}") String bookingBaseUrl) {
        this.restTemplate = restTemplate;
        this.bookingBaseUrl = bookingBaseUrl;
    }

    private HttpHeaders buildForwardHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (auth != null && !auth.isBlank()) {
            headers.set(HttpHeaders.AUTHORIZATION, auth);
        }
        return headers;
    }

    public ResponseEntity<String> forward(HttpMethod method, String path, Object body, HttpServletRequest request) {
        HttpHeaders headers = buildForwardHeaders(request);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(bookingBaseUrl + path, method, entity, String.class);
    }

    public ResponseEntity<String> forwardGet(String path, HttpServletRequest request) {
        HttpHeaders headers = buildForwardHeaders(request);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(bookingBaseUrl + path, HttpMethod.GET, entity, String.class);
    }
}
