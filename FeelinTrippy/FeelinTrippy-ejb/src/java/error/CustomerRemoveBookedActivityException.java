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
public class CustomerRemoveBookedActivityException extends Exception {
    public CustomerRemoveBookedActivityException() {
    }

    public CustomerRemoveBookedActivityException(String s) {
        super(s);
    }
}
