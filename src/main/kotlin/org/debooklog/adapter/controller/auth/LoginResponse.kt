package org.debooklog.adapter.controller.auth

import org.debooklog.core.auth.model.TokenData

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    constructor(tokenData: TokenData) : this(
        accessToken = tokenData.accessToken,
        refreshToken = tokenData.refreshToken,
    )
}
