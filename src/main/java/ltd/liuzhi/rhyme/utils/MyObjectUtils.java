package ltd.liuzhi.rhyme.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


/**
 * 对象处理工具类
 * @author LiuZhi
 */
public class MyObjectUtils
{
    private static final Long CACHE_TIME = 300L;
    private static final String FIELD_KEY = "field_all:%s";
    private static final String FIELD_ALL_KEY = "field:%s";
    private static final String METHOD_KEY = "method_all:%s";
    private static final String METHOD_ALL_KEY = "method:%s";

    private MyObjectUtils(){}

    /**
     * 实例化对象
     * @param clazz 类
     * @return 对象
     */
    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiate(clazz);
    }

    /**
     * 实例化对象
     * @param clazzStr 类名
     * @return 对象
     */
    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T instantiate(Class<T> clazz) {
        MyUtils.notNull(clazz, "Class must not be null");
        if (clazz.isInterface()) {
            throw new RuntimeException("不能对接口实例化");
        } else {
            try {
                return clazz.newInstance();
            } catch (InstantiationException var2) {
                throw new RuntimeException("不能对抽象类创建实例");
            } catch (IllegalAccessException var3) {
                throw new RuntimeException("改类构造函数私有化不能创建实例");
            }
        }
    }

    /**
     * copy 简单的对象属性到另一个对象
     * @param obj 对象
     * @param clazz 类名
     * @return T 返回新的对象
     */
    public static <T> T copy(Object obj, Class<T> clazz)
    {
        return copy(obj,clazz,false);
    }

    /**
     * copy 简单的对象属性到另一个对象
     * @param obj 对象
     * @param clazz 类名
     * @param ignoreUnderscore 是否忽略下划线
     * @return T 返回新的对象
     */
    public static <T> T copy(Object obj, Class<T> clazz,boolean ignoreUnderscore){
        T to = newInstance(clazz);
        if(obj == null)
        {
            return to;
        }
        Class source = obj.getClass();
        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = clazz.getDeclaredFields();

        for(Field sourceField : sourceFields)
        {
            for(Field targetField : targetFields)
            {
                if(ignoreUnderscore ? sourceField.getName().replaceAll("_","").equalsIgnoreCase(targetField.getName().replaceAll("_",""))
                        : sourceField.getName().equalsIgnoreCase(targetField.getName())
                        && sourceField.getType() == targetField.getType())
                {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    try {
                        targetField.set(to,sourceField.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return to;
    }

    /**
     * copyNotNull 将源对象里面不为Nu'll的对象拷贝到目标对象里面
     * @param sourceObj 源对象
     * @param targetObj 目标对象
     */
    public static <T> void copyNotEmpty(T sourceObj, T targetObj) {
        if(sourceObj == null)
        {
            return;
        }
        Class target = targetObj.getClass();
        Class source = sourceObj.getClass();
        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        for(Field sourceField : sourceFields)
        {
            sourceField.setAccessible(true);
            try {
                Object obj = sourceField.get(sourceObj);
                if(MyObjectUtils.objIsEmpty(obj))
                {//数据源属性为空跳过本次循环
                    continue;
                }
                for(Field targetField : targetFields)
                {
                    if(sourceField.getName().equalsIgnoreCase(targetField.getName()) && sourceField.getType() == targetField.getType())
                    {//如果目标对象没有数据则替换数据
                        targetField.setAccessible(true);
                        if(MyObjectUtils.objIsEmpty(targetField.get(targetObj)))
                        {
                            targetField.set(targetObj,obj);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断对象是否为空
     * @param obj 需要判断的对象
     * @return 如果是空则返回真,否则取反
     */
    public static boolean objIsEmpty(Object obj)
    {
        if(obj == null)
        {
            return true;
        }else if(obj instanceof String)
        {
            return MyStringUtils.isEmpty((String) obj);
        }else if(obj instanceof Collection)
        {
            return ((Collection) obj).isEmpty();
        }else if(obj instanceof Map)
        {
            return ((Map) obj).isEmpty();
        }
        return false;

    }

//    /**
//     * 判断对象是否相等(同类才有意义)
//     * @param obj1 对象1
//     * @param obj2 对象2
//     * @return 如果对象相等则true,否则false
//     */
//    public static boolean objIsEquals(Object obj1,Object obj2)
//    {
//        if(obj1 == obj2)
//        {
//            return true;
//        }
//        if(obj1 != null && obj2 != null && obj1.equals(obj2))
//        {
//            return true;
//        }
//        if(obj1.getClass() != obj2.getClass())
//        {
//            return false;
//        }
//        if(obj1 instanceof Iterable){
//            Iterable iterable1 = (Iterable) obj1;
//            Iterable iterable2 = (Iterable) obj2;
//            boolean isTrue = false;
//        }
//        //如果是基本类
//        if(obj1.getClass().getClassLoader() == null)
//        {
//            return false;
//        }
//        Field[] fields = obj1.getClass().getFields();
//        for(Field field : fields)
//        {
//            try {
//                Object object1 = field.get(obj1);
//                Object object2 = field.get(obj2);
//                if(obj1 != obj2)
//                {
//
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return true;
//    }

    /**
     * 判断object是否为基本类型
     * @param object
     * @return
     */
    public static boolean isBaseType(Object object) {
        Class className = object.getClass();
        if (className.equals(java.lang.Integer.class) ||
                className.equals(java.lang.Byte.class) ||
                className.equals(java.lang.Long.class) ||
                className.equals(java.lang.Double.class) ||
                className.equals(java.lang.Float.class) ||
                className.equals(java.lang.Character.class) ||
                className.equals(java.lang.Short.class) ||
                className.equals(java.lang.Boolean.class)) {
            return true;
        }
        return false;
    }

//    public static void main(String[] args) {
////        BigDecimal bigDecimal1 = new BigDecimal(1);
////        BigDecimal bigDecimal2 = new BigDecimal(2);
////        System.err.println(objIsEquals(bigDecimal1,bigDecimal2));
//        String[] str1 = new String[]{"1","2"};
//        String[] str2 = new String[]{"1","2"};
//        System.err.println(objIsEquals(str1,str2));
//    }

    /**
     * 获取对象的全部字段(包括父类跟隐私字段)
     * @param cls 类
     * @return
     */
    public static List<Field> getObjectAllField(Class cls)
    {
        String key = String.format(FIELD_ALL_KEY,cls.getName());
        if(MyCacheUtils.exist(key))
        {
            return MyCacheUtils.get(key,List.class);
        }
        List<Field> fields = new ArrayList<>();
        List<Class> classes = getAllClass(cls);
        classes.forEach(c->{
            fields.addAll(getObjectField(c));
        });
        MyCacheUtils.put(key,fields,CACHE_TIME);
        return fields;
    }

    /**
     * 获取对象的全部字段(不包括父类)
     * @param cls 类
     * @return
     */
    public static List<Field> getObjectField(Class cls)
    {
        String key = String.format(FIELD_KEY,cls.getName());
        if(MyCacheUtils.exist(key))
        {
            return MyCacheUtils.get(key,List.class);
        }
        List<Field> fields = MyCollectionUtils.arrayToList(cls.getDeclaredFields());
        fields.addAll(MyCollectionUtils.arrayToList(cls.getFields()));
        MyCacheUtils.put(key,fields,CACHE_TIME);
        return fields;
    }

    /**
     * 获取对象的全部方法(包括父类跟隐私方法)
     * @param cls 类
     * @return
     */
    public static List<Method> getObjectAllMethod(Class cls)
    {
        String key = String.format(METHOD_ALL_KEY,cls.getName());
        if(MyCacheUtils.exist(key))
        {
            return MyCacheUtils.get(key,List.class);
        }
        List<Method> methods = new ArrayList<>();
        List<Class> classes = getAllClass(cls);
        classes.forEach(c->{
            methods.addAll(getObjectMethod(c));
        });
        MyCacheUtils.put(key,methods,CACHE_TIME);
        return methods;
    }

    /**
     * 获取对象的全部方法(不包括父类方法)
     * @param cls 类
     * @return
     */
    public static List<Method> getObjectMethod(Class cls)
    {
        String key = String.format(METHOD_KEY,cls.getName());
        if(MyCacheUtils.exist(key))
        {
            return MyCacheUtils.get(key,List.class);
        }
        List<Method> methods = MyCollectionUtils.arrayToList(cls.getDeclaredMethods());
        methods.addAll(MyCollectionUtils.arrayToList(cls.getMethods())) ;
        MyCacheUtils.put(key,methods,CACHE_TIME);
        return methods;
    }

    /**
     * 获取一个对象的全部类
     * @param cls 获取全部类
     * @return 返回全部类
     */
    public static List<Class> getAllClass(Class cls)
    {
        List<Class> classes = new ArrayList<>();
        while (cls.getSuperclass() != null)
        {
            classes.add(cls);
            cls = cls.getSuperclass();
        }
        classes.add(cls);
        return classes;
    }

    /**
     * 根据字段名设置对象的属性(如果存在的话)
     * @param obj 对象
     * @param filedName 字段名
     * @param val 值
     * @return 设置成功则true,否则false
     */
    public static boolean setObjectProperty(Object obj,String filedName,Object val)
    {
        List<Integer> list = new ArrayList<>();
        List<Field> fields = getObjectAllField(obj.getClass());
        fields.forEach(f->{
            if(f.getName().equalsIgnoreCase(filedName))
            {
                f.setAccessible(true);
                try {
                    f.set(obj,val);
                    list.add(1);
                } catch (IllegalAccessException e) {
                    return;
                }
            }
        });
        return MyCollectionUtils.isNotEmpty(list);
    }

    /**
     * @param cls 类
     * @param name 需要查找的字段名
     * @param ignoreCase 是否忽略大小写
     * @return 如果有则返回字段,没有则空
     */
    public static Field getFieldByName(Class cls,String name,boolean ignoreCase,boolean ignoreUnderscore)
    {
        MyUtils.notNull(name,"被查找的字段名不能为null");
        List<Field> fields = getObjectAllField(cls);
        if(MyCollectionUtils.isEmpty(fields))
        {
            return null;
        }
        return fields.stream().filter(f->{
            if(ignoreCase)
            {
                if(ignoreUnderscore)
                {
                    return f.getName().replaceAll("_","").equalsIgnoreCase(name.replaceAll("_",""));
                }
                return f.getName().equalsIgnoreCase(name);
            }else{
                if(ignoreUnderscore)
                {
                    return f.getName().replaceAll("_","").equals(name.replaceAll("_",""));
                }
                return f.getName().equals(name);
            }
        }).findAny().orElseGet(null);
    }

    /**
     * @param cls 类
     * @param name 需要查找的字段名
     * @return 如果有则返回字段,没有则空
     */
    public static Field getFieldByName(Class cls,String name)
    {
        return getFieldByName(cls,name,true,false);
    }
}