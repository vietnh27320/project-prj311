package dao;

import java.util.List;

//tao interface tọa ra sự nhất quán giữa các obj dễ cho việc debug và maintain.
//khi mà dùng quá nhiều hàm chức năng giống nhau
//interface ko body, ham trong interface mac dinh la public
//crud cre,read,update,delete
//toan tu kim cuong , tạo ra 1 đối tượng đại diện cho thằng khác
public interface ICommonDao<T> {

    List<T> getAll();

    T getOne(int id);

    boolean add(T obj);

    boolean update(int id, T obj);

    boolean delete(int id);

}
