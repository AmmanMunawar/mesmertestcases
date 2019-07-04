package com.ebricks.script.validator;

import com.ebricks.script.assertion.AssertionUDA;

public class ElementValidatorFactory {

    private static ElementValidatorFactory instance;

    private ElementValidatorFactory(){};

    public static ElementValidatorFactory getInstance(){

        if ( instance == null){

            instance = new ElementValidatorFactory();
        }
        return instance;
    }

    public ElementValidator getElementValidator(AssertionUDA uda){

        switch (uda.getType()) {
            case "EQUAL":

                return new EqualTextValidator(uda);
            case "EXIST":

                return new XpathValidator(uda);
            case "CREATE_VAR":

                return new CreateVarValidator(uda);
            case "CONTAIN_VAR":

                return new ContainVarValidator(uda);
        }
        return null;
    }
}
