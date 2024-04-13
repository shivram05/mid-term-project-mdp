package com.miu.edu.sports.news.information.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.databinding.ActivitySignInBinding
import com.miu.edu.sports.news.information.screen.MainActivity
import com.miu.edu.sports.news.information.utils.SharedPreferenceStorage
import com.miu.edu.sports.news.information.utils.hideProgressDialog
import com.miu.edu.sports.news.information.utils.makeStatusColor
import com.miu.edu.sports.news.information.utils.onErrorTextFieldValidator
import com.miu.edu.sports.news.information.utils.showAlert
import com.miu.edu.sports.news.information.utils.showProgressDialog
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity(), Validator.ValidationListener {

    @Inject
    lateinit var sharedPreferenceStorage: SharedPreferenceStorage

    @NotEmpty(message = "Email Field is required")
    @Email
    lateinit var emailEditText: TextInputEditText

    @NotEmpty(message = "Password Field is required")
    lateinit var passwordEditText: TextInputEditText

    private lateinit var binding: ActivitySignInBinding

    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusColor()
        binding.signInToolBar.toolbarActionTitle.text = getString(R.string.sign_in)

        /*
       * initializing all fields
       * */
        initializingFields()

        /*
         initializing validator
        * */
        validator = Validator(this)
        validator.setValidationListener(this)

        binding.signIn.setOnClickListener {
            validator.validate()
        }
        binding.signUpLinearLayout.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java)).apply {
                finish()
            }
        }
    }

    private fun initializingFields() {
        emailEditText = binding.emailInputEdit
        passwordEditText = binding.passwordInputEdit
    }

    override fun onValidationSucceeded() {
        showProgressDialog()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email == sharedPreferenceStorage.userEmail && password == sharedPreferenceStorage.userPassword){
            hideProgressDialog()
            showAlert(getString(R.string.user_login_successfully), positiveString = getString(R.string.ok),"", onPositiveBtnClicked = {
                startActivity(Intent(this,MainActivity::class.java).apply {
                    putExtra("userEmail",email)
                })
                finish()
            }, onNegativeBtnClicked = {})
        }else{
            hideProgressDialog()
            showAlert(getString(R.string.invalid_email_or_password), positiveString = "",getString(R.string.ok), onPositiveBtnClicked = {}, onNegativeBtnClicked = {})
        }
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        onErrorTextFieldValidator(errors)
    }
}