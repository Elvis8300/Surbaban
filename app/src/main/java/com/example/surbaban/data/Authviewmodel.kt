package com.example.surbaban.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.surbaban.models.User
import com.example.surbaban.navigation.LOGIN_URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(val navController: NavHostController,val context: Context) {
    val mAuth:FirebaseAuth
    val progress:ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("loading")
        progress.setMessage("Please wait...")
    }
    fun signup(name:String, email:String, password:String){
        progress.show()
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            var userId = mAuth.currentUser!!.uid
            var userprofile = User(name, email, password, userId)
            //create a reference table called Users inside of the firebase database
            var userRef = FirebaseDatabase.getInstance().getReference()
                .child("Users/$userId")
            userRef.setValue(userprofile).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(this.context, "Success",Toast.LENGTH_SHORT).show()
                    navController.navigate(LOGIN_URL)
                }else{
                    Toast.makeText(this.context,"Error",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
    fun login(email: String, password: String){
        progress.show()
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
        progress.show()
        if (it.isSuccessful){
            Toast.makeText(this.context, "Success",Toast.LENGTH_SHORT).show()
            navController.navigate(LOGIN_URL)
        }else{
            Toast.makeText(this.context,"Error",Toast.LENGTH_SHORT).show()
        }
    }
}
    fun logout(){
        mAuth.signOut()
        navController.navigate(LOGIN_URL)
    }
    fun isLoggIn(): Boolean = mAuth.currentUser != null

}