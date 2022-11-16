import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;

public class Interceptor implements IClientInterceptor {

    static String time = "0";

    @Override
    public void interceptRequest(IHttpRequest iHttpRequest) {
        //TODO
    }

    @Override
    public void interceptResponse(IHttpResponse iHttpResponse) {
       time = String.valueOf(Integer.parseInt(iHttpResponse.getRequestStopWatch().toString().replace("ms", "")) + Integer.parseInt(time));
    }
}
