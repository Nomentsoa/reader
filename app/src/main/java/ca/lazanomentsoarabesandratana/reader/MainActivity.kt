package ca.lazanomentsoarabesandratana.reader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.lazanomentsoarabesandratana.reader.navigation.ReaderNavigation
import ca.lazanomentsoarabesandratana.reader.ui.theme.ReaderTheme
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReaderTheme {

                /*val db = FirebaseFirestore.getInstance()
                val user: MutableMap<String, Any> = HashMap()
                user["firstName"] = "Laza"
                user["lastName"] = "Rabesandratana"

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        Log.d("FB", "DocumentSnapshot added with ID: " + it.id)
                    }.addOnFailureListener {
                        Log.d("FB", "Error adding document: " + it.message)
                    }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding).fillMaxSize())

                }*/
                ReaderApp()

            }
        }
    }
}

@Composable
fun ReaderApp(){
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            ReaderNavigation()
        }
    }

}