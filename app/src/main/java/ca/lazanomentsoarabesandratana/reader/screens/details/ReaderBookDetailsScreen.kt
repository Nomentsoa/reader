package ca.lazanomentsoarabesandratana.reader.screens.details

import android.R
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import ca.lazanomentsoarabesandratana.reader.components.ReaderAppBar
import ca.lazanomentsoarabesandratana.reader.data.DataOrException
import ca.lazanomentsoarabesandratana.reader.model.MBook
import coil.compose.rememberImagePainter
import androidx.compose.ui.platform.LocalResources
import ca.lazanomentsoarabesandratana.reader.components.RoundedButton
import ca.lazanomentsoarabesandratana.reader.model.MBookFireBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun BookDetailsScreen(
    navController: NavController,
    bookId: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        ReaderAppBar(
            title = "Book Details",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            showProfile = false
        ) {
            navController.popBackStack()
        }
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val bookInfo = produceState<DataOrException<MBook, Boolean, Exception>>(
                    initialValue = DataOrException(loading = true)
                ) {
                    value = viewModel.getBookInfo(bookId)
                }.value

                if (bookInfo.data == null) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                } else {
                    ShowBookDetails(bookInfo, navController)
                }
            }

        }
    }


}

@Composable
fun ShowBookDetails(
    bookInfo: DataOrException<MBook, Boolean, Exception>,
    navController: NavController
) {
    val bookData = bookInfo.data
    val bookId = bookData?.id

    Card(
        modifier = Modifier.padding(34.dp),
        shape = CircleShape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = bookData?.imageLinks),
            contentDescription = "Book Image",
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
                .padding(1.dp)
        )
    }

    Text(
        text = bookData?.title.toString(),
        style = MaterialTheme.typography.titleMedium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 5
    )
    Text(text = "Authors:  ${bookData?.authors.toString()}")
    Text(text = "Stars:  ${bookData?.stars.toString()}")
    Text(text = "notes:  ${bookData?.notes.toString()}")

    Spacer(modifier = Modifier.height(5.dp))

    val localDims = LocalResources.current.displayMetrics
    Surface(
        modifier = Modifier
            .height(localDims.heightPixels.dp.times(0.09f))
            .fillMaxWidth()
            .padding(4.dp),
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Text(text = bookData?.description.toString())
    }

    //Button
    Row(modifier = Modifier.padding(top = 6.dp), horizontalArrangement = Arrangement.SpaceAround) {
        RoundedButton(label = "Save") {
            //save this book to the firestore database
            val bookData = MBookFireBase(
                title = bookData?.title,
                authors = bookData?.authors,
                notes = "",
                imageLinks = bookData?.imageLinks,
                description = bookData?.description,
                userid = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                stars = 0
            )
            saveToFirebase(bookData, navController)
        }

        Spacer(modifier = Modifier.width(25.dp))

        RoundedButton(label = "Cancel") {
            navController.popBackStack()
        }
    }


}

fun saveToFirebase(book: MBookFireBase?, navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    val dbCollection = db.collection("books")
    if (book.toString().isNotEmpty()) {
        if (book != null) {
            dbCollection.add(book)
                .addOnSuccessListener { documentRef ->
                    val docId = documentRef.id
                    dbCollection.document(docId)
                        .update(hashMapOf("id" to docId) as Map<String, Any>)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.popBackStack()
                            }
                        }
                        .addOnFailureListener {
                            Log.d("Error", "saveToFirebase: Error updating doc", it)

                        }
                }
                .addOnFailureListener {
                    Log.d("Error", "saveToFirebase: Error updating doc", it)

                }
        }

    } else {

    }
}
