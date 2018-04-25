package Repository;

import org.springframework.data.repository.CrudRepository;

import Entity.Statistics;

public interface statisticsRepository extends CrudRepository <Statistics, String>
{

}
