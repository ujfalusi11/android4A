package com.esiea.android4A

import android.util.Patterns
import java.util.regex.Pattern


object Validator {


    object Login {

        fun email(email: String): Result = when {
            email.isEmpty() -> Result(
                R.string.val_error_email_required
            )
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Result(
                R.string.val_error_email_valid
            )
            else -> Result(
                success = true,
                message = R.string.success
            )
        }

        fun password(password: String): Result = when {
            password.isEmpty() -> Result(
                R.string.val_error_password_required
            )
            else -> Result(
                success = true,
                message = R.string.success
            )
        }


    }

    object Register {

        fun name(name: String): Result = when {
            name.isEmpty() -> Result(R.string.val_error_name_required)
            else -> Result(
                success = true,
                message = R.string.success
            )
        }

        fun password(password: String): Result = when {
            password.isEmpty() -> Result(
                R.string.val_error_password_required
            )
            !PasswordPatterns.DIGIT.matcher(password).find() -> Result(
                R.string.val_error_password_digit
            )
            !PasswordPatterns.LOWER_CASE.matcher(password).find() -> Result(
                R.string.val_error_password_lower
            )
            !PasswordPatterns.UPPER_CASE.matcher(password).find() -> Result(
                R.string.val_error_password_upper
            )
            !PasswordPatterns.MIN.matcher(password).find() -> Result(
                R.string.val_error_password_min,
                false,
                PasswordPatterns.MIN_CHARACTERS
            )
            !PasswordPatterns.MAX.matcher(password).find() -> Result(
                R.string.val_error_password_max,
                false,
                PasswordPatterns.MAX_CHARACTERS
            )
            else -> Result(
                success = true,
                message = R.string.success
            )
        }

        fun gender(gender: String?): Result = when (gender) {
            null -> Result(R.string.val_error_gender_required)
            else -> Result(
                success = true,
                message = R.string.success
            )
        }

        fun nationality(nationality: String): Result = when {
            nationality.isEmpty() -> Result(
                R.string.val_error_nationality_required
            )
            else -> Result(
                success = true,
                message = R.string.success
            )

        }

    }

    data class Result(
        val message: Int,
        val success: Boolean = false,
        val arg: Int? = null
    )

    object PasswordPatterns {

        const val MIN_CHARACTERS = 8

        const val MAX_CHARACTERS = 20

        //    At least 1 digit
        val DIGIT = Pattern.compile("[0-9]")

        //At least 1 upper case character
        val UPPER_CASE = Pattern.compile("[A-Z]")

        //    At least 1 lower case character
        val LOWER_CASE = Pattern.compile("[a-z]")

        //    At least MIN_CHARACTERS characters
        val MIN = Pattern.compile(
            "^" +
                    ".{" + MIN_CHARACTERS + ",}" +
                    "$"
        )
        //    Max MAX_CHARACTERS characters
        val MAX = Pattern.compile(
            "^" +
                    ".{1," + MAX_CHARACTERS + "}" +
                    "$"
        )
    }

}