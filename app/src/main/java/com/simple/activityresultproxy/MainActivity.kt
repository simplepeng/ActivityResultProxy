package com.simple.activityresultproxy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.simple.activityresultproxy.LoginHelper.OnLoginListener
import com.simple.proxy.ARProxy
import com.simple.proxy.ARProxy.OnResultListener
import com.simple.proxy.navTo
import com.simple.proxy.startActivityForResult

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val REQUEST_CODE = 110
    private val REQUEST_CODE_CHOICE_IMAGE = 111

    private var mTvRequestCode: TextView? = null
    private var mTvResultCode: TextView? = null
    private var mTvData: TextView? = null
    private var mTvUser: TextView? = null
    private var mIvImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvRequestCode = findViewById<TextView>(R.id.tv_requestCode)
        mTvResultCode = findViewById<TextView>(R.id.tv_resuleCode)
        mTvData = findViewById<TextView>(R.id.tv_data)
        mTvUser = findViewById<TextView>(R.id.tv_user)
        mIvImage = findViewById<ImageView>(R.id.ivImage)
    }

    fun go(view: View?) {
        val intent = Intent(this@MainActivity, ToActivity::class.java)
        intent.putExtra("name", "simple")

//        ARProxy.navTo(this, ToActivity::class.java)
//            .putExtra("name", "simple")
//            .putExtra("age", 26)
//            .putExtra("man", true)
//            .startActivityForResult(REQUEST_CODE, OnResultListener { requestCode: Int, resultCode: Int, data: Intent? ->
//                if (data == null) return@OnResultListener
//                val reqCode = String.format("requestCode : %s", requestCode)
//                val resultText = String.format("resultCode  :%s", resultCode)
//
//                mTvRequestCode!!.setText(reqCode)
//                mTvResultCode!!.setText(resultText)
//
//                val extras = data.getExtras()
//                val dataText = String.format(
//                    "data : %s-%s", extras!!.getString("username"),
//                    extras.getBoolean("isLogin")
//                )
//
//                mTvData!!.setText(dataText)
//
//                Log.d(TAG, reqCode)
//                Log.d(TAG, resultText)
//                Log.d(TAG, dataText)
//            })
        navTo(ToActivity::class.java)
            .putExtra("name", "simple")
            .putExtra("age", 26)
            .putExtra("man", true)
            .startActivityForResult(REQUEST_CODE) { requestCode, resultCode, data ->
                if (data == null) return@startActivityForResult
                val reqCode = String.format("requestCode : %s", requestCode)
                val resultText = String.format("resultCode  :%s", resultCode)

                mTvRequestCode!!.setText(reqCode)
                mTvResultCode!!.setText(resultText)

                val extras = data.getExtras()
                val dataText = String.format(
                    "data : %s-%s", extras!!.getString("username"),
                    extras.getBoolean("isLogin")
                )

                mTvData!!.setText(dataText)

                Log.d(TAG, reqCode)
                Log.d(TAG, resultText)
                Log.d(TAG, dataText)
            }
    }

    fun login(view: View?) {
        LoginHelper.isLogin(this@MainActivity, object : OnLoginListener {
            override fun onLogin(user: UserBean) {
                mTvUser!!.setText(String.format("user : %s - %s", user.getName(), user.getPassword()))
            }
        })
    }

    fun choiceImage(view: View?) {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)

        //        startActivityForResult(Intent.createChooser(intent, "choice image"), REQUEST_CODE_CHOICE_IMAGE);
//        ARProxy.navTo(this, intent) //                .startActivity();
//            .startActivityForResult(REQUEST_CODE_CHOICE_IMAGE, object : OnResultListener {
//                override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//                    if (requestCode == REQUEST_CODE_CHOICE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//                        val uri = data.getData()
//                        mIvImage!!.setImageURI(uri)
//                    }
//                }
//            })
        startActivityForResult(REQUEST_CODE_CHOICE_IMAGE, intent) { requestCode, resultCode, data ->
            if (requestCode == REQUEST_CODE_CHOICE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                val uri = data.getData()
                mIvImage!!.setImageURI(uri)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("onActivityResult", "requestCode = " + requestCode + "--resultCode = " + resultCode)
        if (data != null && data.getExtras() != null) {
            Log.d("onActivityResult", "data = " + data.getExtras().toString())
        }

        if (requestCode == REQUEST_CODE_CHOICE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            val uri = data.getData()
            mIvImage!!.setImageURI(uri)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "MainActivity onDestroy")
    }

//    @Throws(Throwable::class)
//    protected fun finalize() {
//        super.finalize()
//        Log.d("MainActivity", "MainActivity finalize")
//    }
}
