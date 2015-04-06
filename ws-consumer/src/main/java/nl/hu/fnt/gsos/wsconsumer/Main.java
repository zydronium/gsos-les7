/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hu.fnt.gsos.wsconsumer;

import java.math.BigInteger;
import nl.hu.fnt.gsos.wsinterface.PowerRequest;
import nl.hu.fnt.gsos.wsinterface.PowerResponse;
import nl.hu.fnt.gsos.wsinterface.PowerService;
import nl.hu.fnt.gsos.wsinterface.PowerSoap;

/**
 *
 * @author Jelle
 */
public class Main {
    public static void main(String[] args) {
        PowerService powerService = new PowerService();
        // Dit is de SEI
        PowerSoap wsInterface = powerService.getPowerServiceSoap();
        
        PowerRequest request = new PowerRequest(); 
        request.setX(new BigInteger("2")); 
        request.setPower(new BigInteger("2"));
        try {
            PowerResponse response = wsInterface.calculatePower(request);
            System.out.println(response.getActualResult().toString());
        }catch(Exception e) {
            
        }

    }
}
