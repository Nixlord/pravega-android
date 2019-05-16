package com.phoenixoverlord.pravega.extensions

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import io.reactivex.Observable
import java.io.File
import java.lang.Error
import java.util.*


object Firebase {
    /**
     * Custom getters so that we don't store a reference to context
     * (FirebaseFirestore.getInstance() has reference to context)
     */
    val storage : StorageReference
        get() = FirebaseStorage.getInstance().reference

    val realtime : DatabaseReference
        get() = FirebaseDatabase.getInstance().reference

    val firestore : FirebaseFirestore
        get() = FirebaseFirestore.getInstance()

    val auth : FirebaseAuth
        get() = FirebaseAuth.getInstance()
}


fun StorageReference.pushImage(compressedImage : File, imageName: String) : UploadTask {
    val imageStorage = this.child("images").child(imageName)

    return imageStorage.putFile(Uri.fromFile(compressedImage))
}


/*
fun FirebaseFirestore.savePost(
    post: Post, image: File,
    onSuccess : () -> Unit = { logDebug("savePost", "default") }
) : UploadTask {

    val postDocument = this.collection("posts").document(post.postID)

    val storeImageTask = storage.pushImage(image, post.imageID!!)

    storeImageTask.addOnSuccessListener {
        postDocument.set(post)
            .addOnSuccessListener {
                onSuccess()
            }
    }

    return storeImageTask
}
*/

fun<T> DocumentReference.addSnapshotListener(
    valueType: Class<T>,
    objectListener : (snapshot : T) -> Unit
) {
    this.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
        if (firebaseFirestoreException != null) {
            logError(firebaseFirestoreException)
        }

        else if (documentSnapshot == null || ! documentSnapshot.exists()) {
            logError(Error("No Such Document"))
        }

        else {
            val obj = documentSnapshot.toObject(valueType)
            obj?.apply(objectListener) ?: logError(Error("Null Object"))
        }
    }
}
