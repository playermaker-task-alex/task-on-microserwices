package playermaker.com.playermakertask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import playermaker.com.playermakertask.dto.PlayerParticipationCount;
import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CountServiceImpl implements CountService {

    @Override
    public TopPlayersResponse countParticipations(TopPlayersRequest request) {

        List<List<String>> participatedPlayers = request.getParticipatedPlayers();
        Map<String, Integer> playerParticipationCount = new HashMap<>();

        for (List<String> game : participatedPlayers) {
            for (String player : game) {
                playerParticipationCount.put(player, playerParticipationCount.getOrDefault(player, 0) + 1);
            }
        }

        List<PlayerParticipationCount> playerParticipationCounts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : playerParticipationCount.entrySet()) {
            playerParticipationCounts.add(new PlayerParticipationCount(entry.getKey(), entry.getValue()));
        }

        return new TopPlayersResponse(request.getRequiredTopPlayers(), playerParticipationCounts);
    }
}