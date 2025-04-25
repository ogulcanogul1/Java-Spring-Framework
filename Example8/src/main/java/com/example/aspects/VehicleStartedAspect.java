package com.example.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect

public class VehicleStartedAspect {
    private Logger logger = Logger.getLogger(VehicleStartedAspect.class.getName());

    @Before("execution(* com.example.services.*.*(..)) && args(vehicleStarted,..)")
    public void checkVehicleStarted(boolean vehicleStarted)
    {

        if(vehicleStarted) {
            logger.info("Vehicle is started");
        } else {
            logger.info("Vehicle is not started");
        }
        // burada  @Before içine string bilgide @args içine verilen değer aspect metot da tipi tanımlanmalıdır. (boolean vehicleStarted) gibi bu sayede ilk değeri boolean olan metotlara uygulanacaktır. 2. veya 3. parametresi olsada olmaz ilk parametresi olması gerekir.
    }
}
