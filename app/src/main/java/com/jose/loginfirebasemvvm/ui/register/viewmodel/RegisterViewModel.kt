package com.jose.loginfirebasemvvm.ui.register.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.jose.loginfirebasemvvm.domain.model.User
import com.jose.loginfirebasemvvm.domain.model.UserAuth

class RegisterViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val dbRef = FirebaseDatabase.getInstance().reference

    private var isNameCorrect = false
    private var isSurnameCorrect = false
    private var isEmailCorrect = false
    private var isPasswordCorrect = false
    private var isRepeatPasswordCorrect = false
    private var arePasswordsEquals = false

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _errorName = MutableLiveData<String>()
    val errorName: LiveData<String> = _errorName

    fun onNameChange(newName: String){
        _name.value = newName
    }

    private val _surname = MutableLiveData<String>()
    val surname: LiveData<String> = _surname

    private val _errorSurname = MutableLiveData<String>()
    val errorSurname: LiveData<String> = _errorSurname

    fun onSurnameChange(newSurname: String){
        _surname.value = newSurname
    }

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    fun onEmailChange(newEmail: String){
        _email.value = newEmail
    }

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    fun onPasswordChange(newPassword: String){
        _password.value = newPassword
    }

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _repeatPasswordError = MutableLiveData<String>()
    val repeatPasswordError: LiveData<String> = _repeatPasswordError

    fun onRepeatPasswordChange(newRepeatPassword: String){
        _repeatPassword.value = newRepeatPassword
    }

    private val _isVisibleToast = MutableLiveData<Boolean>()
    val isVisibleToast: LiveData<Boolean> = _isVisibleToast

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun validateName(){
        if(name.value.isNullOrBlank()){
            _errorName.value = "El nombre no puede estar vacío"
            isNameCorrect = false
        }else{
            _errorName.value = ""
            isNameCorrect = true
        }
    }

    fun validateSurname(){
        if(surname.value.isNullOrBlank()){
            _errorSurname.value = "Los apellidos no pueden estar vacíos"
            isSurnameCorrect = false

        }else{
            _errorSurname.value = ""
            isSurnameCorrect = true
        }
    }

    fun validateEmail(){
        //Si es blank o null
        if(email.value.isNullOrBlank()){
            _emailError.value = "El correo no puede estar vacío"
            isEmailCorrect = false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches()){
            //No coincide con un patron de correo
            _emailError.value = "No tiene formato de correo"
            isEmailCorrect = false
        }else{
            _emailError.value = ""
            isEmailCorrect = true
        }
    }

    fun validatePassword(){
        if(password.value.isNullOrBlank()){
            _passwordError.value = "La contraseña no puede estar vacía"
            isPasswordCorrect = false
        }else if (password.value!!.length < 8){
            _passwordError.value = "La contraseña tiene que tener más de 8 caracteres"
            isPasswordCorrect = false
        }else{
            _passwordError.value = ""
            isPasswordCorrect = true
        }
    }

    fun validateRepeatPassword(){
        if(repeatPassword.value.isNullOrBlank()){
            _repeatPasswordError.value = "La contraseña no puede estar vacía"
            isRepeatPasswordCorrect = false
        }else if (password.value!!.length < 8){
            _repeatPasswordError.value = "La contraseña tiene que tener más de 8 caracteres"
            isRepeatPasswordCorrect = false
        }else{
            _repeatPasswordError.value = ""
            isRepeatPasswordCorrect = true
        }
    }

    fun validateBothPasswords(){
        if(isPasswordCorrect && isRepeatPasswordCorrect){
            if(password.value == repeatPassword.value){
                _passwordError.value = ""
                _repeatPasswordError.value = ""
                arePasswordsEquals = true
            }else{
                _passwordError.value = "Las dos contraseñas tienen que ser iguales"
                _repeatPasswordError.value = "Las dos contraseñas tienen que ser iguales"
                arePasswordsEquals = false
            }
        }
    }

    fun registerUser(){
        _isVisibleToast.value = true
        if(isNameCorrect && isSurnameCorrect && isEmailCorrect && arePasswordsEquals){
            registerUserAuth()
        }else{
            _toastMessage.value = "Hay campos incorrectos, revísalo"
        }
    }

    fun registerUserAuth(){
        val email = _email.value!!
        val password = _password.value!!
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
            if(task.isSuccessful){
                val userAuth = auth.currentUser
                registerUserRealTimeDatabase(userAuth!!)
            }else{
                _toastMessage.value = "Ya existe una cuenta con ese correo"
            }
        }
    }

    fun registerUserRealTimeDatabase(userAuth: FirebaseUser){
        val id = userAuth.uid
        val name = _name.value!!
        val surname = _surname.value!!
        val email = userAuth.email!!
        val password = _password.value!!
        dbRef.child("Aplication").child("Users").child(id).setValue(
            User(
                id,
                name,
                surname,
                email,
                password
            )
        )
        _toastMessage.value = "Usuario creado con éxito"
    }

    fun toastDisplayed(){
        _isVisibleToast.value = false
    }

    fun validateAll(){
        validateName()
        validateSurname()
        validateEmail()
        validatePassword()
        validateRepeatPassword()
        validateBothPasswords()
        registerUser()
    }


}