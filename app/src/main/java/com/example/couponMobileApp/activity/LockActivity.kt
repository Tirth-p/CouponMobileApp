package com.example.couponMobileApp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatDelegate
import com.example.couponMobileApp.ApiUtils
import com.example.couponMobileApp.R
import com.example.couponMobileApp.UserApi
import com.example.couponMobileApp.models.ShowCardResponse
import com.hanks.passcodeview.PasscodeView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import java.util.HashMap

class LockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.header_black_2)
        val txtTit = findViewById<TextView>(R.id.txtTitle)
        txtTit.setText("Lock Screen")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        var mAPIService: UserApi? = null
        mAPIService = ApiUtils.apiService
        var cid = intent.getIntExtra("cardId",0)
        var st = intent.getStringExtra("status")
        var ct = 0

        var cnm = intent.getStringExtra("cardNm")
        var cnum = intent.getStringExtra("cardNum")
        var crwd = intent.getStringExtra("cardRwd")
        var cdtl = intent.getStringExtra("cardDetl")
        var cdt = intent.getStringExtra("cardDt")
        var im = intent.getStringExtra("cardImg")

        Log.d("imgggggg",im.toString())
        var  passcode_view = findViewById<PasscodeView>(R.id.passcode_view)
        val SHARED_PREF_NAME = "my_shared_preff"
        val sharedPreference = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val token = "Bearer " + sharedPreference.getString("token", "defaultName")
        val pin = sharedPreference.getString("pin","defaultvalue")

       // Toast.makeText(this,pin, Toast.LENGTH_LONG).show()
        passcode_view.setPasscodeLength(4) // Set Password Length
            .setLocalPasscode(pin)
            .setListener(object: PasscodeView.PasscodeViewListener{

                override fun onFail() {
                    Toast.makeText(this@LockActivity,"Password is Wrong", Toast.LENGTH_LONG).show()
                    Log.d("wwwww","hhhh")
                    Toast.makeText(this@LockActivity, ct.toString(), Toast.LENGTH_LONG).show()
                    passcode_view.localPasscode = ""
                }

                override fun onSuccess(number: String?) {
                    //If password is Correct
                    val map: MutableMap<String, RequestBody> = HashMap()
                    map["card_id"] = toPart(cid.toString()) as RequestBody

                    mAPIService.showCard(token!!, "ShowCard", map).enqueue(object :
                        Callback<ShowCardResponse> {
                        override fun onResponse(
                            call: Call<ShowCardResponse>,
                            response: retrofit2.Response<ShowCardResponse>
                        ) {

                            if(response.body()?.success == true) {
                                Toast.makeText(this@LockActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                                val i: Intent
                                i = Intent(this@LockActivity, CardDetailActivity::class.java)
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                i.putExtra("cardNm", cnm)
                                i.putExtra("cardNum", cnum)
                                i.putExtra("cardRwd", crwd)
                                i.putExtra("cardDetl", cdtl)
                                i.putExtra("cardDt", cdt)
                                i.putExtra("cardImg", im.toString())
                                i.putExtra("status", st)
                                startActivity(i)
                            }
                            else
                            {
                                Toast.makeText(this@LockActivity,"Something went wrong!",Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onFailure(call: Call<ShowCardResponse>, t: Throwable) {
                            Toast.makeText(this@LockActivity, t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                }
            })
    }
    fun toPart(data: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), data)
    }
}