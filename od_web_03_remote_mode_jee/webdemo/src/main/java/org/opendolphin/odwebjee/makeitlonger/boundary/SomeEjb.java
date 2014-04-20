package org.opendolphin.odwebjee.makeitlonger.boundary;

import javax.ejb.Stateless;

@Stateless
public class SomeEjb {

    public String makeItLonger(String inputString) {
        return inputString + " longer";
    }
}
