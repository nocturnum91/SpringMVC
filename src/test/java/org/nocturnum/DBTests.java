package org.nocturnum;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class DBTests {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMySQLDBConnection() {

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/noc?useSSL=false&serverTimezone=Asia/Seoul", "nocturnum", "Smfdlajdajd1!")) {
            log.info(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostgreSQLDBConnection() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/noc", "nocturnum", "Smfdlajdajd1!")) {
            log.info(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Setter(onMethod_ = {@Autowired})
    private DataSource dataSource;

    @Test
    public void testDataSourceConnection() {
        try (Connection con = dataSource.getConnection()) {
            log.info(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Setter(onMethod_ = {@Autowired})
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testMyBatis() {
        try (SqlSession session = sqlSessionFactory.openSession();
             Connection con = session.getConnection()) {
            log.info(session);
            log.info(con);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


}
