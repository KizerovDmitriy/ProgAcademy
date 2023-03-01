package mainprogram;

import classes.FindAnnotations;

public class FindAnnotationsProgram {
    public static void main(String[] args) {
        FindAnnotations findAnnotations = new FindAnnotations();
        findAnnotations.invokeMethodsWithAnnotation("testpackage");
        System.out.println("Inner:");
        findAnnotations.invokeMethodsWithAnnotation("testpackage.inner");
    }
}