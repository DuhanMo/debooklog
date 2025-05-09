package org.debooklog.adapter.client.book.kakao.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class KakaoBookInformationResponse(
    val meta: Meta,
    val documents: List<KakaoBookDocument>,
)

@JsonNaming(SnakeCaseStrategy::class)
data class Meta(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int,
)

@JsonNaming(SnakeCaseStrategy::class)
data class KakaoBookDocument(
    val title: String?,
    val contents: String?,
    val url: String?,
    val isbn: String?,
    val datetime: String?,
    val authors: List<String>?,
    val publisher: String?,
    val translator: List<String>?,
    val price: Int?,
    val salePrice: Int?,
    val thumbnail: String?,
    val status: String?,
)
