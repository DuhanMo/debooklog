package org.debooklog.adapter.client.auth.google

import org.debooklog.core.auth.model.OAuth2UserData
import org.debooklog.core.auth.model.OAuth2UserDataGetterStrategy
import org.debooklog.core.member.model.SocialProvider
import org.debooklog.core.member.model.SocialProvider.GOOGLE
import org.springframework.stereotype.Component

@Component
class GoogleOAuth2UserDataGetter(
    private val properties: GoogleOAuth2Properties,
    private val googleOAuth2Client: GoogleOAuth2Client,
    private val googleClient: GoogleClient,
) : OAuth2UserDataGetterStrategy {
    override val support: SocialProvider
        get() = GOOGLE

    override fun fetch(code: String): OAuth2UserData {
        val googleToken =
            googleOAuth2Client.fetchToken(
                grantType = "authorization_code",
                clientId = properties.clientId,
                clientSecret = properties.clientSecret,
                code = code,
                redirectUri = properties.redirectUri,
            )
        return googleClient.fetchUserInfo("Bearer ${googleToken.accessToken}")
            .toOAuth2UserData()
    }
}
