package playermaker.com.playermakertask.service;

import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;

public interface CountService {
    TopPlayersResponse countParticipations(TopPlayersRequest request);
}