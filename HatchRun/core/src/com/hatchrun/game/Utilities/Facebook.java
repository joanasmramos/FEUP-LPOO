package com.hatchrun.game.Utilities;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;

public class Facebook {


    private String appId = "220092408774995";
    private String appSecret = "a685919daaf573dd60a0ea60d879d892";
    private String redirectUri = "https://www.facebook.com/connect/login_success.html";

    private FacebookClient facebookClient;
    private ScopeBuilder scopeBuilder;

    public Facebook() {
        scopeBuilder = new ScopeBuilder();
        facebookClient = new DefaultFacebookClient(Version.VERSION_2_9);
    }

    public void login() {
        String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, redirectUri, scopeBuilder);
        Gdx.net.openURI(loginDialogUrl);
    }
}

