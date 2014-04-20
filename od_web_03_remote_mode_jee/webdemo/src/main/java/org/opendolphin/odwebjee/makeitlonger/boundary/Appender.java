package org.opendolphin.odwebjee.makeitlonger.boundary;

import javax.ejb.Stateless;

@Stateless
public class Appender {

    public String makeItLonger(String inputString) {
        return inputString + " longer";
    }
}
