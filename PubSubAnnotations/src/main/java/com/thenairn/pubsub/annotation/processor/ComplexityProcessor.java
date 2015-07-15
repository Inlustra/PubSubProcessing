/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thenairn.pubsub.annotation.processor;

import com.thenairn.pubsub.annotation.Publisher;
import com.thenairn.pubsub.annotation.util.AnnotationUtils;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 *
 * @author thomas
 */
@SupportedAnnotationTypes("com.thenairn.pubsub.annotation.Publisher")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ComplexityProcessor extends AbstractProcessor {

    public ComplexityProcessor() {
        super();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
            RoundEnvironment env) {
        Messager messager = processingEnv.getMessager();

        for (Element element : env.getElementsAnnotatedWith(Publisher.class)) {
            try {
                Publisher p = element.getAnnotation(Publisher.class);
                System.out.println("Starting: " + element.getSimpleName());
                AnnotationMirror mirror = AnnotationUtils.getAnnotationMirror((TypeElement) element, Publisher.class);
                AnnotationValue value = AnnotationUtils.getAnnotationValue(mirror);
                Class<? extends Enum> clazz = (Class<? extends Enum>) Class.forName(value.getValue().toString());
                
                for(Enum e : clazz.getEnumConstants()) {
                    System.out.println(e.name());
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ComplexityProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

}
