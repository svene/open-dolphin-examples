package org.opendolphin.odwebjee;

import javax.ejb.Stateless;

@Stateless
public class SomeEjb {

    public String makeItLonger(String inputString) {
        return inputString + " longer";
    }
}
