import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mg.goldenmarc.data.model.UserResponseErrorLogin
import com.mg.goldenmarc.data.model.UserResponseLogin
import com.mg.goldenmarc.data.remote.network.AuthRepository
import kotlinx.coroutines.launch


class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<UserResponseLogin?>>()
    val loginResult: LiveData<Result<UserResponseLogin?>> get() = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.login(username, password)
                if (response.isSuccessful) {
                    _loginResult.value = Result.success(response.body())
                } else {
                    val errorResponse = authRepository.handleErrorResponse(response, Gson())
                    _loginResult.value = Result.failure(Throwable(errorResponse ?: "Unknown error"))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}

