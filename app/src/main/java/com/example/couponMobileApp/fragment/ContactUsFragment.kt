package com.example.couponMobileApp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import com.example.couponMobileApp.ApiUtils
import com.example.couponMobileApp.R
import com.example.couponMobileApp.UserApi
import com.example.couponMobileApp.activity.HomeActivity
import com.example.couponMobileApp.models.ChangePasswordResponse
import com.example.couponMobileApp.utils.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback


class ContactUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        (context as AppCompatActivity).supportActionBar!!.title = "Contact Us"

        //For hide keyboard on touch outside
        val touch = view?.findViewById<LinearLayout>(R.id.keyboard)
        touch?.setOnClickListener {
            Utils.hideKeyboard(requireActivity())
        }

        val btnCn = requireView().findViewById<Button>(R.id.btn_cnt)
        val tnm = requireView().findViewById<EditText>(R.id.txtCNm)
        val tem = requireView().findViewById<EditText>(R.id.txtCem)
        val tsb = requireView().findViewById<EditText>(R.id.txtSub)
        val tmg = requireView().findViewById<EditText>(R.id.txtMsg)


        var mAPIService: UserApi? = null
        mAPIService = ApiUtils.apiService

        btnCn.setOnClickListener {
            val nm = tnm.text.toString()
            val em = tem.text.toString()
            val sb = tsb.text.toString()
            val mg = tmg.text.toString()

            //for hide keyboard on button click
            Utils.hideKeyboard(requireActivity())

            val SHARED_PREF_NAME = "my_shared_preff"
            val sharedPreference =
                this.activity?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val token = "Bearer " + (sharedPreference?.getString("token", "defaultName"))
            val map: MutableMap<String, RequestBody> = HashMap()
            map["name"] = toPart(nm) as RequestBody
            map["email"] = toPart(em)
            map["subject"] = toPart(sb)
            map["description"] = toPart(mg)

            mAPIService.changePas(token!!, "ContactUsFragment", map).enqueue(object :
                Callback<ChangePasswordResponse> {
                override fun onResponse(
                    call: Call<ChangePasswordResponse>,
                    response: retrofit2.Response<ChangePasswordResponse>,
                ) {
                    if (response.body()?.success == true) {
                        Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                        val intent =
                            Intent(this@ContactUsFragment.context, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startActivity(Intent(context, HomeActivity::class.java))

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    fun toPart(data: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), data)
    }
}