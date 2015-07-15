/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thenairn.pubsub.annotation.util;

import java.lang.annotation.Annotation;
import java.util.Map.Entry;
import java.util.Set;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

/**
 *
 * @author thomas
 */
public class AnnotationUtils {

    public static AnnotationMirror getAnnotationMirror(TypeElement typeElement, Class className) {
        for (AnnotationMirror m : typeElement.getAnnotationMirrors()) {
            System.out.println(m.getAnnotationType().toString()+" "+className.getName());
            if (m.getAnnotationType().toString().equals(className.getName())) {
                return m;
            }
        }
        return null;
    }

    public static AnnotationMirror getAnnotationMirror(TypeElement typeElement, String className) {
        for (AnnotationMirror m : typeElement.getAnnotationMirrors()) {
            if (m.getAnnotationType().toString().equals(className)) {
                return m;
            }
        }
        return null;
    }

    public static AnnotationValue getAnnotationValue(AnnotationMirror annotationMirror, String key) {
        for (Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues().entrySet()) {
            if (entry.getKey().getSimpleName().toString().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static AnnotationValue getAnnotationValue(AnnotationMirror annotationMirror) {
        return getAnnotationValue(annotationMirror, "value");
    }

    public static TypeElement getAnnotationValueAsType(Types types, AnnotationMirror annotationMirror) {
        return getAnnotationValueAsType(types, annotationMirror, "value");
    }

    public static TypeElement getAnnotationValueAsType(Types types, AnnotationMirror annotationMirror, String key) {
        AnnotationValue annotationValue = getAnnotationValue(annotationMirror, key);
        if (annotationValue == null) {
            return null;
        }
        TypeMirror typeMirror = (TypeMirror) annotationValue.getValue();
        if (typeMirror == null) {
            return null;
        }
        return (TypeElement) types.asElement(typeMirror);
    }
}
