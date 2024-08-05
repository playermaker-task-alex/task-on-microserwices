package playermaker.com.playermakertask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopPlayersResponse {
    private int requiredTopPlayers;
    private List<PlayerParticipationCount> playerParticipationCounts;
}