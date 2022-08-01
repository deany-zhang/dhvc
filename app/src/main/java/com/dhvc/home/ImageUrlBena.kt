package com.dhvc.home

data class ImageUrlBena(
    val message: List<Message>,
    val meta: Meta
)

data class Message(
    val goods_id: Int,
    val image_src: String,
    val navigator_url: String,
    val open_type: String

) {
    override fun toString(): String {
        return "Message(goods_id=$goods_id, image_src='$image_src', navigator_url='$navigator_url', open_type='$open_type')"
    }
}

data class Meta(
    val msg: String,
    val status: Int
)
