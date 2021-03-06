package com.example.couponMobileApp

import com.example.couponMobileApp.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


//import  retrofit2.Retrofit

@JvmSuppressWildcards
interface UserApi {
//@FormUrlEncoded
//@POST("register")
//fun createUser(
//        @Field("name") name:String,
//        @Field("email") email:String,
//        @Field("password") password:String,
//        @Field("phone") phone:String,
//        @Field("pin") pin:Int,
//):Call<com.example.stocardapp.models.Response>

        @Multipart
        @POST("{url}")
        fun createUser(
                @Header("Authorization") AUTH: String,
                @Part file: MultipartBody.Part?,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<com.example.couponMobileApp.models.Response>

        @Multipart
        @POST("{url}")
        fun loginUser(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<com.example.couponMobileApp.models.LoginResponse>

        @Multipart
        @POST("{url}")
        fun addStore(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @Part file: MultipartBody.Part?,
                @PartMap map: Map<String, RequestBody>
        ): Call<com.example.couponMobileApp.models.StoreResponse>

        @Multipart
        @POST("{url}")
        fun editProfile(
                @Header("Authorization") AUTH: String,
                @Part file: MultipartBody.Part?,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<com.example.couponMobileApp.models.UpdateResponse>

        @Multipart
        @POST("{url}")
        fun changePas(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<com.example.couponMobileApp.models.ChangePasswordResponse>

        @Multipart
        @POST("{url}")
        fun storeDetails(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<com.example.couponMobileApp.models.StoreDetailResponse>

        @Multipart
        @POST("{url}")
        fun addCard(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @Part file: MultipartBody.Part?,
                @PartMap map: Map<String, RequestBody>
        ): Call<com.example.couponMobileApp.models.CardResponse>

        @Multipart
        @POST("{url}")
        fun cardList(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<com.example.couponMobileApp.models.CardDetailResponse>

        @Multipart
        @POST("{url}")
        fun forgotPs(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<ForgotPsResponse>

        @Multipart
        @POST("{url}")
        fun delCard(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>

        ): Call<DeleteResponse>

        @Multipart
        @POST("{url}")
        fun delStore(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String,RequestBody>

        ): Call<DeleteResponse>

        @Multipart
        @POST("{url}")
        fun codeGen(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String,RequestBody>

        ): Call<ShareResponse>

        @Multipart
        @POST("{url}")
        fun shareCard(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String,RequestBody>
        ): Call<ShareCardResponse>

        @Multipart
        @POST("{url}")
        fun hideCard(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String,RequestBody>
        ): Call<HideCardResponse>

        @Multipart
        @POST("{url}")
        fun showCard(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String,RequestBody>
        ): Call<ShowCardResponse>

        @Multipart
        @POST("{url}")
        fun storeSuggestion(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<StoreSuggestionResponse>

        @Multipart
        @POST("{url}")
        fun addFav(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<FavoriteResponse>

        @Multipart
        @POST("{url}")
        fun removeFav(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<FavoriteResponse>

        @Multipart
        @POST("{url}")
        fun getCategory(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<FilterResponse>

        @Multipart
        @POST("{url}")
        fun storeUpdate(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<StoreUpdateResponse>

        @Multipart
        @POST("{url}")
        fun sociallogin(
                @Header("Authorization") AUTH: String,
                @Path(value = "url", encoded = true) url: String?,
                @PartMap map: Map<String, RequestBody>
        ): Call<LoginResponse>
}

object ApiUtils {
        val BASE_URL = "http://stocard.project-demo.info/api/"
        val apiService: UserApi
                get() = RetrofitClientD.getClient(BASE_URL)!!.create(UserApi::class.java)

}