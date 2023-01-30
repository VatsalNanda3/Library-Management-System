//Repository for Library Entity

package nanda.vatsal.lib;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Integer> {

}
