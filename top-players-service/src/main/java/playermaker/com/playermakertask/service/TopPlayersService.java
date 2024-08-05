package playermaker.com.playermakertask.service;

import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;

public interface TopPlayersService {
    TopPlayersResponse getTopPlayers(TopPlayersRequest request);
}