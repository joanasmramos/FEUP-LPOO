package com.hatchrun.game.Utilities;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;


/**
 * Facebook API
 */
public class Facebook {

    private String appId = "220092408774995";
    private String appSecret = "a685919daaf573dd60a0ea60d879d892";
    private String redirectUri = "https://www.facebook.com/connect/login_success.html";
    private String redirectUriFb = "https://www.facebook.com";
    public FacebookClient facebookClient = null;
    private ScopeBuilder scopeBuilder;


    /**
     * Constructor for class
     */
    public Facebook() {
        scopeBuilder = new ScopeBuilder();
        facebookClient = new DefaultFacebookClient("EAACEdEose0cBAAscV5XhXrc28UawgvWniQiT3bDlXgHtHVBEZCMxlCd7KwOKGkA3ZB1GDBJb1JetIVxloWw2BpFZAceFcLaqroPITA2Epu4fUFY946XeAoWw9SSZB3Vnjz8PDVOSzaZClBaItagyUbGCLfcaYNYc8qZCLCjjhqpZC4dXeVs1sAWRvNz4WDFlMZCOfdZCfTtu45AZDZD",Version.VERSION_2_9);
    }

    /**
     * Funntion that allows you to log in to facebook
     */
    public void login() {
        String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, redirectUri, scopeBuilder);
       Gdx.net.openURI(loginDialogUrl);
       Gdx.net.openURI(redirectUriFb);
    }
}

