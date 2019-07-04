package com.ebricks.script.validator;

import com.ebricks.script.Main;
import com.ebricks.script.assertion.AssertionUDA;
import com.ebricks.script.executor.FindElement;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EqualTextValidator extends ElementValidator {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public EqualTextValidator(AssertionUDA uda) {

        super(uda);
    }

    public AssertionUDA validate(String domName) {


        AssertionUDA uda = new AssertionUDA();
        try {

            UIElement udadomElment = FindElement.getInstance().findElementByudaId(this.uda.getUdaId(), domName);
            System.out.println(udadomElment.getText());
            System.out.println(udadomElment.getType());

            UIElement udaPageSourceElment = FindElement.getInstance().findUIElement(AppiumService.getInstance().getPageSourse(),udadomElment,0,0,true);
            System.out.println(udaPageSourceElment);

            //setting uda properties
            uda.setNode(this.uda.getNode());
            uda.set_id(this.uda.get_id());
            uda.setType(this.uda.getType());
            uda.setElementType(this.uda.getElementType());
            uda.setUdaId(this.uda.getUdaId());
            uda.setName(this.uda.getName());
            uda.setResult(this.uda.getResult());

            if (this.uda.getValue().equals(udaPageSourceElment.getText())) {

                uda.getResult().setSuccess(true);
                uda.getResult().setStatus("SUCCESS");
                uda.getResult().setValue("");
            } else {

                uda.getResult().setSuccess(false);
                uda.getResult().setStatus("Failed");
                uda.getResult().setValue("This should equals to " + this.uda.getValue());
            }

            uda.getResult().setBase(this.uda.getResult().getBase());
            uda.getResult().setResult(this.uda.getResult().getResult());
            //Result Element Properties

            uda.getResult().getResult().setValue(udaPageSourceElment.getText());
            uda.getResult().getResult().setDisplay(true);
            uda.getResult().getResult().setPosition(FindElement.getInstance().findElementPosition(udaPageSourceElment.getBounds()));
            uda.getResult().getResult().setSize(FindElement.getInstance().findElementSize(udaPageSourceElment.getBounds()));
            //Base Element Properties

            uda.getResult().getBase().setValue(this.uda.getValue());
            uda.getResult().getBase().setDisplay(true);
            uda.getResult().getBase().setPosition(FindElement.getInstance().findElementPosition(udadomElment.getBounds()));
            uda.getResult().getBase().setSize(FindElement.getInstance().findElementSize(udadomElment.getBounds()));
            return uda;
        } catch (IOException e) {
            LOGGER.error("Text Validator Exception",e);
        }
        return null;
    }

}