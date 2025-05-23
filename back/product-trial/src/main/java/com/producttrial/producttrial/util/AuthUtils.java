package com.producttrial.producttrial.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils
{
    public static String getCurrentUserEmail()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
