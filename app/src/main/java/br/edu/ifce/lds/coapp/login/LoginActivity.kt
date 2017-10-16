package br.edu.ifce.lds.coapp.login

import android.os.Bundle
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import com.bumptech.glide.Glide
import com.facebook.FacebookSdk
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //start up facebook sdk before creating views
        FacebookSdk.sdkInitialize(this.applicationContext)
        setContentView(R.layout.activity_login2)

        Glide
                .with(this)
                .load(R.drawable.office_background)
                .centerCrop()
                .into(backgroundImage)
    }
}
