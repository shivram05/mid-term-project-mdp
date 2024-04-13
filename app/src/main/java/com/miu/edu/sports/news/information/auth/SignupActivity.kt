package com.miu.edu.sports.news.information.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.databinding.ActivitySignupBinding
import com.miu.edu.sports.news.information.utils.onErrorTextFieldValidator
import com.miu.edu.sports.news.information.utils.SharedPreferenceStorage
import com.miu.edu.sports.news.information.utils.hideProgressDialog
import com.miu.edu.sports.news.information.utils.makeStatusColor
import com.miu.edu.sports.news.information.utils.showAlert
import com.miu.edu.sports.news.information.utils.showProgressDialog
import com.miu.edu.sports.news.information.utils.showToast
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupActivity : AppCompatActivity(), Validator.ValidationListener {

    companion object {
        var TAG = "SignupActivity"
    }
    @Inject
    lateinit var sharedPreferenceStorage: SharedPreferenceStorage

    @NotEmpty(message = "FirstName Field is required")
    lateinit var firstNameEditText: TextInputEditText

    @NotEmpty(message = "LastName Field is required")
    lateinit var lastNameEditText: TextInputEditText

    @NotEmpty(message = "Email Field is required")
    @Email
    lateinit var emailEditText: TextInputEditText

    @NotEmpty(message = "Password Field is required")
   // User need to put password min 6 character
    @Password
    lateinit var passwordEditText: TextInputEditText

    @NotEmpty(message = "Confirm Password Field is required")
    @ConfirmPassword
    lateinit var confirmPasswordEditText: TextInputEditText

    private lateinit var binding: ActivitySignupBinding

    private lateinit var validator: Validator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusColor()
        binding.signupToolBar.toolbarActionTitle.text = getString(R.string.signup)
        /*
        * initializing all fields
        * */
        initializingFields()

        /*
         initializing validator
        * */
        validator = Validator(this)
        validator.setValidationListener(this)

        binding.signInLinearLayout.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java)).apply {
                finish()
            }
        }
        binding.submit.setOnClickListener {
            validator.validate()
        }
    }

    private fun initializingFields() {
        firstNameEditText = binding.firstNameInputEditText
        lastNameEditText = binding.lastNameInputEditText
        emailEditText = binding.emailInputEdit
        passwordEditText = binding.passwordInputEdit
        confirmPasswordEditText = binding.cPasswordInputEdit
    }

    override fun onValidationSucceeded() {
        showProgressDialog()
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            hideProgressDialog()
            showToast(getString(R.string.please_enter_valid_email_address))
        }else{
            hideProgressDialog()
            Log.d(TAG, "onValidationSucceeded: called")
            /*
            * saving in the sharedPreferenceStorage storage
            * */
            sharedPreferenceStorage.firstName = firstName
            sharedPreferenceStorage.lastName = lastName
            sharedPreferenceStorage.userEmail = email
            sharedPreferenceStorage.userPassword = password

            showAlert(getString(R.string.user_register_successfully), positiveString = getString(R.string.ok),"", onPositiveBtnClicked = {
                startActivity(Intent(this, SignInActivity::class.java)).apply {
                    finish()
                }
            }, onNegativeBtnClicked = {})
        }
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        onErrorTextFieldValidator(errors)
    }
}