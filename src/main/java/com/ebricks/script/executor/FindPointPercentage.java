package com.ebricks.script.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindPointPercentage {

    private static final Logger LOGGER = LogManager.getLogger(FindPointPercentage.class.getName());
    private float percentagePointX;
    private float percentagePointY;
    private static FindPointPercentage instance;

    private FindPointPercentage(){}

    public static FindPointPercentage getInstance(){

        if (instance == null){
            instance = new FindPointPercentage();
        }
        return instance;
    }

    public void findPointsPercentage(int x,int y,String bounds){

        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");
        float boundX = Integer.parseInt(splitted[1]);
        float boundY = Integer.parseInt(splitted[2]);
        float boundWidth = Integer.parseInt(splitted[4]);
        float boundHeight = Integer.parseInt(splitted[5]);
        LOGGER.info(boundX);
        LOGGER.info(boundY);

        this.percentagePointX =  ((x-boundX)/(boundWidth-boundX))*100;
        this.percentagePointY =  ((y-boundY)/(boundHeight-boundY))*100;
    }

    public int getPointX(String bounds){

        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");
        float boundX = Integer.parseInt(splitted[1]);
        float boundWidth = Integer.parseInt(splitted[4]);
        float pointX = (boundWidth-boundX)*(this.percentagePointX/100);
        return (int) (pointX+boundX);
    }

    public int getPointY(String bounds){

        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");
        float boundY = Integer.parseInt(splitted[2]);
        float boundHeight = Integer.parseInt(splitted[5]);
        float pointY = (boundHeight-boundY)*(this.percentagePointY/100);
        return (int) (pointY+boundY);
    }
}
