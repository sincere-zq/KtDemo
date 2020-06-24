package com.witation.ktdemo.ui.act

import android.content.Intent
import com.witation.ktdemo.R
import com.witation.ktdemo.base.BVMAct
import com.witation.ktdemo.databinding.ActivityLoginBinding
import com.witation.ktdemo.utils.toast
import com.witation.ktdemo.vm.LoginVM

class LoginActivity : BVMAct<ActivityLoginBinding, LoginVM>() {

    override fun viewbind(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
    override fun viewmodel() = LoginVM::class.java
    fun login() {
        val account = vb.etAccount.text
        if (account.isEmpty()) {
            toast(this, R.string.please_input_account)
            return
        }
        val password = vb.etPassword.text
        if (password.isEmpty()) {
            toast(this, R.string.please_input_password)
            return
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun initViews() {
        vb.btnLogin.setOnClickListener {
            login()
        }
    }

    override fun initDatas() {

    }

}
