package self.learning.backend.lib.mgmt;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer postgresContainer() {
        return new PostgreSQLContainer(DockerImageName.parse("postgres:18"));
    }

    /*
     * MySQL is kept as a commented learning reference only.
     * The KHAE Library baseline and the student integration tests use PostgreSQL.
     *
     * @Bean
     * @ServiceConnection
     * MySQLContainer mysqlContainer() {
     *     return new MySQLContainer(DockerImageName.parse("mysql:latest"));
     * }
     */
}
