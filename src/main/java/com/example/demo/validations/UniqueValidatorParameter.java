package com.example.demo.validations;

import com.example.demo.annotations.UniqueParameter;
import com.example.demo.repositories.ParameterRepository;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class UniqueValidatorParameter implements ConstraintValidator<UniqueParameter, String> {

    // Injectez votre service ou repository pour accéder à la base de données

    private String attributeName;

    private ParameterRepository parameterRepository;

    public UniqueValidatorParameter(ParameterRepository parameterRepository){
        this.parameterRepository = parameterRepository;
    }

    @Override
    public void initialize(UniqueParameter constraintAnnotation) {
        // Logique d'initialisation si nécessaire
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // Récupère le nom de l'attribut sur lequel l'annotation est appliquée
        Field[] fields = context.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(UniqueParameter.class)) {
                attributeName = field.getName();
                break;
            }
        }

        String methodName = "findBy"+attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);

        // Obtention de la classe de l'objet
        Class<?> clazz = parameterRepository.getClass();

        // Obtention des types de paramètres
        Class<?>[] parameterTypes = new Class<?>[] { int.class };


        // Obtention de la méthode à partir du nom et des types de paramètres
        Method method = null;
        try {
            method = clazz.getMethod(methodName, parameterTypes);

            // Arguments à passer à la méthode
            Object[] arguments = new Object[] { 42, "Hello" };

            // Appel de la méthode sur l'objet avec les paramètres
             method.invoke(parameterRepository, arguments);


        } catch (Exception e) {
            System.out.println("Impossible d'Avoir la méthode");
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        if (attributeName == null) {
            return true; // Retourne true si l'annotation @UniqueParameter n'est pas présente sur l'attribut
        }

        // Effectue la logique de validation d'unicité en utilisant attributeName
        // ...

        return true; // Retourne true si la validation réussit
    }
}
