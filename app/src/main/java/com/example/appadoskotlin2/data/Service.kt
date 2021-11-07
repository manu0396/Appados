package com.example.appadoskotlin2.data

import java.io.Serializable
import kotlinx.serialization.*

@kotlinx.serialization.Serializable
data class Service(
    val status: String?,
    val type: String,
) : Serializable
