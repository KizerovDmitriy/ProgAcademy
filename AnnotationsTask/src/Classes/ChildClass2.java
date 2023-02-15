package Classes;

import Annotations.MethodAnnotation;
import Annotations.ParameterAnnotation;

import java.util.List;

public class ChildClass2 extends ChildClass1 {

    @MethodAnnotation
    private void doJob(String str, int value) {
    }

    private long calculate(@ParameterAnnotation int... values) {
        return 0;
    }

    private void changeArray(@ParameterAnnotation List<?> list) {
    }

    private void setObjNewValue(@ParameterAnnotation Object obj) {
    }
}