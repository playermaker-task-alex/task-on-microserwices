package playermaker.com.playermakertask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TopPlayersServiceImpl implements TopPlayersService {

    @Override
    public TopPlayersResponse getTopPlayers(TopPlayersRequest request) {

        Map<String, Integer> playerParticipationCount = request.getPlayerParticipationCount();

        List<String> mostParticipatedPlayers = playerParticipationCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(request.getRequiredTopPlayers())
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        return new TopPlayersResponse(request.getRequiredTopPlayers(), mostParticipatedPlayers);
    }
}
