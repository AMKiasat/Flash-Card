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
import retrofit2.http.POST


data class LoginData(
    val username: String,
    val password: String,

    )


interface LoginApiService {

    @POST("login")
    suspend fun login(@Body loginData: LoginData):String

    companion object {
        var apiService: LoginApiService? = null

        fun getInstance(): LoginApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://flash-card-flash-card.fandogh.cloud/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(LoginApiService::class.java)
            }
            return apiService!!
        }
    }

}

class LoginApiViewModel : ViewModel() {
    var status: String by mutableStateOf("401")

    fun checkLogin(loginData: LoginData) {
        viewModelScope.launch {
            val apiService = LoginApiService.getInstance()
            try {
                val res = apiService.login(loginData)
                status = res
                Log.d("loginApi", "onCreate: $res")
            } catch (e: Exception) {
                Log.d("loginApiERROR", "getWordList:$e.message.toString() ")
            }
        }
    }
}