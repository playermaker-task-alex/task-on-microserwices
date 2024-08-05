package playermaker.com.playermakertask.service;

import playermaker.com.playermakertask.dto.TopPlayersResponse;

public interface SaveToDbService {
    void saveTopPlayers(TopPlayersResponse response);
}