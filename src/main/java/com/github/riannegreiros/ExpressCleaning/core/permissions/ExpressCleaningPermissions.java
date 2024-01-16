package com.github.riannegreiros.ExpressCleaning.core.permissions;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface ExpressCleaningPermissions {
    @PreAuthorize("hasAnyAuthority('CLIENT', 'HOUSEKEEPER')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isHousekeeperOrClient {}

    @PreAuthorize("hasAuthority('CLIENT')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isClient {}

    @PreAuthorize("hasAuthority('HOUSEKEEPER')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isHousekeeper {}

    @PreAuthorize("hasAuthority('CLIENT') and @securityUtils.isClientFromDailyRate(#id)")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isClientFromDailyRate {}

    @PreAuthorize("hasAnyAuthority('CLIENT', 'HOUSEKEEPER') and @securityUtils.isClientOrHousekeeperFromDaily(#id)")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isClientOrHousekeeperFromDaily {}
}
