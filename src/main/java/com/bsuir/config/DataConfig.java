package com.bsuir.config;

import com.bsuir.dao.utils.DaoUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Здесь я вольно изложу немного теории об данном приложении.
 * Итак. Начнём с того, что я использую Spring Framework - популярный инструмент для разработки
 * веб(и не только)-приложений на Java. Он использует несколько ключевых понятий:
 * 1) {@link Bean}. Бин - это объект, который "лежит" в "контейнере"(контексте) приложения и может быть легко
 * получен по своему id или "внедрён" в поле класса или переменную с помощью "Dependency Injection"(внедрение зависимостей).
 * То есть по сути бины избавляют нас от необходимости постоянно прописывать объект "с нуля"(через оператор new)
 * Выглядеть это может так:
 * {@link org.springframework.beans.factory.annotation.Autowired} @Autowired
 *                                                                private FowlDao fowlDao;
 * При такой записи спринг "ищет" в своём "контейнере" подходящий бин и "внедряет" его в это поле.
 * 2) {@link org.springframework.beans.factory.annotation.Autowired}. Переводится как "автоматическое связывание" -
 * пример выше.
 * 3) {@link org.springframework.stereotype.Component}. Компонент - это специальная аннотация, которая ставится
 * над объявлением класса для того, чтобы спринг "знал", что нужно создать бин этого класса и поместить его в
 * "контейнер". В Spring MVC приложении(коем наше приложение и является) используются следующий вариации компонента:
 * {@link Configuration}, {@link org.springframework.stereotype.Repository}, {@link org.springframework.stereotype.Service},
 * {@link org.springframework.stereotype.Controller}. Они олицетворяют так называемые "слои" веб-приложения.
 * Класс-конфигурация - это класс, в котором мы объявляем наши бины и указываем, в каких пакетах искать
 * компоненты({@link ComponentScan})
 */

@Configuration
@ComponentScan(basePackages = {"com.bsuir"})
public class DataConfig {

    // бин для создания соединения с БД
    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DaoUtils.DRIVER);
        ds.setUrl(DaoUtils.URL);
        return ds;
    }

    //бин для удобной работы с CRUD-операциями базы данных(используется в DAO-слое)
    @Bean
    public static NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
