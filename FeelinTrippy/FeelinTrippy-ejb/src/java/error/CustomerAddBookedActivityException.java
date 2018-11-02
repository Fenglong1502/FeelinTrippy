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
public class CustomerAddBookedActivityException extends Exception{
      public CustomerAddBookedActivityException() {
    }

    public CustomerAddBookedActivityException(String s) {
        super(s);
    }
}
