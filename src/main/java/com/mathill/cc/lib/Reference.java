package com.mathill.cc.lib;

public class Reference {

    /* Debug Mode On-Off */
    public static final boolean DEBUG_MODE                  = true;

    /* General Mod related constants */
    public static final String  MOD_ID                      = "CConduits";
    public static final String  MOD_NAME                    = "CConduits";
    public static final String  VERSION_NUMBER              = "@VERSION@ (build @BUILD_NUMBER@)";
    public static final String  CHANNEL_NAME                = MOD_ID;
    public static final String  DEPENDENCIES                = "required-after:Forge@[7.7.1.650,)";
    public static final String  FINGERPRINT                 = "@FINGERPRINT@";
    public static final int     SECOND_IN_TICKS             = 20;
    public static final int     SHIFTED_ID_RANGE_CORRECTION = 256;
    public static final int     VERSION_CHECK_ATTEMPTS      = 3;
    public static final String  SERVER_PROXY_CLASS          = "com.mathill.cc.proxy.CommonProxy";
    public static final String  CLIENT_PROXY_CLASS          = "com.mathill.cc.proxy.ClientProxy";
    public static final String  PIPES_PROXY_CLIENT          = "com.mathill.cc.proxy.pipes.PipesProxyClient";
    public static final String  PIPES_PROXY_SERVER          = "com.mathill.cc.proxy.pipes.PipesProxy";

}
