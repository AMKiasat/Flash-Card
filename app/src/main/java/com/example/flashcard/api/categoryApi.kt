import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class CategoryApi(
    val id: Int?,
    val word: String,
)


interface CategoryApiService {

    @GET("category")
    suspend fun getMovies(): List<CategoryApi>

    @POST("category")
    suspend fun setWords(@Body wordList: List<CategoryApi>): String

    companion object {
        var apiService: CategoryApiService? = null
        fun getInstance(): CategoryApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://flash-card-flash-card.fandogh.cloud/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(CategoryApiService::class.java)
            }
            return apiService!!
        }
    }

}

class CategoryApiViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var returned = mutableListOf<CategoryApi>()

    fun getCategoryList() {
        viewModelScope.launch {
            val apiService = CategoryApiService.getInstance()
            try {
                returned = apiService.getMovies() as MutableList<CategoryApi>
                Log.d("wordApiList", "onCreate: $returned")
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("wordApiListERROR", "getWordList:$errorMessage ")
            }
        }
    }

    fun storeCategoryList(wordList: List<CategoryApi>) {
        viewModelScope.launch {
            val apiService = CategoryApiService.getInstance()
            try {
                Log.d("wordApiList", "sending $wordList")
                apiService.setWords(wordList = wordList)
                Log.d("wordApiList", "send succses")

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("wordApiListERROR", "$errorMessage ")
            }
        }

    }
}