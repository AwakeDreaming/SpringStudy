package dao;

/**
 * @author gaowenhao
 * @date 2020-03-21 13:20
 */
public class MyDaoImpl implements MyDao {


    public String getBy(Long userId, String product) {
        String sql = "select * from User where userId = " + userId + ", product = " + product;
        System.out.println(sql);
        return sql;
    }

    public void update(Long userId) {
        System.out.println("exec update.");
    }

    public void deleteAll() {
        System.out.println("exec delete.");
    }

}
