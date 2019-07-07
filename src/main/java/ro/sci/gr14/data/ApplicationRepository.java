package ro.sci.gr14.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.City;
import ro.sci.gr14.model.County;
import ro.sci.gr14.model.DBEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 * <p>
 */

@Repository
public class ApplicationRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public ApplicationRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }
    
    public List<County> findAllCounty() {
        return jdbc.query("select id, name from county",
                this::mapRowToCounty);
    }

    private County mapRowToCounty(ResultSet rs, int rowNum)
            throws SQLException {
        return new County(rs.getLong("id"), rs.getString("name"));
    }

    public List<City> findAllCity() {
        return jdbc.query("select id, name from city",
                this::mapRowToCity);
    }

    public List<City> findAllCityByCounty(String countyname) {
        return jdbc.query("select id, name from city where countyname='"+countyname+"'",
                this::mapRowToCity);
    }

    private City mapRowToCity(ResultSet rs, int rowNum)
            throws SQLException {
        return new City(rs.getLong("id"), rs.getString("name"));
    }

    public List<DBEntity> findAllHandicraft() {
        return jdbc.query("select id, name from handicraft",
                this::mapRowToDBEntity);
    }

    private DBEntity mapRowToDBEntity(ResultSet rs, int rowNum)
            throws SQLException {
        return new DBEntity(rs.getLong("id"), rs.getString("name"));
    }
}
