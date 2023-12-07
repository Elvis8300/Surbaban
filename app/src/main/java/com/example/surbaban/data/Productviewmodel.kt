package com.example.surbaban.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.surbaban.models.Product
import com.example.surbaban.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class Productviewmodel(val navController: NavHostController, val context: Context) {
    var authViewModel:AuthViewModel
    var progress:ProgressDialog
    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggIn()){
            navController.navigate(LOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("please wait...")
    }

    fun UploadProduct(name:String, quantity:String, price:String, filepath:Uri){
        val productId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("products/$productId")
        progress.show()
        storageRef.putFile(filepath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                //save data to do
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageurl = it.toString()
                    var product = Product(name, quantity, price, imageurl,productId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("product/$productId")
                    databaseRef.setValue(product).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this.context,"success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this.context,"error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this.context,"Upload error", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun allproducts(product:MutableState<Product>,
                    products:SnapshotStateList<Product>):SnapshotStateList<Product>{
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Products")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               products.clear()
                for (snap in snapshot.children){
                    var retrievedProduct = snap.getValue(Product::class.java)
                    product.value = retrievedProduct!!
                    products.add(retrievedProduct)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return products
    }
    fun deleteproduct(productId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
                           .child("products/$productId")
        ref.removeValue()
        Toast.makeText(this.context,"success", Toast.LENGTH_SHORT).show()

    }

}