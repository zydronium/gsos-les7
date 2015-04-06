package nl.hu.fnt.gsos.wsproducer;

import java.math.BigInteger;
import javax.jws.WebService;

import nl.hu.fnt.gsos.wsinterface.ApplicatieFout;
import nl.hu.fnt.gsos.wsinterface.ObjectFactory;
import nl.hu.fnt.gsos.wsinterface.PowerFail;
import nl.hu.fnt.gsos.wsinterface.PowerRequest;
import nl.hu.fnt.gsos.wsinterface.PowerResponse;
import nl.hu.fnt.gsos.wsinterface.PowerSoap;

@WebService( endpointInterface= "nl.hu.fnt.gsos.wsinterface.PowerSoap")
public class PowerServiceImpl implements PowerSoap {

	@Override
	public PowerResponse calculatePower(PowerRequest request) throws ApplicatieFout {
		ObjectFactory factory = new ObjectFactory();
		PowerResponse response = factory.createPowerResponse();
		try {
			Double result = Math.pow(request.getX().doubleValue(), request
					.getPower().doubleValue());
			// x en power zijn altijd gehele getallen dan is er geen afronding
			long actualResult = result.longValue();
			response.setActualResult(BigInteger.valueOf(actualResult));
		} catch (RuntimeException e) {
			PowerFail x = factory.createPowerFail();
			x.setCode(BigInteger.valueOf(1));
			x.setMessage("Kan de macht van " + request.getX()
					+ " tot de macht " + request.getPower().toString()
					+ " niet berekenen.");
			ApplicatieFout fault = new ApplicatieFout(
					"Er ging iets mis met het berekenen van de power", x);
			throw fault;
		}
		return response;
	}

}
