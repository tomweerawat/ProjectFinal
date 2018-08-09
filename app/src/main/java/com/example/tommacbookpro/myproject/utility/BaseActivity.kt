package com.example.tommacbookpro.myproject.utility

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.tommacbookpro.myproject.R

import kotlinx.android.synthetic.main.progressdialog.*


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    private var mProgressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progressdialog)

    }

    fun showDialog() {
      /*  mView.show(supportFragmentManager, "")*/
        google_progress.setIndeterminateDrawable(getDrawable(R.mipmap.ic_launcher))


    }
    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setMessage(getString(R.string.loading))
            mProgressDialog!!.isIndeterminate = true
        }
        mProgressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    public override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }
}