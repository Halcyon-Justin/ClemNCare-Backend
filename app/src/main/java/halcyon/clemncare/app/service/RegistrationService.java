package halcyon.clemncare.app.service;

import halcyon.clemncare.app.dto.RegistrationRequest;
import halcyon.clemncare.app.model.Family;

public interface RegistrationService {
    public Family registerNewFamily(RegistrationRequest request);
}



