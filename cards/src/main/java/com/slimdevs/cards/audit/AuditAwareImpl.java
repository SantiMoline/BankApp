package com.slimdevs.cards.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS"); 
        //Con este valor va llenar las columnas de createdBy y UpdatedBy automáticamente.
        //Una vez que tengamos integrada la seguridad de Spring Security, vamos a poder obtener los usuario y desde qué aplicación viene la solicitud.
    }
    
}
