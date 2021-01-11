//package cn.rhyme.utils;
//
//
//import java.util.NoSuchElementException;
//import java.util.function.Consumer;
//import java.util.function.Supplier;
//
///**
// * @author LiuZhi
// * @Date 2020-12-25 18:01
// * @Version V1.0
// */
//public class BooleanUtils {
//
//    private static final BooleanUtils EMPTY = new BooleanUtils();
//
//    private final Boolean value;
//
//    public BooleanUtils() {
//        this.value = false;
//    }
//
//    public BooleanUtils(boolean value) {
//        this.value = value;
//    }
//
//    public static BooleanUtils of(Boolean value) {
//        return new BooleanUtils(value);
//    }
//
//    public boolean get() {
//        if (value == null) {
//            throw new NoSuchElementException("No value present");
//        }
//        return value;
//    }
//
//    public boolean isPresent() {
//        return value != null;
//    }
//
//    public void ifPresent(Consumer<Boolean> consumer) {
//        if (value != null){
//            consumer.accept(value);
//        }
//    }
//
//    public BooleanUtils orTrueGet(Supplier<BooleanUtils> other) {
//        return value != null ? value : other.get();
//    }
//
//    public BooleanUtils orElseGet(Supplier<BooleanUtils> other) {
//        return value != null ? value : other.get();
//    }
//
//    public <X extends Throwable> boolean orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
//        if (value != null) {
//            return value;
//        } else {
//            throw exceptionSupplier.get();
//        }
//    }
//}
