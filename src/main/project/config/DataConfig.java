package main.project.config;

import main.project.model.persistence.PetMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("main.project.model.persistence")
public class DataConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/test_project_db");
        dataSource.setPassword("admin");

//        /**
//         * Block for table initiation and sample data insertion
//         * Код для инициализации таблиц и первичного наполнения
//         */
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS animals (" +
//                "animal_id BIGSERIAL NOT NULL UNIQUE PRIMARY KEY ," +
//                "name VARCHAR(255) NOT NULL, " +
//                "description TEXT)");
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS pets (" +
//                "id BIGSERIAL NOT NULL UNIQUE PRIMARY KEY ," +
//                "name VARCHAR(255) NOT NULL, " +
//                "animal_id BIGINT REFERENCES animals(animal_id)," +
//                "weight REAL, " +
//                "adopted BOOLEAN DEFAULT FALSE)");
//        jdbcTemplate.execute("INSERT INTO animals (name, description) VALUES " +
//                "('Cat', 'Feline creature, 4 legs')," +
//                "('Dog', 'Canine creature, 4 legs')");
//        jdbcTemplate.execute("INSERT INTO pets (name, animal_id, weight, adopted) VALUES " +
//                "('Mike', 2, 1350, false)," +
//                "('Fenrir', 1, 3550, true)," +
//                "('Slim', 2, 990, false)," +
//                "('Catherine', 2, 9000, false)");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("main.project.model.entity");
        sessionFactory.setMapperLocations(ResourcePatternUtils.getResourcePatternResolver(resourceLoader).
                getResources("classpath:persistence/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("org.mybatis.spring.sample.mapper");
        return configurer;
    }
}
