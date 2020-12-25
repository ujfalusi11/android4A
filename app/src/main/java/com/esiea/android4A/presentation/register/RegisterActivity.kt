package com.esiea.android4A.presentation.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.esiea.android4A.R
import com.esiea.android4A.presentation.Validator
import com.esiea.android4A.presentation.register.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.widget.Toast
import com.esiea.android4A.presentation.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    //Creation RegisterViewModel
    private val registerViewModel: RegisterViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        registerViewModel.registerLiveData.observe(this, Observer {
            when(it){
                is RegisterSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Success")
                        .setMessage("Your account has been created")
                        .setPositiveButton("Ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                    //finish()
                }
                RegisterError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("Login or password empty")
                        .setPositiveButton("Ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        })

        signUpButton.setOnClickListener {

            val name = nameEditText.text.toString().trim()

            val email = emailEditText.text.toString().trim()

            val password = passwordEditText.text.toString().trim()

            val nationality = nationalityEditText.text.toString().trim()

            val gender = if (maleRadioButton.isChecked && !femaleRadioButton.isChecked) {

                "male"

            } else if (femaleRadioButton.isChecked && !maleRadioButton.isChecked) {

                "female"

            } else {

                null

            }

            var success = true

            val nameResult = Validator.Register.name(name)

            val emailResult = Validator.Login.email(email)

            val passwordResult = Validator.Register.password(password)

            val nationalityResult = Validator.Register.nationality(nationality)

            val genderResult = Validator.Register.gender(gender)

            if (!nameResult.success) {

                nameEditText.error = getString(nameResult.message)

                success = false
            }

            if (!emailResult.success) {

                emailEditText.error = getString(emailResult.message)

                success = false
            }
            if (!passwordResult.success) {

                passwordEditText.error = getString(passwordResult.message, passwordResult.arg)

                success = false

            }

            if (!nationalityResult.success) {

                nationalityEditText.error = getString(nationalityResult.message)

                success = false
            }

            if (!genderResult.success) {

                Toast.makeText(applicationContext, genderResult.message, Toast.LENGTH_SHORT).show()
                success = false

            }



            if (!success) {
                return@setOnClickListener
            }

            registerViewModel.onClickedRegister(name, email, password, gender!!, nationality)
        }

        backButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}