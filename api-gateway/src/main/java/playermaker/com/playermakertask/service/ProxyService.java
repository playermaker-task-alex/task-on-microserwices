package playermaker.com.playermakertask.service;

import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;
import org.springframework.http.ResponseEntity;

public interface ProxyService {
    ResponseEntity<TopPlayersResponse> proxyRouting(TopPlayersRequest request);
}