package com.ebricks.script.validator;

import com.ebricks.script.Main;
import com.ebricks.script.assertion.AssertionUDA;
import com.ebricks.script.executor.FindElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XpathValidator extends ElementValidator{

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public XpathValidator(AssertionUDA uda){
        super(uda);
    }

    public AssertionUDA validate(String domName) {

        AssertionUDA uda = new AssertionUDA();

        //setting uda properties
        uda.setNode(this.uda.getNode());
        uda.set_id(this.uda.get_id());
        uda.setType(this.uda.getType());
        uda.setElementType(this.uda.getElementType());
        uda.setUdaId(this.uda.getUdaId());
        uda.setName(this.uda.getName());
        uda.setResult(this.uda.getResult());

        if (FindElement.getInstance().findElementByXpath(this.uda.getValue())) {

            uda.getResult().setSuccess(true);
            uda.getResult().setStatus("SUCCESS");
            uda.getResult().setValue("");
        } else {

            uda.getResult().setSuccess(false);
            uda.getResult().setStatus("Failed");
            uda.getResult().setValue("Xpath should exist '" + this.uda.getValue()+"'");
        }

        uda.getResult().setBase(null);
        uda.getResult().setResult(null);
        return uda;
    }
}
