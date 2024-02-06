package com.slimdevs.loans.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current app's auditor.
     * 
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        
        return Optional.of("User_Santi_Loan");
    }
    
}
