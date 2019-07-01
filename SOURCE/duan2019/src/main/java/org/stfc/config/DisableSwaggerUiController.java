package org.stfc.config;


import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.utils.Contants;

@Profile(value = {Contants.SPRING_PROFILE_PRODUCTION})
@RestController
public class DisableSwaggerUiController {

    @RequestMapping(value = "/swagger-ui.html", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getSwagger() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
