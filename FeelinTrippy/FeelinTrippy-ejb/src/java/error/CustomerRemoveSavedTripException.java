/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

/**
 *
 * @author fengl
 */
public class CustomerRemoveSavedTripException extends Exception{
    public CustomerRemoveSavedTripException() {
    }

    public CustomerRemoveSavedTripException(String s) {
        super(s);
    }
}
