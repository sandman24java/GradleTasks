package task16_17.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import task16_17.exception.DBConnectionException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConfig {

    // Храним единственный объект пула соединений
    private static DataSource dataSource = null;

    static {
        // Создаём конфигурацию для HikariCP
        HikariConfig config = new HikariConfig();

        // Говорим Hikari, куда подключаться: PostgreSQL на localhost, база car
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/car");

        // Сообщаем драйвер, который должен использоваться
        config.setDriverClassName("org.postgresql.Driver");

        // Передаём PostgreSQL имя пользователя
        config.setUsername("postgres");

        // Передаём PostgreSQL пароль
        config.setPassword("postgres");

        // Указываем, что работать будем внутри схемы module_two
        config.setSchema("module_two");

        // Разрешаем авто-commit: каждое действие автоматически фиксируется
        config.setAutoCommit(true);

        // Минимальное число "живых" соединений, которые пул должен держать
        config.setMinimumIdle(2);

        // Через сколько миллисекунд простаивающее соединение можно закрыть
        config.setIdleTimeout(40000);

        // Максимальное количество соединений, которые пул может открыть
        config.setMaximumPoolSize(10);

        // Максимальное время ожидания получения соединения из пула
        config.setConnectionTimeout(10000);

        // Как часто пул будет "будить" соединения, чтобы они не протухли
        config.setKeepaliveTime(30000);

        // Создаём сам пул соединений согласно конфигурации
        dataSource = new HikariDataSource(config);
    }
    public static Connection getConnection() throws DBConnectionException {
        try {
            return dataSource.getConnection();
        } catch (SQLException connectionException) {
            throw new DBConnectionException("Could not connect to DB", connectionException);
        }
    }
}
