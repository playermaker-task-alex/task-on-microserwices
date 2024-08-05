package playermaker.com.playermakertask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;

@Service
@Slf4j
public class ProxyServiceImpl implements ProxyService {

    @Value("http://localhost:8080")
    private String facadeServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<TopPlayersResponse> proxyRouting(TopPlayersRequest request) {
        log.trace("routing request to facade service {}", request);
        String url = String.format("%s/api/top-players", facadeServiceUrl);
        return restTemplate.postForEntity(url, request, TopPlayersResponse.class);
    }
}
