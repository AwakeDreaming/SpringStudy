package dao;

/**
 * @author gaowenhao
 * @date 2020-03-21 13:19
 */
public interface MyDao {

    String getBy(Long userId, String product);

    void update(Long userId);

    void deleteAll();

}
