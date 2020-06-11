package comm.example.dao;

import comm.example.model.Page;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@EnableAutoConfiguration
@EnableTransactionManagement
public interface PageRepository extends CrudRepository<Page,Integer> {
}
