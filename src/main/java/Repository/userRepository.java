package Repository;



import org.springframework.data.repository.CrudRepository;

import Entity.User;

public interface userRepository extends CrudRepository<User, String> {

}
