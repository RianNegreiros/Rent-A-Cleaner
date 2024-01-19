package com.github.riannegreiros.ExpressCleaning.core.permissions;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface RentACleanerPermissions {
    @PreAuthorize("hasAnyAuthority('CLIENT', 'CLEANER')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isCleanerOrClient {}

    @PreAuthorize("hasAuthority('CLIENT')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isClient {}

    @PreAuthorize("hasAuthority('CLEANER')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isCleaner {}

    @PreAuthorize("hasAuthority('CLIENT') and @securityUtils.isClientFromDaily(#id)")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isClientFromDaily {}

    @PreAuthorize("hasAnyAuthority('CLIENT', 'CLEANER') and @securityUtils.isClientOrCleanerFromDaily(#id)")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isClientOrCleanerFromDaily {}
}
