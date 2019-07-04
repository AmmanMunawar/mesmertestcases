package com.ebricks.script.validator;

import com.ebricks.script.assertion.AssertionUDA;

public abstract class ElementValidator {

    protected AssertionUDA uda;

    public ElementValidator(AssertionUDA uda) {

        this.uda = uda;
    }

    public abstract AssertionUDA validate(String domName);


}