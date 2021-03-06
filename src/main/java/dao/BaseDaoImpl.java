package dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @ClassName BaseDaoImpl
 * @Description 所有DAO层的实现类都可以继承该类
 * Author sf
 * Date 18-10-26 下午9:13
 * @Version 1.0
 **/
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class clazz;

    public BaseDaoImpl() {
        // CustomerDaoImpl extends BaseDaoImpl<Customer>
        Class c = this.getClass();    // 创建子类时会调用父类的构造函数,this表示的是子类
        // c 表示 CustomerDaoImpl, 需要拿到Customer

        Type type = c.getGenericSuperclass();   //BaseDaoImpl<Customer>
        // 把type接口转化成子接口
        if(type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) type;
            Type[] types = ptype.getActualTypeArguments();  // 拿到泛型
            this.clazz = (Class) types[0];      //实际工作的类 Customer
        }
    }

    @Override
    public void save(T t) {
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        this.getHibernateTemplate().saveOrUpdate(t);
    }

    @Override
    public T findById(String id) {
        return (T) this.getHibernateTemplate().get(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
    }

    @Override
    public long count() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        detachedCriteria.setProjection(Projections.rowCount());

        List<T> ts = (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());

        return ts.size();
        //List<Number> list = (List<Number> )this.getHibernateTemplate().findByCriteria(detachedCriteria);
//        if(list != null && list.size() > 0){
//            return list.get(0).longValue();
//        }

        //return 0;
    }

}
