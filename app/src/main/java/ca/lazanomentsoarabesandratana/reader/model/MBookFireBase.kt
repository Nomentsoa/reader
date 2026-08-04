package ca.lazanomentsoarabesandratana.reader.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class MBookFireBase(
    @Exclude
    var id: String? = null,
    var title: String? = null,
    var authors: String? = null,
    var notes: String? = null,
    //we need this to firebase
    @get:PropertyName("image_links")
    @set:PropertyName("image_links")
    var imageLinks: String? = null,
    var stars: Int = 0,
    @get:PropertyName(value = "user_id")
    @set:PropertyName(value = "user_id")
    var userid: String? = null,
    var description: String? = null
)




