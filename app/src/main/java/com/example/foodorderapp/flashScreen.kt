package com.example.foodorderapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.AsyncTask
import android.view.View
import android.widget.Button
import com.example.foodorderapp.databinding.ActivityFlashScreenBinding
import com.google.common.net.InternetDomainName

class flashScreen : AppCompatActivity() {
    private lateinit var timer : CountDownTimer
    private lateinit var binding: ActivityFlashScreenBinding
    private var progressBar: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isOnline(this)) {

            // ProgressBar in action
            timer = object: CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.progressBar.visibility = View.VISIBLE
                }

                override fun onFinish() {
                    binding.progressBar.visibility = View.INVISIBLE
                    val intent = Intent(this@flashScreen, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.start()
        } else {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.findViewById<Button>(R.id.OKBtn).setOnClickListener {
                dialog.dismiss()
                finish()
            }
            dialog.show()

        }

    }

    // Checking internet connection
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

}