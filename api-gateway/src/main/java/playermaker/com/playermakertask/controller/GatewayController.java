package playermaker.com.playermakertask.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;
import playermaker.com.playermakertask.service.ProxyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@Slf4j
public class GatewayController {

    @Autowired
    final ProxyService proxyService;

    @PostMapping("/top-players")
    ResponseEntity<TopPlayersResponse> getTopPlayers(@RequestBody TopPlayersRequest request) {
        log.trace("received request for top players {}", request);
        return proxyService.proxyRouting(request);
    }
}