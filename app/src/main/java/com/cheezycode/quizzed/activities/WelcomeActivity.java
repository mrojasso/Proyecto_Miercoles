package com.cheezycode.quizzed.activities

class WelcomeActivity : AppCompatActivity() {

        lateinit var firebaseAuth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
        signUpUser()
        }

        btnLogin.setOnClickListener {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
        }
        }

private fun signUpUser(){
        val email = etEmailAddress.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
        Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
        return
        }

        if (password != confirmPassword) {
        Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
        .show()
        return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this) {
        if(it.isSuccessful){
        Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        }
        else{
        Toast.makeText(this, "Error creating user.", Toast.LENGTH_SHORT).show()
        }
        }
        }
        }