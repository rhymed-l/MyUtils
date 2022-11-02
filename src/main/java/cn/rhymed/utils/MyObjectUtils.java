package cn.rhymed.utils;


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
    private static final String FIELD_KEY = "field:%s";
    private static final String FIELD_ALL_KEY = "field_all:%s";
    private static final String METHOD_KEY = "method:%s";
    private static final String METHOD_ALL_KEY = "method_all:%s";

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
        List<Field> sourceFields = MyObjectUtils.getObjectAllField(source);
        List<Field> targetFields = MyObjectUtils.getObjectAllField(clazz);

        for(Field sourceField : sourceFields)
        {
            for(Field targetField : targetFields)
            {
                if(ignoreUnderscore ? sourceField.getName().replaceAll("_","").equalsIgnoreCase(targetField.getName().replaceAll("_",""))
                        : sourceField.getName().equalsIgnoreCase(targetField.getName())
                        && fieldCompare(sourceField.getType(),targetField.getType()))
                {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    try {
                        if(sourceField.get(obj) != null){
                            targetField.set(to,sourceField.get(obj));
                        }
                    } catch (Exception e) {
                        //不需要做任何处理
                    }
                }
            }
        }
        return to;
    }

    /**
     * copyNotNull 将源对象里面不为Null的对象拷贝到目标对象里面
     * @param sourceObj 源对象
     * @param targetObj 目标对象
     */
    public static <T> void copyNotEmpty(T sourceObj, T targetObj) {
        copyNotEmpty(sourceObj,targetObj,true);
        return;
    }

    /**
     * copyNotNull 将源对象里面不为Null的对象拷贝到目标对象里面
     * @param sourceObj 源对象
     * @param targetObj 目标对象
     * @param allCopy 替换拷贝
     */
    public static <T> void copyNotEmpty(T sourceObj, T targetObj,boolean allCopy) {
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
                    {
                        targetField.setAccessible(true);
                        //如果是全量拷贝则源数据没有值则拷贝,否则目标数据为空则拷贝
                        if((allCopy && MyObjectUtils.objIsNotEmpty(sourceField.get(targetObj))) ||
                                MyObjectUtils.objIsEmpty(targetField.get(targetObj)))
                        {
                            targetField.set(targetObj,obj);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                //不需要做任何处理
            }
        }
    }

    /**
     * 判断对象是否不为空
     * @param obj 需要判断的对象
     * @return 如果是空则返回真,否则取反
     */
    public static boolean objIsNotEmpty(Object obj){
        return !objIsEmpty(obj);
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
     * @param object 需要判断的对象
     * @return 返回是否包装类型
     */
    public static boolean isBaseType(Object object) {
        return isBaseType(object.getClass());
    }

    /**
     * 判断object是否为基本类型
     * @param cls 类
     * @return 返回是否包装类型
     */
    public static boolean isBaseType(Class cls) {
        if (cls.equals(java.lang.Integer.class) || cls.getTypeName().equals("int") ||
                cls.equals(java.lang.Byte.class) || cls.getTypeName().equals("byte") ||
                cls.equals(java.lang.Long.class) || cls.getTypeName().equals("long") ||
                cls.equals(java.lang.Double.class) || cls.getTypeName().equals("double") ||
                cls.equals(java.lang.Float.class) || cls.getTypeName().equals("float") ||
                cls.equals(java.lang.Character.class) || cls.getTypeName().equals("char") ||
                cls.equals(java.lang.Short.class) || cls.getTypeName().equals("long") ||
                cls.equals(java.lang.Boolean.class) || cls.getTypeName().equals("boolean")) {
            return true;
        }
        return false;
    }

    /**
     * 判断两个字段属性是否相等
     */
    public static boolean fieldCompare(Object obj1,Object obj2) {
        return getWrapType(obj1).equals(getWrapType(obj2));
    }

    /**
     * 获取对象包装类型
     * @param obj 需要获取的对象
     * @return 如果是基本类型则返回包装类型,否则返回本身
     */
    public static Class getWrapType(Object obj){
       return getWrapType(obj.getClass());
    }

    /**
     * 获取类的包装类型
     * @param cls 需要获取的对象
     * @return 如果是基本类型则返回包装类型,否则返回本身
     */
    public static Class getWrapType(Class cls){
        if(cls == null){
            return null;
        }
        if(isBaseType(cls)){
            if (cls.equals(java.lang.Integer.class) || cls.getTypeName().equals("int"))
                return Integer.class;
            if (cls.equals(java.lang.Byte.class) || cls.getTypeName().equals("byte"))
                return Byte.class;
            if (cls.equals(java.lang.Long.class) || cls.getTypeName().equals("long"))
                return Long.class;
            if (cls.equals(java.lang.Double.class) || cls.getTypeName().equals("double"))
                return Double.class;
            if (cls.equals(java.lang.Float.class) || cls.getTypeName().equals("float"))
                return Float.class;
            if (cls.equals(java.lang.Character.class) || cls.getTypeName().equals("char"))
                return Character.class;
            if (cls.equals(java.lang.Short.class) || cls.getTypeName().equals("short"))
                return Short.class;
            if (cls.equals(java.lang.Boolean.class) || cls.getTypeName().equals("boolean"))
                return Boolean.class;
        }
        return cls;
    }




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
        classes.forEach(c->fields.addAll(getObjectField(c)));
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
        List<Field> fields = MyCollUtils.arrayToList(cls.getDeclaredFields());
        fields.addAll(MyCollUtils.arrayToList(cls.getFields()));
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
        List<Method> methods = MyCollUtils.arrayToList(cls.getDeclaredMethods());
        methods.addAll(MyCollUtils.arrayToList(cls.getMethods())) ;
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
        return MyCollUtils.isNotEmpty(list);
    }

    /**
     * 强制获取对象的字段(包括父类对象)
     * @param obj 需要获取的对象
     * @param filedName 需要获取对象的字段名
     * @param cls 回去对象的返回类
     * @return 返回对象的字段数据
     */
    public static <T> T getObjectProperty(Object obj,String filedName,Class<T> cls)
    {
        List<T> list = new ArrayList<>();
        List<Field> fields = getObjectAllField(obj.getClass());
        fields.stream().filter(field -> field.getName().equalsIgnoreCase(filedName)).
                findAny().ifPresent(field -> {
            field.setAccessible(true);
            try {
                list.add((T) field.get(obj));
            } catch (IllegalAccessException e) {
                //不需要做任何处理
            }
        });
        if(MyCollUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查找指定类的字段名
     * @param cls 类
     * @param name 需要查找的字段名
     * @param ignoreCase 是否忽略大小写
     * @return 如果有则返回字段,没有则空
     */
    public static Field getFieldByName(Class cls,String name,boolean ignoreCase,boolean ignoreUnderscore)
    {
        MyUtils.notNull(name,"被查找的字段名不能为null");
        List<Field> fields = getObjectAllField(cls);
        if(MyCollUtils.isEmpty(fields))
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
     * 查找指定类的字段名
     * @param cls 类
     * @param name 需要查找的字段名
     * @return 如果有则返回字段,没有则空
     */
    public static Field getFieldByName(Class cls,String name)
    {
        return getFieldByName(cls,name,true,false);
    }

    /**
     * 如果是空则返回指定值
     * @param obj 需要判断的对象
     * @param t 可能需要返回的对象
     * @return 根据判断返回值
     */
    public static <T> T objIfNullThen(T obj,T t){
        return (obj == null) ? t : obj;
    }
}