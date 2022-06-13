package com.dicoding.picodiploma.wotive

import com.google.gson.annotations.SerializedName

data class WorkshopResponse(

	@field:SerializedName("workshop")
	val workshop: Workshop,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class Workshop(

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("merek")
	val merek: String,

	@field:SerializedName("alamat")
	val alamat: String
)
