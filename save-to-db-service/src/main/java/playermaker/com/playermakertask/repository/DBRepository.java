package playermaker.com.playermakertask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playermaker.com.playermakertask.entity.TopPlayersEntity;

@Repository
public interface DBRepository extends JpaRepository<TopPlayersEntity, Long> {

}
