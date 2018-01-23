package com.bsuir.dao.impl;

import com.bsuir.dao.FowlDao;
import com.bsuir.model.Fowl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@link Repository} @Repository - аннотация, которая указывает, что данный класс работает
 * с базой данных - реализует логику создания и обработки запросов к ней.
 */

@Repository
public class FowlDaoImpl implements FowlDao {

    // Наши бин, который мы объявляли в классе DataConfig
    private NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public FowlDaoImpl(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) throws SQLException {
        this.jdbcTemplate = jdbcTemplate;
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("fowl")
                .usingColumns("name", "description", "number");
        jdbcInsert.setGeneratedKeyName("id");
    }

    @Override
    public int save(Fowl fowl) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", fowl.getName());
        parameterSource.addValue("description", fowl.getDescription());
        parameterSource.addValue("number", fowl.getNumber());

        return (int) jdbcInsert.executeAndReturnKey(parameterSource);
    }

    private static final String QUERY_GET_BY_NAME =
            "SELECT * FROM fowl WHERE name = :name AND deleted = 0";

    @Override
    public Fowl getFowlByName(String fowlName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", fowlName);
        return jdbcTemplate.queryForObject(QUERY_GET_BY_NAME, parameterSource, new FowlRowMapper());
    }

    private static final String QUERY_GET_BY_ID =
            "SELECT * FROM fowl WHERE id = :id AND deleted = 0";

    @Override
    public Fowl getFowlById(int fowlId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", fowlId);
        return jdbcTemplate.queryForObject(QUERY_GET_BY_ID, parameterSource, new FowlRowMapper());
    }

    private static final String QUERY_GET_ALL =
            "SELECT * FROM fowl WHERE deleted = 0";

    @Override
    public List<Fowl> getAll() {
        return jdbcTemplate.query(QUERY_GET_ALL, new MapSqlParameterSource(), new FowlRowMapper());
    }


    private static final String QUERY_UPDATE =
            "UPDATE fowl SET name=:name, number=:number, description=:description WHERE id=:id AND deleted = 0";

    @Override
    public void update(Fowl fowl) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", fowl.getName());
        parameterSource.addValue("description", fowl.getDescription());
        parameterSource.addValue("number", fowl.getNumber());
        parameterSource.addValue("id", fowl.getId());

        jdbcTemplate.update(QUERY_UPDATE, parameterSource);
    }

    private static final String QUERY_DELETE =
            "UPDATE fowl SET deleted = 1 WHERE id=:id";

    @Override
    public boolean deleteById(long fowlId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", fowlId);

        return jdbcTemplate.update(QUERY_DELETE, parameterSource) != 0;
    }

    private static final class FowlRowMapper implements RowMapper<Fowl> {
        @Override
        public Fowl mapRow(ResultSet resultSet, int i) throws SQLException {
            if (resultSet != null) {
                Fowl fowl = new Fowl();
                fowl.setId(resultSet.getInt("id"));
                fowl.setName(resultSet.getString("name"));
                fowl.setDescription(resultSet.getString("description"));
                fowl.setNumber(resultSet.getInt("number"));
                return fowl;
            } else return null;
        }
    }
}
